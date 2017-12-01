package com.dsj.data.web.agent;

import java.util.Date;
import java.util.HashMap;
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
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.service.AgentNewsService;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.evaluate.service.AgentDailyScoreService;
import com.dsj.modules.evaluate.vo.AgentInfoVo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;


@Controller
@RequestMapping(value = "back/**/agentNews")
public class AgentNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsController.class);
	
	@Autowired
	private AgentNewsService agentNewsService;
	
	@Autowired
	private AgentService agentService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private AgentDailyScoreService agentDailyScoreService;
	
	int pageSize = 5;
	
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		
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
		//点赞统计
		if (ShiroUtils.getSessionUser()!=null) {
			requestMap.put("clickUser", ShiroUtils.getSessionUser().getId());
		}
		if (aoData.contains("industry")) {
			requestMap.put("industry", "industry");
		}else {
			requestMap.put("createUser", ShiroUtils.getSessionUser().getId());
		}
		PageBean page = null;
		try {
			page = agentNewsService.listAgentNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("getNewsDetail")
	@ResponseBody
	public PageDateTable<?> getNewsDetail(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
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
		//点赞统计
		requestMap.put("clickUser", ShiroUtils.getSessionUser().getId());
		PageBean page = null;
		try {
			page = agentNewsService.listAgentNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 发经纪人动态
	 * @return
	 */
	@RequestMapping("addAgentNews")
	@ResponseBody
    public AjaxResultVo addAgentNews(AgentNewsPo agentNews) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentNews.setCreateTime(new Date());
			agentNews.setCommentNum(AgentNewsEnum.COMMENT_INTI_NUM.getCode());
			agentNews.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
			agentNews.setLikeNum(AgentNewsEnum.LIKE_INIT_NUM.getCode());
			agentNews.setStick(AgentNewsEnum.NOT_STICK.getCode());
			agentNews.setNegativeNum(AgentNewsEnum.NAGETIVA_INIT_NUM.getCode());
			agentNews.setDelFlag(DeleteStatusEnum.NDEL.getValue());
			try {
				//评价体系
				AgentVo vo = agentService.getScoreVoById(ShiroUtils.getSessionUser().getId());
				agentDailyScoreService.addDailyBusinessScore
					(vo.getAgentCode().longValue(), vo.getName(), 
						Long.parseLong(vo.getCity()), vo.getCityName(), 79305, DateUtils.formatDate(new Date()), null);
			} catch (Exception e) {
				LOGGER.error("新增评价体系异常" ,e);
			}
			agentNewsService.saveDynamic(agentNews);
			result.setStatusCode(StatusCode.SUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("新增经纪人动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 
	 * @param id 
	 * @param type  经纪人动态点赞 1   评论赞 2 
	 * @param flag 点赞类型
	 * @return
	 */
	@RequestMapping("like")
	@ResponseBody
    public AjaxResultVo like(Long id ,Integer type , Integer flag) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			//是否已点评过
			long num = commentService.getClickCount
					(type,id,ShiroUtils.getSessionUser().getId().intValue());
			if (num>0) {
				result.setStatusCode(StatusCode.SERVER_ERROR);
				result.setData("您已点评过");
				return result;
			}else {
				commentService.saveClickCountPo
					(id,type,flag,ShiroUtils.getSessionUser().getId().intValue());
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
				c.setLikeNum(c.getLikeNum()+1);
				commentService.updateDynamic(c);
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

	//置顶动态
	@RequestMapping("set_stick")
	@ResponseBody
    public AjaxResultVo stick(long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stick", AgentNewsEnum.NOT_STICK.getCode());
			map.put("createUser", ShiroUtils.getSessionUser().getId());
			agentNewsService.updateRemoveStick(map);
			map.put("id",id);
			map.put("stick", AgentNewsEnum.STICK.getCode());
			agentNewsService.updateAddStick(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//取消置顶动态
	@RequestMapping("set_un_stick")
	@ResponseBody
    public AjaxResultVo setUnStick(long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",id);
			map.put("stick", AgentNewsEnum.NOT_STICK.getCode());
			agentNewsService.updateAddStick(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

}
