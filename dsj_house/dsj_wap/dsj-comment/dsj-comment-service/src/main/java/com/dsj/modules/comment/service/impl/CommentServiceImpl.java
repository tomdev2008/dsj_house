package com.dsj.modules.comment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;

import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.vo.CommentVo;
import com.dsj.modules.comment.dao.CommentDao;
import com.dsj.modules.comment.po.ClickCountPo;
import com.dsj.modules.comment.po.CommentPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 13:39:32.
 * @版本: 1.0 .
 */
@Service
public class CommentServiceImpl  extends BaseServiceImpl<CommentDao,CommentPo> implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	@Override
	public List<CommentPo> getCommentsByObjectId(Map<String, Object> map) {
		
		return commentDao.getCommentsByObjectId(map);
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");

	}
	
	@Override
	public PageBean listAgentCommentPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listAgentCommentCount", "listAgentCommentPage");
	}
	
	@Override
	public void deleteMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer delFlag = DeleteStatusEnum.DEL.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("delFlag", delFlag);
 			commentDao.deleteByIds(map);
			
				
		}
		
	}
	@Override
	public PageBean listCommentPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listCommentCount","listCommentPage");
	}
	@Override
	public Long getClickCount(Integer type, long id, int userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("id", id);
		map.put("userId", userId);
		return dao.getClickCount(map);
	}
	@Override
	public void saveClickCountPo(Long id, Integer type, Integer flag, int userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("objId", id);
		map.put("objType", type);
		map.put("type", flag);
		map.put("createPreson", userId);
		map.put("createTime", new Date());
		dao.saveClickCountPo(map);
	}

	@Override
	public CommentVo getVoById(long id) {
		return commentDao.getVoById(id);
	}

	@Override
	public CommentVo getAgentCommentById(Long id) {
		return dao.getAgentCommentById(id);
	}

	@Override
	public List<CommentVo> getUserComment(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commentDao.getUserComment(map);
	}

	@Override
	public long getUserCommentCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return commentDao.getUserCommentCount(map);
	}

	@Override
	public Long getCountByHT(Map<String, Object> map) {
		return commentDao.getCountByHT(map);
	}

}