package com.dsj.data.web.agent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.comment.service.AgentNewsService;


@Controller
@RequestMapping(value = "front/agentNews")
public class AgentNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsController.class);
	
	@Autowired
	private AgentNewsService agentNewsService;
	
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(Long userId ,Integer pageFirst ,Integer pageSize,
			Long newsId,HttpServletRequest request ) {
		
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
    public String detail(Model model, Long id , Long userId) { 
		model.addAttribute("newsId", id);
		model.addAttribute("userId", userId);
		return "agent/agent_news_detail";
	}
}
