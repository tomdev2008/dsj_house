package com.dsj.modules.comment.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.comment.po.ClickCountPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.vo.CommentVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:39:32.
 * @版本: 1.0 .
 */
public interface CommentService extends BaseService<CommentPo>{

	List<CommentPo> getCommentsByObjectId(Map<String,Object> map);

	PageBean listCommentPage(PageParam pageParam, Map<String, Object> requestMap);

	Long getClickCount(Integer type, long id, int intValue);

	void saveClickCountPo(Long id, Integer type, Integer flag, int intValue);

	

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	

	void deleteMany(String ids);
	
	CommentVo getVoById(long id);

	PageBean listAgentCommentPage(PageParam pageParam, Map<String, Object> requestMap);

	CommentVo getAgentCommentById(Long id);
	
	List<CommentVo> getUserComment(Map<String,Object> map);
	
	long getUserCommentCount(Map<String,Object> map);

	Long getCountByHT(Map<String, Object> requestMap);
}