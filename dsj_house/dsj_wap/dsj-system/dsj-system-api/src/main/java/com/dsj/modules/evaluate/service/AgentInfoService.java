package com.dsj.modules.evaluate.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.evaluate.po.AgentInfoPo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:52:42.
 * @版本: 1.0 .
 */
public interface AgentInfoService extends BaseService<AgentInfoPo>{

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
			Long cityCode, String cityName, int itemMark, Integer personId);
	
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
			Long cityCode, String cityName, int itemMark, Integer personId);
	
}