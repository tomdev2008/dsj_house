package com.dsj.modules.comment.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.vo.AgentNewsVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 12:08:06.
 * @版本: 1.0 .
 */
public interface AgentNewsService extends BaseService<AgentNewsPo>{

	void insertAgentNews(AgentNewsPo agentNews);
	
	List<AgentNewsPo> getNewsByUserId(Map<String,Object> map);
	
	void updateRemoveStick(Map<String,Object> map);
	
	void updateAddStick(Map<String,Object> map);

	PageBean listAgentNewsPage(PageParam pageParam, Map<String, Object> requestMap);
	
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	void deleteMany(String ids);
	
	AgentNewsVo getVoById(long id);
}