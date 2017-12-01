package com.dsj.modules.evaluate.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.dao.AgentInfoDao;
import com.dsj.modules.evaluate.dao.AgentStandardDao;
import com.dsj.modules.evaluate.po.AgentInfoPo;
import com.dsj.modules.evaluate.po.AgentStandardPo;
import com.dsj.modules.evaluate.service.AgentInfoService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:52:42.
 * @版本: 1.0 .
 */
@Service
public class AgentInfoServiceImpl extends BaseServiceImpl<AgentInfoDao,AgentInfoPo> implements AgentInfoService {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentInfoServiceImpl.class);

	@Autowired
	private AgentStandardDao agentStandardDao;

	/**
	 * 分页条件查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	@Override
	public PageBean listPageByParams(PageParam pageParam,
			Map<String, Object> paramMap) {
		return dao.listPageByParams(pageParam, paramMap);
	}
	
	/**
	 * 经纪人学历评分  addEducationScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param itemMark 评价标识   1：本科及以上    2：专科   3：高中以下
	 * @param personId 操作人
	 * @return 
	 */
	public void addEducationScore(Long agentCode, String agentName,
			Long cityCode, String cityName, int itemMark, Integer personId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paragraph", "学历");
		if (itemMark == 1) {
			paramMap.put("item", "本科及以上");
		} else if (itemMark == 2) {
			paramMap.put("item", "专科");
		} else if (itemMark == 3) {
			paramMap.put("item", "高中以下");
		}
		AgentStandardPo agentStandard = agentStandardDao.getBy(paramMap);
		if (agentStandard != null) {
			paramMap.clear();
			paramMap.put("agentId", agentCode);
			AgentInfoPo agentInfoPo = dao.getBy(paramMap);
			if (agentInfoPo == null) {
				agentInfoPo = new AgentInfoPo();
				agentInfoPo.setAgentId(agentCode);
				agentInfoPo.setAgentName(agentName);
				agentInfoPo.setCityName(cityName);
				agentInfoPo.setCityCode(cityCode);
				agentInfoPo.setEducationScore(agentStandard.getScore());
				agentInfoPo.setExperienceScore(0);
				agentInfoPo.setBaseScore(agentStandard.getScore());
				agentInfoPo.setBusinessScore(0);
				agentInfoPo.setTotalScore(agentStandard.getScore());
				agentInfoPo.setCreatePerson(personId);
				agentInfoPo.setCreateTime(new Date());
				agentInfoPo.setUpdatePerson(personId);
				agentInfoPo.setUpdateTime(new Date());
				dao.insertDynamic(agentInfoPo);
			} else {
				agentInfoPo.setCityCode(cityCode);
				agentInfoPo.setCityName(cityName);
				agentInfoPo.setTotalScore(agentInfoPo.getTotalScore() 
						+ agentStandard.getScore() 
						- agentInfoPo.getEducationScore());
				agentInfoPo.setEducationScore(agentStandard.getScore());
				agentInfoPo.setBaseScore(agentInfoPo.getExperienceScore() 
						+ agentStandard.getScore());
				agentInfoPo.setUpdatePerson(personId);
				agentInfoPo.setUpdateTime(new Date());
				dao.updateDynamic(agentInfoPo);
			}
		}
	}
	
	/**
	 * 经纪人从业年限评分  addExperienceScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param itemMark 评价标识   1：3年以上    2：3年以下
	 * @param personId 操作人
	 * @return 
	 */
	public void addExperienceScore(Long agentCode, String agentName,
			Long cityCode, String cityName, int itemMark, Integer personId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paragraph", "从业年限");
		if (itemMark == 1) {
			paramMap.put("item", "3年以上");
		} else if (itemMark == 2) {
			paramMap.put("item", "3年以下");
		}
		AgentStandardPo agentStandard = agentStandardDao.getBy(paramMap);
		if (agentStandard != null) {
			paramMap.clear();
			paramMap.put("agentId", agentCode);
			AgentInfoPo agentInfoPo = dao.getBy(paramMap);
			if (agentInfoPo == null) {
				agentInfoPo = new AgentInfoPo();
				agentInfoPo.setAgentId(agentCode);
				agentInfoPo.setAgentName(agentName);
				agentInfoPo.setCityName(cityName);
				agentInfoPo.setCityCode(cityCode);
				agentInfoPo.setEducationScore(0);
				agentInfoPo.setExperienceScore(agentStandard.getScore());
				agentInfoPo.setBaseScore(agentStandard.getScore());
				agentInfoPo.setBusinessScore(0);
				agentInfoPo.setTotalScore(agentStandard.getScore());
				agentInfoPo.setCreatePerson(personId);
				agentInfoPo.setCreateTime(new Date());
				agentInfoPo.setUpdatePerson(personId);
				agentInfoPo.setUpdateTime(new Date());
				dao.insertDynamic(agentInfoPo);
			} else {
				agentInfoPo.setCityCode(cityCode);
				agentInfoPo.setCityName(cityName);
				agentInfoPo.setTotalScore(agentInfoPo.getTotalScore() 
						+ agentStandard.getScore() 
						- agentInfoPo.getExperienceScore());
				agentInfoPo.setExperienceScore(agentStandard.getScore());
				agentInfoPo.setBaseScore(agentInfoPo.getEducationScore() 
						+ agentStandard.getScore());
				agentInfoPo.setUpdatePerson(personId);
				agentInfoPo.setUpdateTime(new Date());
				dao.updateDynamic(agentInfoPo);
			}
		}
	}
	
}