package com.dsj.modules.evaluate.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.evaluate.dao.AgentDailyScoreDao;
import com.dsj.modules.evaluate.po.AgentDailyScorePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:53:42.
 * @版本: 1.0 .
 */
@Repository
public class AgentDailyScoreDaoImpl extends BaseDaoImpl<AgentDailyScorePo> implements AgentDailyScoreDao{
	
	@Override
	public List<AgentDailyScorePo> listStatisticsBy(Map<String, Object> 
		paramMap) {
		List<AgentDailyScorePo> list = sessionTemplate
				.selectList(getStatement("listStatisticsBy"), paramMap);
		return list;
	}
	
}