package com.dsj.modules.system.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.CommConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;
import com.dsj.modules.system.dao.AgentDao;
import com.dsj.modules.system.dao.EmployeeDao;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.evaluate.service.AgentInfoService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;

/**
 *
 * @鎻忚堪: Service鎺ュ彛瀹炵幇绫�.
 * @浣滆��: gaocj
 * @鍒涘缓鏃堕棿: 2017-06-27 19:45:51.
 * @鐗堟湰: 1.0 .
 */
@Service
public class AgentServiceImpl  extends BaseServiceImpl<AgentDao,AgentPo> implements AgentService {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentServiceImpl.class);
	@Autowired
	private AgentDao agentDao;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private AreaService areaService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private AgentInfoService agentInfoService;
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public String getAgentCode() {
		List<Integer> agentCodelist = agentDao.getAgentCode();
		if(agentCodelist.size()>0){
			List<Integer> agentCodelistTemp = new ArrayList<Integer>();
			for(Integer item : agentCodelist){
				if(String.valueOf(item).length()==9 && "1002".equals(String.valueOf(item).substring(0, 4))){
					agentCodelistTemp.add(item);
				}
			}
			if(agentCodelistTemp.size()>0){
				int max = 0;
				for(Integer itemTemp : agentCodelistTemp){
					if(itemTemp > max){
						max = itemTemp;
					}
				}
				return String.valueOf(max+1);
			}else{
				return CommConst.AGENT_NUM_START;
			}
		}
		return CommConst.AGENT_NUM_START;
	}



	@Override
	public void deleteAgent(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer delFlag = DeleteStatusEnum.DEL.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			employeeDao.deleteByIds(idlist,delFlag);
			
				
		}
		
	}


	@Override
	public void updateResetPwdMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			UserPo user =new UserPo();
			Map<String,Object> map = new HashMap<String,Object>();

			map.put("password", ShiroSaltAndMd5Utils.getMD5(CommConst.INIT_PWD));
			map.put("list", idlist);
			agentDao.resetPwdMany(map);
			
				
		}
		
	}


	@Override
	public void updateAuditMany(String ids,String msg, String status, Integer auditPerson) {
		if(StringUtils.isNotBlank(ids)){
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			if(status.equals(AgentStatus.AUDIT_SUCCESS.getCode().toString())){
				map.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
				for(int i = 0;i<arrayId.length;i++){
					AgentPo agent = agentDao.getById(Long.valueOf(arrayId[i]));
					Long cityCode = null;
					String cityName = null;
					if(agent.getCity()!=null){
						Map<String,Object> map1 = new HashMap<String,Object>();
						map1.put("areaCode", agent.getCity());
						AreaPo a = areaService.getBy(map1);
						cityCode = Long.parseLong(agent.getCity());
						cityName = a.getName();
					}
					int education = 0;
					if(agent.getEducation().equals("本科")||agent.getEducation().equals("研究生及以上")){
						education = 1;
					}else if(agent.getEducation().equals("专科")){
						education = 2;
					}else{
						education = 3;
					}
					int workyear = 0;
					if(agent.getWorkyears()>=3){
						workyear = 1;
					}else{
						workyear = 2;
					}
					//经纪人评价体系
					agentInfoService.addEducationScore(agent.getAgentCode().longValue(), agent.getName(), cityCode,cityName, education,auditPerson);
					agentInfoService.addExperienceScore(agent.getAgentCode().longValue(), agent.getName(), cityCode, cityName, workyear, auditPerson);
					
				}
				
				
			}else{
				map.put("auditStatus", AgentStatus.AUDIT_REFUSE.getCode());
			}
			
			map.put("auditUser",auditPerson);
			map.put("auditTime", new Date());
			map.put("list", idlist);
			agentDao.updateAuditMany(map);
			List<Map> Numberlist = agentDao.findPhone(idlist);
			if(status.equals(AgentStatus.AUDIT_SUCCESS.getCode().toString())){
				for(Map item : Numberlist ){
					try {
						if(Integer.valueOf(item.get("isExternalRegist").toString())==1){
							SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.AGENT_AUDIT_SUCCESS, item.get("phone").toString()), item.get("phone").toString());
						}else{
							SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.AUDIT_SUCCESS, item.get("phone").toString()), item.get("phone").toString());
						}
						
					} catch (Exception e) {
						LOGGER.error("鐭俊寮傚父" , e);
					}
				}	
			}else{
				for(Map item : Numberlist ){
					
					try {
						SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.AUDIT_FAIL, msg), item.get("phone").toString());
					} catch (Exception e) {
						LOGGER.error("鐭俊寮傚父" , e);
					}
				}	
			}
				
		}
		
	}


	@Override
	public PageBean listAgentPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listAgentCount", "listAgentList");
	}

	@Override
	public AgentPo selectAgent(Long agentId) {
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("id", agentId);
		return dao.agentId(hashMap);
	}

	@Override
	public List<AgentPo> selectSort() {
		
		List<AgentPo> sortList = agentDao.selectSort();
		for(int i=1;i<=20;i++){
			int flag = 0;
			for(AgentPo item:sortList){
				if(i==item.getSort()){
					flag=1;
				}
			}
			if(flag==0){
				AgentPo po = new AgentPo();
				po.setSort(i);
				sortList.add(po);
			}
		}

		return sortList;
	}


	@Override
	public List<AgentPo> selectByName(Map<String,Object> map) {
		return agentDao.selectByName(map);
	}

	@Override
	public long findAgentRecommend(int agentId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("agentId", agentId);
		return dao.selectAgentRecommend(paramMap);
	}


	@Override
	public long insertAgentDirectory(AgentDirectoryVo agentDirectoryVo) {
		return dao.insertAgentDirectory(agentDirectoryVo);
	}

	@Override
	public AgentDirectoryVo selectAgentDirectory(Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("newHouseId", newHouseId);
		return dao.selectAgentDirectory(paramMap);
	}

	@Override
	public List<AgentDirectoryVo> findListAgent(int agentId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("agentId", agentId);
		return dao.selectAgentDirectoryList(paramMap);
	}

	@Override
	public long deleteAgentDircetory(Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("newHouseId", newHouseId);
		return dao.deleteAgentDircetory(paramMap);
	}

	@Override
	public long findShowShelves(int agentId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("agentId", agentId);
		return dao.findShowShelves(paramMap);
	}


	@Override
	public void deleteShelves(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		 dao.deleteShelves(paramMap);
	}

	@Override
	public AgentDirectoryVo selectShowShelves(Long newHouseId,int type) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("newHouseId", newHouseId);
		paramMap.put("type", type);
		return dao.selectShowShelves(paramMap);
	}

	@Override
	public long insertShowShelves(AgentDirectoryVo agentDirectoryVo) {
		return dao.insertShowShelves(agentDirectoryVo);
	}

	@Override
	public long deleteShowShelves(Long newHouseId, int type) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("houseId", newHouseId);
		paramMap.put("type", type);
		 return dao.deleteShowShelves(paramMap);
	}

	@Override
	public List<AgentDirectoryVo> findListSevlves(int agentId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("agentId", agentId);
		return dao.selectListSevlves(paramMap);
	}

	@Override
	public AgentVo getByUserId(long userId) {
		
		return agentDao.getByUserId(userId);
	}

	@Override
	public List<AgentPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}

	@Override
	public List<AgentPo> getAgent(HashMap<String, Object> map) {
		return dao.getAgent(map);
	}

	@Override
	public List<AgentVo> getLikeAgent(HashMap<String, Object> map) {
		return dao.getLikeAgent(map);
	}

	@Override
	public AgentVo getVoById(long id) {
		return agentDao.getVoById(id);
	}


	@Override
	public void updateSort(Map<String, Object> map) {
		
		agentDao.updateSort(map);
	}

	@Override
	public List<AgentVo> getAgentBySelect(HashMap<String, Object> map) {
		return agentDao.getAgentBySelect(map);
	}
	
	@Override
	public List<AgentVo> getRentHouse() {
		return dao.getAgentDirectory();
	}

	@Override
	public AgentVo getAgentByHouseId(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", id);
		return dao.getAgentByHouseId(map);
	}
     
	@Override
	public AgentDirectoryVo selectDateMin(int agentId) {
		Map<String, Object> map = new HashMap<>();
		map.put("agentId", agentId);
		return dao.selectDateMin(map);
	}

	@Override
	public List<AgentVo> getVoByRentHouseId(Map<String, Object> map) {
		
		return dao.getVoByRentHouseId(map);
	}


	@Override
	public List<AgentVo> findFollow(Map<String, Object> map) {
		
		return agentDao.findFollow(map);
	}
	@Override
	public List<AgentVo> getNewHouseAgentDirectory(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("newHouseId", id);
		paramMap.put("is_duty", 1);
		List<AgentVo> list = dao.getNewHouseAgentDirectory(paramMap);
		for (AgentVo agentVo : list) {
			agentVo.setFeatureArray(agentVo.getFeature().split(","));
		}
		return list; 
	}
	@Override
	public List<AgentVo> getNewHouseAgentDirectoryTwo(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("newHouseId", id);
		List<AgentVo> list = dao.getNewHouseAgentDirectory(paramMap);
		for (AgentVo agentVo : list) {
			if(StringUtils.isNotBlank(agentVo.getFeature())){
				agentVo.setFeatureArray(agentVo.getFeature().split(","));
			}
		}
		return list; 
	}

	@Override
	public long findFollowCount(Map<String, Object> map) {
		return agentDao.findFollowCount(map);
	}

	@Override
	public List<AgentVo> lookHistory(Map<String, Object> map) {
		return agentDao.lookHistory(map);
	}

	@Override
	public long lookHistoryCount(Map<String, Object> map) {
		return agentDao.lookHistoryCount(map);
	}

	@Override
	public int findIsFollow(long objectId,long userId,Integer type) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("objectId",objectId);
		map.put("type",type);
		
		return agentDao.findIsFollow(map);
	}

	@Override
	public List<AgentVo> getRentHousePage() {
		return dao.getRentHousePage();
	}

	@Override
	public List<RecommendVo> getRecommend(Integer userId) {
		return dao.getRecommend(userId);
	}

	@Override
	public AgentVo getScoreVoById(Long id) {
		return dao.getScoreVoById(id);
	}

	@Override
	public List<AgentVo> listByNewHouseId(Long id) {
		return dao.listByNewHouseId(id);
	}

	@Override
	public List<com.dsj.modules.newhouse.vo.AgentFrontVo> listByMap(HashMap<String, Object> map) {
		return dao.listByMap(map);
	}

}