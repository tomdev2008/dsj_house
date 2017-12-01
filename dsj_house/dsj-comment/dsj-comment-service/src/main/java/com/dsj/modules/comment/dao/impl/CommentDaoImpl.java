package com.dsj.modules.comment.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.comment.dao.CommentDao;
import com.dsj.modules.comment.po.ClickCountPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.vo.CommentVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 13:39:32.
 * @版本: 1.0 .
 */
@Repository
public class CommentDaoImpl extends BaseDaoImpl<CommentPo> implements CommentDao{

	@Override
	public List<CommentPo> getCommentsByObjectId(Map<String, Object> map) {
		
		return sessionTemplate.selectList(getStatement("getCommentsByUserId"), map);
	}

	@Override
	public void deleteByIds(Map<String, Object> map) {
		sessionTemplate.update(getStatement("deleteByIds"), map);
		
	}


	@Override
	public Long getClickCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getClickCount"), map);
	}

	@Override
	public void saveClickCountPo(Map<String, Object> map) {
		sessionTemplate.insert(getStatement("saveClickCountPo"),map);
	}

	@Override
	public CommentVo getVoById(long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}

	@Override
	public CommentVo getAgentCommentById(Long id) {
		return sessionTemplate.selectOne(getStatement("getAgentCommentById"), id);
	}

	@Override
	public List<CommentVo> getUserComment(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getUserComment"), map);
	}

	@Override
	public long getUserCommentCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getUserCommentCount"), map);
	}

	@Override
	public Long getCountByHT(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getCountByHT"),map);
	}

	
}