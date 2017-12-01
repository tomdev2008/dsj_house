package com.dsj.modules.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.AgentStatisticsDao;
import com.dsj.modules.system.po.AgentStatisticsPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-17 11:40:32.
 * @版本: 1.0 .
 */
@Repository
public class AgentStatisticsDaoImpl extends BaseDaoImpl<AgentStatisticsPo> implements AgentStatisticsDao{

	@Override
	public List<Map<String, Object>> getHouseNews() {
		return sessionTemplate.selectList(getStatement("getHouseNews"));
		 
	}

	@Override
	public List<Map<String, Object>> getAgentNews() {
		return sessionTemplate.selectList(getStatement("getAgentNews"));
	}

	@Override
	public List<Map<String, Object>> getAgentNewsReply() {
		return sessionTemplate.selectList(getStatement("getAgentNewsReply"));
	}

	@Override
	public List<Map<String, Object>> getAgentNewsLike() {
		return sessionTemplate.selectList(getStatement("getAgentNewsLike"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemark() {
		return sessionTemplate.selectList(getStatement("getHouseRemark"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkReply() {
		return sessionTemplate.selectList(getStatement("getHouseRemarkReply"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkLike() {
		return sessionTemplate.selectList(getStatement("getHouseRemarkLike"));
	}

	@Override
	public List<Map<String, Object>> getAgentGrade() {
		return sessionTemplate.selectList(getStatement("getAgentGrade"));
	}
//////////////////////////////////
	@Override
	public List<Map<String, Object>> getHouseNewsYesterday() {
		return sessionTemplate.selectList(getStatement("getHouseNewsYesterday"));
		 
	}

	@Override
	public List<Map<String, Object>> getAgentNewsYesterday() {
		return sessionTemplate.selectList(getStatement("getAgentNewsYesterday"));
	}

	@Override
	public List<Map<String, Object>> getAgentNewsReplyYesterday() {
		return sessionTemplate.selectList(getStatement("getAgentNewsReplyYesterday"));
	}

	@Override
	public List<Map<String, Object>> getAgentNewsLikeYesterday() {
		return sessionTemplate.selectList(getStatement("getAgentNewsLikeYesterday"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkYesterday() {
		return sessionTemplate.selectList(getStatement("getHouseRemarkYesterday"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkReplyYesterday() {
		return sessionTemplate.selectList(getStatement("getHouseRemarkReplyYesterday"));
	}

	@Override
	public List<Map<String, Object>> getHouseRemarkLikeYesterday() {
		return sessionTemplate.selectList(getStatement("getHouseRemarkLikeYesterday"));
	}

	@Override
	public List<Map<String, Object>> getAgentGradeYesterday() {
		return sessionTemplate.selectList(getStatement("getAgentGradeYesterday"));
	}
	
}