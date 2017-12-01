package com.dsj.modules.system.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.AgentStatisticsPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-17 11:40:32.
 * @版本: 1.0 .
 */
public interface AgentStatisticsService extends BaseService<AgentStatisticsPo>{
	/*初始化数据Start*/
	List<Map<String,Object>> getHouseNews();
	
	List<Map<String,Object>> getAgentNews();
	
	List<Map<String,Object>> getAgentNewsReply();
	
	List<Map<String,Object>> getAgentNewsLike();
	
	List<Map<String,Object>> getHouseRemark();
	
	List<Map<String,Object>> getHouseRemarkReply();
	
	List<Map<String,Object>> getHouseRemarkLike();
	
	List<Map<String,Object>> getAgentGrade();
	
	/*初始化数据end*/
	
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	/*昨日数据Start*/
	List<Map<String,Object>> getHouseNewsYesterday();
	
	List<Map<String,Object>> getAgentNewsYesterday();
	
	List<Map<String,Object>> getAgentNewsReplyYesterday();
	
	List<Map<String,Object>> getAgentNewsLikeYesterday();
	
	List<Map<String,Object>> getHouseRemarkYesterday();
	
	List<Map<String,Object>> getHouseRemarkReplyYesterday();
	
	List<Map<String,Object>> getHouseRemarkLikeYesterday();
	
	List<Map<String,Object>> getAgentGradeYesterday();
	
	/*昨日数据end*/

	
}