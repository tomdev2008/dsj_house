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
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.service.AgentNewsService;


@Controller
@RequestMapping(value = "agentNews")
public class AgentNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsController.class);
	
	@Autowired
	private AgentNewsService agentNewsService;
	
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(Long userId ,Integer pageFirst ,Integer pageSize,
			Long newsId ,HttpServletRequest request ) {
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		
		PageParam pageParam = new PageParam( pageFirst , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		if(userId!=null) requestMap.put("createUser", userId);
		if(newsId!=null) requestMap.put("id", newsId);
		
		//点赞统计
		if (UserUtil.getCurrentUser(request)!=null) {
			requestMap.put("clickUser", UserUtil.getCurrentUser(request).getId());
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
	
	@RequestMapping("detail")
    public String detail(Model model, Long id) { 
		model.addAttribute("newsId", id);
		return "agent/agent_news_detail";
	}
	
	/**
	 * 发经纪人动态
	 * @return
	 */
	@RequestMapping("addAgentNews")
	@ResponseBody
    public AjaxResultVo addAgentNews(@RequestBody AgentNewsPo agentNews ,HttpServletRequest request ) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentNews.setCreateUser(UserUtil.getCurrentUser(request).getId().intValue());
			agentNews.setCreateTime(new Date());
			agentNews.setCommentNum(AgentNewsEnum.COMMENT_INTI_NUM.getCode());
			agentNews.setLikeNum(AgentNewsEnum.LIKE_INIT_NUM.getCode());
			agentNews.setStick(AgentNewsEnum.NOT_STICK.getCode());
			agentNews.setNegativeNum(AgentNewsEnum.NAGETIVA_INIT_NUM.getCode());
			agentNews.setDelFlag(DeleteStatusEnum.NDEL.getValue());
			
			agentNewsService.saveDynamic(agentNews);
			result.setStatusCode(StatusCode.SUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("新增经纪人动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
}
