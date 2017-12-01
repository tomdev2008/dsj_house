package com.dsj.modules.comment.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.comment.po.ClickCountPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.vo.CommentVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:39:32.
 * @版本: 1.0 .
 */
public interface CommentDao extends BaseDao<CommentPo> {
	List<CommentPo> getCommentsByObjectId(Map<String, Object> map);

	
	void deleteByIds(Map<String,Object> map);


	Long getClickCount(Map<String, Object> map);

	void saveClickCountPo(Map<String, Object> map);
	
	CommentVo getVoById(long id);


	CommentVo getAgentCommentById(Long id);
	
	List<CommentVo> getUserComment(Map<String, Object> map);
	
	long getUserCommentCount(Map<String, Object> map);


	Long getCountByHT(Map<String, Object> map);
}