package com.dsj.modules.comment.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.comment.dao.AgentNewsDao;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.vo.AgentNewsVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 12:08:06.
 * @版本: 1.0 .
 */
@Repository
public class AgentNewsDaoImpl extends BaseDaoImpl<AgentNewsPo> implements AgentNewsDao{

	@Override
	public List<AgentNewsPo> getNewsByUserId(Map<String,Object> map) {
		return sessionTemplate.selectList(getStatement("getNewsByUserId"), map);
	}

	@Override
	public void updateAddStick(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateAddStick"), map);
		
	}

	@Override
	public void updateRemoveStick(Map<String,Object> map) {
		sessionTemplate.update(getStatement("updateRemoveStick"), map);
	}

	@Override
	public void deleteByIds(Map<String, Object> map) {
		sessionTemplate.update(getStatement("deleteAgentNewsByIds"), map);
		
	}

	@Override
	public AgentNewsVo getVoById(long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}
}