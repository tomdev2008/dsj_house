package com.dsj.data.wap.agent;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.service.AgentNewsService;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.system.enums.UserType;


@Controller
@RequestMapping(value = "comment")
public class CommentController {
	private final Logger LOGGER = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private AgentNewsService agentNewsService;

	/**
	 * 获取楼盘点评 
	 * objType 1：经纪人动态评论2：楼盘动态评论，3：经纪人对楼盘点评；4：普通用户点评'
	 * commentId 查看点评详情的id 详情页的那条id
	 */
	@RequestMapping("getComment")
	@ResponseBody
    public PageDateTable<?> getComment(Integer pageFirst,Integer pageSize,Long newHouseId, 
    		Long objectId, Integer objectType, Long commentId ,HttpServletRequest request) { 
		Map<String, Object> requestMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam( pageFirst , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("objectType", objectType);
		requestMap.put("pc", "yes");//时间倒叙
		if (newHouseId !=null ) requestMap.put("houseId", newHouseId);
		if (objectId !=null ) requestMap.put("objectId", objectId);
		if (commentId !=null ) requestMap.put("id", commentId);
		//点赞统计
		if (UserUtil.getCurrentUser(request)!=null) {
			requestMap.put("clickUser", UserUtil.getCurrentUser(request).getId());
		}
		PageBean page = null;
		try {
			page = commentService.listCommentPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("楼盘点评查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("addComment")
	@ResponseBody
    public AjaxResultVo addComment(@RequestBody CommentPo comment,HttpServletRequest request) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			//改变动态或楼盘评论数量
			if (CommentEnum.COMMENT_AGENT.getCode() == comment.getObjectType()) {
				AgentNewsPo po = agentNewsService.getById(comment.getObjectId());
				po.setCommentNum(po.getCommentNum()+1);
				agentNewsService.updateDynamic(po);
			}else if(CommentEnum.COMMENT_HOUSE.getCode() == comment.getObjectType()){
				CommentPo c = commentService.getById(comment.getObjectId());
				c.setCommentNum(c.getCommentNum()+1);
				commentService.updateDynamic(c);
			}else{
				if (UserUtil.getCurrentUser(request).getUserType() == UserType.AGENT.getValue()) {
					comment.setObjectType(CommentEnum.COMMENT__HOUSE_REMARK.getCode());
				}else {
					comment.setObjectType(CommentEnum.GENERAL_HOUSE_REMARK.getCode());
				}
				
			}
			savePoDynamic(comment,request);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	public void savePoDynamic(CommentPo comment,HttpServletRequest request){
		comment.setLikeNum(CommentEnum.COMMENT_INTI_NUM.getCode());
		comment.setCommentNum(CommentEnum.COMMENT_INTI_NUM.getCode());
		comment.setCommentUser(UserUtil.getCurrentUser(request).getId().intValue());
		comment.setCreateTime(new Date());
		comment.setDelFlag(DeleteStatusEnum.NDEL.getValue());
		commentService.saveDynamic(comment);
	}
	
	/**
	 * 
	 * @param id 
	 * @param type  经纪人动态点赞 1   评论或点评赞 2 
	 * @param flag 点赞类型
	 * @return
	 */
	@RequestMapping("comLike")
	@ResponseBody
    public AjaxResultVo comLike(Long id ,Integer type , Integer flag,HttpServletRequest request) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			synchronized(this){
				//是否已点评过
				long num = commentService.getClickCount
						(type,id,UserUtil.getCurrentUser(request).getId().intValue());
				if (num>0) {
					result.setStatusCode(StatusCode.SERVER_ERROR);
					result.setData("您已点评过");
					return result;
				}else {
					commentService.saveClickCountPo
						(id,type,flag,UserUtil.getCurrentUser(request).getId().intValue());
				}
				if(type==CommentEnum.AGENTNEWS_LIKE.getCode()){
					//经济人动态点赞
					AgentNewsPo a = agentNewsService.getById(id);
					if(flag == CommentEnum.LIKE.getCode()){
						a.setLikeNum(a.getLikeNum()+1);
						a.setNegativeNum(a.getNegativeNum());
					}else if(flag == CommentEnum.UN_LIKE.getCode()){
						a.setNegativeNum(a.getNegativeNum()+1);
						a.setLikeNum(a.getLikeNum());
					}
					agentNewsService.updateDynamic(a);
				}else if(type==CommentEnum.COMMENT_LIKE.getCode()){
					//评论或者点评点赞
					CommentPo c = commentService.getById(id);
					if(flag == CommentEnum.LIKE.getCode()){
						c.setLikeNum(c.getLikeNum()+1);
					}else if(flag == CommentEnum.UN_LIKE.getCode()){
						c.setNegativeNum(c.getNegativeNum()+1);
					}
					commentService.updateDynamic(c);
				}
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping("detail")
    public String detail(Model model, Long id) { 
		model.addAttribute("commentId", id);
		return "newHouse/newHouse_comment_detail";
	}

}
