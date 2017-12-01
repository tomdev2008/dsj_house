package com.dsj.modules.evaluate.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.evaluate.dao.AgentDailyScoreDao;
import com.dsj.modules.evaluate.dao.AgentInfoDao;
import com.dsj.modules.evaluate.dao.AgentStandardDao;
import com.dsj.modules.evaluate.po.AgentDailyEvaluatePo;
import com.dsj.modules.evaluate.po.AgentDailyScorePo;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;
import com.dsj.modules.evaluate.po.AgentInfoPo;
import com.dsj.modules.evaluate.po.AgentStandardPo;
import com.dsj.modules.evaluate.service.AgentDailyScoreService;
import com.dsj.modules.evaluate.vo.AgentInfoVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:53:42.
 * @版本: 1.0 .
 */
@Service
public class AgentDailyScoreServiceImpl  extends BaseServiceImpl<AgentDailyScoreDao,AgentDailyScorePo> implements AgentDailyScoreService {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentDailyScoreServiceImpl.class);

	@Autowired
	private AgentStandardDao agentStandardDao;
	
	@Autowired
	private AgentDailyScoreDao agentDailyScoreDao;
	
	@Autowired
	private AgentInfoDao agentInfoDao;
	
	/**
	 * 计算房源日得分  addDailyHouseScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param itemMark 评价标识   1：房源信息    2：业主姓名电话   3：实堪图片  4：钥匙
	 * @param date 评价日期      eg：2017-09-01
	 * @param personId 操作人
	 * @return 
	 */
	public void addDailyHouseScore(Long agentCode, String agentName, 
			Long cityCode, String cityName, int itemMark, String date, 
			Integer personId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paragraphNo", 79301);
		if (itemMark == 1) {
			paramMap.put("item", "房源信息");
		} else if (itemMark == 2) {
			paramMap.put("item", "业主姓名电话");
		} else if (itemMark == 3) {
			paramMap.put("item", "实堪图片");
		} else if (itemMark == 4) {
			paramMap.put("item", "钥匙");
		}
		AgentStandardPo agentStandard = agentStandardDao.getBy(paramMap);
		if (agentStandard != null) {
			paramMap.clear();
			paramMap.put("agentId", agentCode);
			paramMap.put("itemId", agentStandard.getId());
			paramMap.put("updateDate", date);
			AgentDailyScorePo agentDailyScorePo = dao.getBy(paramMap);
			if (agentDailyScorePo == null && agentStandard.getCount() > 0) {
				agentDailyScorePo = new AgentDailyScorePo();
				agentDailyScorePo.setAgentId(agentCode);
				agentDailyScorePo.setAgentName(agentName);
				agentDailyScorePo.setItemId(agentStandard.getId());
				agentDailyScorePo.setItemName(agentStandard.getItem());
				agentDailyScorePo.setCityName(cityName);
				agentDailyScorePo.setCityCode(cityCode);
				agentDailyScorePo.setScore(agentStandard.getScore());
				agentDailyScorePo.setCount(1);
				agentDailyScorePo.setUpdateDate(date);
				agentDailyScorePo.setCreatePerson(personId);
				agentDailyScorePo.setCreateTime(new Date());
				agentDailyScorePo.setUpdatePerson(personId);
				agentDailyScorePo.setUpdateTime(new Date());
				dao.insertDynamic(agentDailyScorePo);
				addAgentScore(agentCode, agentName, cityCode, cityName, 
						personId, agentStandard);
			} else if (agentDailyScorePo.getCount() 
					< agentStandard.getCount()) {
				agentDailyScorePo.setCityCode(cityCode);
				agentDailyScorePo.setCityName(cityName);
				agentDailyScorePo.setScore(agentDailyScorePo.getScore() 
						+ agentStandard.getScore());
				agentDailyScorePo.setCount(agentDailyScorePo.getCount() + 1);
				agentDailyScorePo.setUpdatePerson(personId);
				agentDailyScorePo.setUpdateTime(new Date());
				dao.updateDynamic(agentDailyScorePo);
				addAgentScore(agentCode, agentName, cityCode, cityName, 
						personId, agentStandard);
			}
		}
	}
	
	/**
	 * 计算经纪人业务日得分  addDailyBusinessScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param paragraphNo 评价项目   带看量: 79302  成交数 ：79303 培训考试: 79304  活跃度 ：79305
	 * @param date 评价日期      eg：2017-09-01
	 * @param personId 操作人
	 * @return 
	 */
	public void addDailyBusinessScore(Long agentCode, String agentName, 
			Long cityCode, String cityName, int paragraphNo, String date, 
			Integer personId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("paragraphNo", paragraphNo);
		AgentStandardPo agentStandard = agentStandardDao.getBy(paramMap);
		if (agentStandard != null) {
			paramMap.clear();
			paramMap.put("agentId", agentCode);
			paramMap.put("itemId", agentStandard.getId());
			paramMap.put("updateDate", date);
			AgentDailyScorePo agentDailyScorePo = dao.getBy(paramMap);
			if (agentDailyScorePo == null && agentStandard.getCount() > 0) {
				agentDailyScorePo = new AgentDailyScorePo();
				agentDailyScorePo.setAgentId(agentCode);
				agentDailyScorePo.setAgentName(agentName);
				agentDailyScorePo.setItemId(agentStandard.getId());
				agentDailyScorePo.setItemName(agentStandard.getItem());
				agentDailyScorePo.setCityName(cityName);
				agentDailyScorePo.setCityCode(cityCode);
				agentDailyScorePo.setScore(agentStandard.getScore());
				agentDailyScorePo.setCount(1);
				agentDailyScorePo.setUpdateDate(date);
				agentDailyScorePo.setCreatePerson(personId);
				agentDailyScorePo.setCreateTime(new Date());
				agentDailyScorePo.setUpdatePerson(personId);
				agentDailyScorePo.setUpdateTime(new Date());
				dao.insertDynamic(agentDailyScorePo);
				addAgentScore(agentCode, agentName, cityCode, cityName, 
						personId, agentStandard);
			} else if (agentDailyScorePo.getCount() 
					< agentStandard.getCount()) {
				agentDailyScorePo.setCityCode(cityCode);
				agentDailyScorePo.setCityName(cityName);
				agentDailyScorePo.setScore(agentDailyScorePo.getScore() 
						+ agentStandard.getScore());
				agentDailyScorePo.setCount(agentDailyScorePo.getCount() + 1);
				agentDailyScorePo.setUpdatePerson(personId);
				agentDailyScorePo.setUpdateTime(new Date());
				dao.updateDynamic(agentDailyScorePo);
				addAgentScore(agentCode, agentName, cityCode, cityName, 
						personId, agentStandard);
			}
		}
	}

	/**
	 * 计算经纪人总得分  addAgentScore: <br/>
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
	private void addAgentScore(Long agentCode, String agentName,
			Long cityCode, String cityName, Integer personId, 
			AgentStandardPo agentStandard) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("agentId", agentCode);
		paramMap.put("paragraphNo", agentStandard.getParagraphNo());
		AgentInfoPo agentInfoPo = agentInfoDao.getBy(paramMap);
		if (agentInfoPo == null) {
			agentInfoPo = new AgentInfoPo();
			agentInfoPo.setAgentId(agentCode);
			agentInfoPo.setAgentName(agentName);
			agentInfoPo.setCityName(cityName);
			agentInfoPo.setCityCode(cityCode);
			agentInfoPo.setEducationScore(0);
			agentInfoPo.setExperienceScore(0);
			agentInfoPo.setBaseScore(0);
			agentInfoPo.setBusinessScore(agentStandard.getScore());
			agentInfoPo.setTotalScore(agentStandard.getScore());
			agentInfoPo.setCreatePerson(personId);
			agentInfoPo.setCreateTime(new Date());
			agentInfoPo.setUpdatePerson(personId);
			agentInfoPo.setUpdateTime(new Date());
			agentInfoDao.insertDynamic(agentInfoPo);
		} else {
			agentInfoPo.setCityCode(cityCode);
			agentInfoPo.setCityName(cityName);
			agentInfoPo.setBusinessScore(agentInfoPo.getBusinessScore() 
					+ agentStandard.getScore());
			agentInfoPo.setTotalScore(agentInfoPo.getTotalScore() 
					+ agentStandard.getScore());
			agentInfoPo.setUpdatePerson(personId);
			agentInfoPo.setUpdateTime(new Date());
			agentInfoDao.updateDynamic(agentInfoPo);
		}
	}
	
	/**
	 * 统计经纪人得分  AgentDailyScorePo: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param startDate 开始日期    eg：2017-09-01
	 * @param endDate 结束日期      eg：2017-09-01
	 * @return 
	 */
	public List<AgentDailyScorePo> listStatisticsBy(Long agentCode, 
			String startDate, String endDate) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (agentCode != null)
			paramMap.put("agentId", agentCode);
		if (StringUtils.isNotEmpty(endDate))
			paramMap.put("startDate", startDate);
		if (StringUtils.isNotEmpty(endDate))
			paramMap.put("endDate", endDate);
		return agentDailyScoreDao.listStatisticsBy(paramMap);
	}
	
}