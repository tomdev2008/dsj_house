package com.dsj.modules.evaluate.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.evaluate.service.AgentDailyEvaluateService;
import com.dsj.modules.evaluate.dao.AgentDailyEvaluateDao;
import com.dsj.modules.evaluate.dao.AgentEvaluateDao;
import com.dsj.modules.evaluate.dao.AgentStandardDao;
import com.dsj.modules.evaluate.po.AgentDailyEvaluatePo;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;
import com.dsj.modules.evaluate.po.AgentStandardPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:54:23.
 * @版本: 1.0 .
 */
@Service
public class AgentDailyEvaluateServiceImpl  extends BaseServiceImpl<AgentDailyEvaluateDao,AgentDailyEvaluatePo> implements AgentDailyEvaluateService {
	
	private final Logger LOGGER 
			= LoggerFactory.getLogger(AgentDailyEvaluateServiceImpl.class);
	
	@Autowired
	private AgentStandardDao agentStandardDao;
	
	@Autowired
	private AgentEvaluateDao agentEvaluateDao;
	
	/**
	 * 计算经纪人日评价  addDailyEvaluate: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param paragraphNo 评价项目   服务态度: 79401  专业水平 ：79402
	 * @param itemMark 评价标识   1：好    2：中   3：差
	 * @param date 评价日期      eg：2017-09-01
	 * @param personId 操作人
	 * @return 
	 */
	public void addDailyEvaluate(Long agentCode, String agentName, 
			Long cityCode, String cityName, int paragraphNo, int itemMark, 
			String date, Integer personId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paragraphNo", paragraphNo);
		if (itemMark == 1) {
			paramMap.put("item", "好");
		} else if (itemMark == 2) {
			paramMap.put("item", "中");
		} else if (itemMark == 3) {
			paramMap.put("item", "差");
		}
		AgentStandardPo agentStandard = agentStandardDao.getBy(paramMap);
		if (agentStandard != null) {
			paramMap.clear();
			paramMap.put("agentId", agentCode);
			paramMap.put("itemId", agentStandard.getId());
			paramMap.put("updateDate", date);
			AgentDailyEvaluatePo agentDailyEvaluatePo = dao.getBy(paramMap);
			if (agentDailyEvaluatePo == null && agentStandard.getCount() > 0) {
				agentDailyEvaluatePo = new AgentDailyEvaluatePo();
				agentDailyEvaluatePo.setAgentId(agentCode);
				agentDailyEvaluatePo.setAgentName(agentName);
				agentDailyEvaluatePo.setItemId(agentStandard.getId());
				agentDailyEvaluatePo.setItemName(agentStandard.getItem());
				agentDailyEvaluatePo.setCityName(cityName);
				agentDailyEvaluatePo.setCityCode(cityCode);
				agentDailyEvaluatePo.setCount(1);
				agentDailyEvaluatePo.setUpdateDate(date);
				agentDailyEvaluatePo.setCreatePerson(personId);
				agentDailyEvaluatePo.setCreateTime(new Date());
				agentDailyEvaluatePo.setUpdatePerson(personId);
				agentDailyEvaluatePo.setUpdateTime(new Date());
				dao.insertDynamic(agentDailyEvaluatePo);
				addAgentEvaluate(agentCode, agentName, cityCode, cityName, 
						itemMark, personId, agentStandard);
			} else if (agentDailyEvaluatePo.getCount() 
					< agentStandard.getCount()) {
				agentDailyEvaluatePo.setCityCode(cityCode);
				agentDailyEvaluatePo.setCityName(cityName);
				agentDailyEvaluatePo
					.setCount(agentDailyEvaluatePo.getCount() + 1);
				agentDailyEvaluatePo.setUpdatePerson(personId);
				agentDailyEvaluatePo.setUpdateTime(new Date());
				dao.updateDynamic(agentDailyEvaluatePo);
				addAgentEvaluate(agentCode, agentName, cityCode, cityName, 
						itemMark, personId, agentStandard);
			}
		}
	}

	/**
	 * 计算经纪人总评价  addAgentEvaluate: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param agentStandard 评价项
	 * @param itemMark 评价标识   1：好    2：中   3：差
	 * @param personId 操作人
	 * @return 
	 */
	private void addAgentEvaluate(Long agentCode, String agentName,
			Long cityCode, String cityName, int itemMark, Integer personId, 
			AgentStandardPo agentStandard) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("agentId", agentCode);
		paramMap.put("paragraphNo", agentStandard.getParagraphNo());
		AgentEvaluatePo agentEvaluatePo = agentEvaluateDao.getBy(paramMap);
		if (agentEvaluatePo == null) {
			agentEvaluatePo = new AgentEvaluatePo();
			agentEvaluatePo.setAgentId(agentCode);
			agentEvaluatePo.setAgentName(agentName);
			agentEvaluatePo.setCityName(cityName);
			agentEvaluatePo.setCityCode(cityCode);
			agentEvaluatePo.setParagraphNo(agentStandard.getParagraphNo());
			agentEvaluatePo.setParagraph(agentStandard.getParagraph());
			agentEvaluatePo.setHighCount(0);
			agentEvaluatePo.setMidCount(0);
			agentEvaluatePo.setBadCount(0);
			if (itemMark == 1) {
				agentEvaluatePo.setHighCount(1);
			} else if (itemMark == 2) {
				agentEvaluatePo.setMidCount(1);
			} else if (itemMark == 3) {
				agentEvaluatePo.setTotalCount(1);
			}
			agentEvaluatePo.setTotalCount(1);
			agentEvaluatePo.setCreatePerson(personId);
			agentEvaluatePo.setCreateTime(new Date());
			agentEvaluatePo.setUpdatePerson(personId);
			agentEvaluatePo.setUpdateTime(new Date());
			agentEvaluateDao.insertDynamic(agentEvaluatePo);
		} else {
			agentEvaluatePo.setCityCode(cityCode);
			agentEvaluatePo.setCityName(cityName);
			if (itemMark == 1 && agentEvaluatePo.getHighCount() 
					< agentStandard.getCount()) {
				agentEvaluatePo
					.setHighCount(agentEvaluatePo.getHighCount() + 1);
				agentEvaluatePo
					.setTotalCount(agentEvaluatePo.getTotalCount() + 1);
			} else if (itemMark == 2 && agentEvaluatePo.getMidCount() 
					< agentStandard.getCount()) {
				agentEvaluatePo
					.setMidCount(agentEvaluatePo.getMidCount() + 1);
				agentEvaluatePo
					.setTotalCount(agentEvaluatePo.getTotalCount() + 1);
			} else  if (itemMark == 3 && agentEvaluatePo.getBadCount() 
					< agentStandard.getCount()) {
				agentEvaluatePo
					.setBadCount(agentEvaluatePo.getBadCount() + 1);
				agentEvaluatePo
					.setTotalCount(agentEvaluatePo.getTotalCount() + 1);
			}
			agentEvaluatePo.setUpdatePerson(personId);
			agentEvaluatePo.setUpdateTime(new Date());
			agentEvaluateDao.updateDynamic(agentEvaluatePo);
		}
	}
	
}