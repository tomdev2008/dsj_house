package com.dsj.modules.evaluate.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:31:17.
 * @版本: 1.0 .
 */
public interface AgentEvaluateService extends BaseService<AgentEvaluatePo>{

	/**
	 * 分页条件查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	public PageBean listPageByParams(PageParam pageParam, 
			Map<String, Object> paramMap);
	
	Map<String,Object> getRankAndScore(int agentId);
}