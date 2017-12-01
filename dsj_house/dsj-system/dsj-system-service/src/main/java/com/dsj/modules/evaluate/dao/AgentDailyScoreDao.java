package com.dsj.modules.evaluate.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.evaluate.po.AgentDailyScorePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:53:42.
 * @版本: 1.0 .
 */
public interface AgentDailyScoreDao extends BaseDao<AgentDailyScorePo> {
	
	public List<AgentDailyScorePo> listStatisticsBy(Map<String, Object> 
		paramMap);
	
}