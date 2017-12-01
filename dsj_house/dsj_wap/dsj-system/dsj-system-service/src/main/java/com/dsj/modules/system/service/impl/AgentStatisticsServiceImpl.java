package com.dsj.modules.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.service.AgentStatisticsService;
import com.dsj.modules.system.dao.AgentStatisticsDao;
import com.dsj.modules.system.po.AgentStatisticsPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-17 11:40:32.
 * @版本: 1.0 .
 */
@Service
public class AgentStatisticsServiceImpl  extends BaseServiceImpl<AgentStatisticsDao,AgentStatisticsPo> implements AgentStatisticsService {
	@Autowired
	private AgentStatisticsDao agentStatisticsDao;
	@Override
	public List<Map<String, Object>> getHouseNews() {
		return agentStatisticsDao.getHouseNews();
	}

	@Override
	public List<Map<String, Object>> getAgentNews() {
		return agentStatisticsDao.getAgentNews();
	}

	@Override
	public List<Map<String, Object>> getAgentNewsReply() {
		return agentStatisticsDao.getAgentNewsReply();
	}

	@Override
	public List<Map<String, Object>> getAgentNewsLike() {
		return agentStatisticsDao.getAgentNewsLike();
	}

	@Override
	public List<Map<String, Object>> getHouseRemark() {
		return agentStatisticsDao.getHouseRemark();
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkReply() {
		return agentStatisticsDao.getHouseRemarkReply();
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkLike() {
		return agentStatisticsDao.getHouseRemarkLike();
	}

	@Override
	public List<Map<String, Object>> getAgentGrade() {
		return agentStatisticsDao.getAgentGrade();
	}
	
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}
	
	@Override
	public List<Map<String, Object>> getHouseNewsYesterday() {
		return agentStatisticsDao.getHouseNewsYesterday();
	}

	@Override
	public List<Map<String, Object>> getAgentNewsYesterday() {
		return agentStatisticsDao.getAgentNewsYesterday();
	}

	@Override
	public List<Map<String, Object>> getAgentNewsReplyYesterday() {
		return agentStatisticsDao.getAgentNewsReplyYesterday();
	}

	@Override
	public List<Map<String, Object>> getAgentNewsLikeYesterday() {
		return agentStatisticsDao.getAgentNewsLikeYesterday();
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkYesterday() {
		return agentStatisticsDao.getHouseRemarkYesterday();
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkReplyYesterday() {
		return agentStatisticsDao.getHouseRemarkReplyYesterday();
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkLikeYesterday() {
		return agentStatisticsDao.getHouseRemarkLikeYesterday();
	}

	@Override
	public List<Map<String, Object>> getAgentGradeYesterday() {
		return agentStatisticsDao.getAgentGradeYesterday();
	}
	
}