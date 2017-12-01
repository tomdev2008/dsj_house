package com.dsj.modules.mobile400.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.mobile400.po.AgentMobilePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-15 15:08:49.
 * @版本: 1.0 .
 */
public interface AgentMobileService extends BaseService<AgentMobilePo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	PageBean newPageList(PageParam pageParam, Map<String, Object> requestMap);

	PageBean agentYesterday(PageParam pageParam, Map<String, Object> requestMap);

	PageBean agentTotal(PageParam pageParam, Map<String, Object> requestMap);

	long saveToLeadAgent();


	
}