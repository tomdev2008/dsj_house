package com.dsj.modules.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.CommConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 *
 * @鎻忚堪: Service鎺ュ彛瀹炵幇绫�.
 * @浣滆��: gaocj
 * @鍒涘缓鏃堕棿: 2017-06-15 14:38:53.
 * @鐗堟湰: 1.0 .
 */
@Service
public class UserServiceImpl  extends BaseServiceImpl<UserDao,UserPo> implements UserService {
	
	@Autowired
	SmsLogsService smsLogsService;
	
	@Autowired
	EvelopersService evelopersService;
	
	@Override
	public UserPo getUserByName(String username) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("username", username);
		paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.getBy(paramMap);
	}

	//寮�鍙戝晢閲嶇疆瀵嗙爜
	@Override
	public void updateEvelopersReset(Long id) {
		UserPo userPo = new UserPo();
		userPo.setId(id);
		
		userPo.setPassword(ShiroSaltAndMd5Utils.getMD5(CommConst.INIT_PWD));
		dao.updateDynamic(userPo);
	}

	@Override
	public void updateEvelopersAudit(String ids, int status) {
		if(StringUtils.isNotBlank(ids)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids);
			map.put("status", status);
			dao.updateEvelopersAudit(map);
		}
		if(status==UserStatusEnum.YES.getValue()){
			List<EvelopersVo>  list= evelopersService.listByIds(ids);
			for (EvelopersVo evelopersPo : list) {
				smsLogsService.saveLogsAndsend(evelopersPo.getOperationPhone(),SMSTemplate.getDrawingTemplate(SMSTemplate.EVELOPER_AUTH_RESULT,evelopersPo.getUsername()));
			}
		}
		
	}

	@Override
	public int updatePasswordByPhone(String phone, String password1,Integer userType) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("phone", phone);
		map.put("password", ShiroSaltAndMd5Utils.getMD5(password1));
		map.put("userType", userType);
		return dao.updatePasswordByPhone(map);
	}

	@Override
	public void updateEvelopersDeleteFlag(String ids, Integer delFlag) {
		if(StringUtils.isNotBlank(ids)){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids);
			map.put("delFlag", delFlag);
			dao.updateEvelopersDelFlag(map);
		}
	}

	@Override
	public UserPo getUserByName(String username, int userType) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("username", username);
		paramMap.put("userType", userType);
		paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.getBy(paramMap);
	}

	@Override
	public UserPo getUserByPhone(String phone, int userType) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("phone", phone);
		paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("userType", userType);
		return dao.getBy(paramMap);
	}

	@Override
	public UserPo getByLeftEveloper(HashMap<String, Object> paramMap) {
		return dao.getByLeftEveloper(paramMap);
	}
	
	@Override
	public List<UserPo> listAgentUserBy(Map<String, Object> paramMap) {
		return dao.listAgentUserBy(paramMap);
	}

	@Override
	public List<NewHouseAgentFrontVo> getNewHouseAgent(HashMap<String, Object> map) {
		return dao.getNewHouseAgent(map);
	}

	@Override
	public void deleteFollow(long id) {
		dao.deleteFollow(id);
		
	}

	@Override
	public void insertFollow(Map<String, Object> map) {
		dao.insertFollow(map);
		
	}
	
	@Override
	public void deleteFollowByMap(Map<String, Object> map) {
		dao.deleteFollowByMap(map);
		
	}

	@Override
	public void deleteManyFollow(List<Integer> ids) {
		dao.deleteManyFollow(ids);
		
	}

	@Override
	public UserPo getByLeftPropert(HashMap<String, Object> map) {
		return dao.getByLeftPropert(map);
	}

	@Override
	public int findByUsername(String phone) {
		return dao.findByUsername(phone);
	}

	@Override
	public String getNewHouseIdsByMap(HashMap<String, Object> map) {
		return dao.getNewHouseIdsByMap(map);
	}

	@Override
	public UserPo getUserByPhone(String phone) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("phone", phone);
		paramMap.put("del_falg", DeleteStatusEnum.NDEL.getValue());
		return dao.getBy(paramMap);
	}

	@Override
	public Integer findIsHistory(Map<String, Object> map) {
		return dao.findIsHistory(map);
	}

	@Override
	public void updateLookHistory(Map<String, Object> map) {
		dao.updateLookHistory(map);
		
	}

	@Override
	public void insertLookHistory(Map<String, Object> map) {
		dao.insertLookHistory(map);
		
	}

	@Override
	public void updateHandleLookHistory(int type,long objectId,long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("type", type);//有对应的枚举类型
		map.put("objectId", objectId);//房子Id
		map.put("userId", userId);
		Integer id = findIsHistory(map);
		if(id!=null){
			map.put("id", id);
			map.put("createTime", new Date());
			updateLookHistory(map);
		}else{
			map.put("createTime", new Date());
			insertLookHistory(map);
		}
		
	}

	@Override
	public List<String> getAdminPhones() {
		return dao.getAdminPhones();
	}

	@Override
	public UserPo selectUser(String openId, int i) {
		Map<String, Object> map=new HashMap<>();
		map.put("openId", openId);
		map.put("type", i);
		return dao.selectUser(map);
	}

}