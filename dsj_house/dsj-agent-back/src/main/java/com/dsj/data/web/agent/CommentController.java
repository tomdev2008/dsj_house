package com.dsj.data.web.agent;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.service.AgentNewsService;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.vo.CommentVo;


@Controller
@RequestMapping(value = "back/**/comment")
public class CommentController {
	private final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private AgentNewsService agentNewsService;

	/**
	 * 获取评论 
	 * @param objectType 评论类型
	 * @param objectId	评论id
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("getComment")
	@ResponseBody
    public PageDateTable<?> getComment(
    		@RequestParam(value = "aoData", defaultValue = "") String aoData) { 
		int pageSize = 3;
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		if(requestMap.get("pageSize")!=null){
			pageSize = Integer.parseInt((String) requestMap.get("pageSize"));
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("pc", "back");
		//点赞统计
		if (ShiroUtils.getSessionUser()!=null) {
			requestMap.put("clickUser", ShiroUtils.getSessionUser().getId());
		}
		PageBean page = null;
		try {
			page = commentService.listCommentPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("addComment")
	@ResponseBody
    public AjaxResultVo addComment(CommentPo comment) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			//改变动态或楼盘评论数量
			if (CommentEnum.COMMENT_AGENT.getCode() == comment.getObjectType()) {
				AgentNewsPo po = agentNewsService.getById(comment.getObjectId());
				po.setCommentNum(po.getCommentNum()+1);
				agentNewsService.updateDynamic(po);
			}
			savePoDynamic(comment);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	public void savePoDynamic(CommentPo comment){
		comment.setLikeNum(CommentEnum.COMMENT_INTI_NUM.getCode());
		comment.setCommentNum(CommentEnum.COMMENT_INTI_NUM.getCode());
		comment.setCommentUser(ShiroUtils.getSessionUser().getId().intValue());
		comment.setCreateTime(new Date());
		comment.setDelFlag(DeleteStatusEnum.NDEL.getValue());
		commentService.saveDynamic(comment);
	}
	
	//经纪人--我的点评
	@RequestMapping("getAgentComment")
	@ResponseBody
    public PageDateTable<?> getAgentComment(
    		@RequestParam(value = "aoData", defaultValue = "") String aoData) { 
		int pageSize = 3;
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("commentUser", ShiroUtils.getSessionUser().getId());
		requestMap.put("objectType", CommentEnum.COMMENT__HOUSE_REMARK.getCode());
		PageBean page = null;
		try {
			page = commentService.listAgentCommentPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("getAgentCommentById")
	@ResponseBody
    public AjaxResultVo getAgentCommentById(Long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			CommentVo vo = commentService.getAgentCommentById(id);
			result.setData(vo);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

}
