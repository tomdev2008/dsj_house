package com.dsj.data.web.system.feedback;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.dsj.modules.comment.po.FeedbackPo;
import com.dsj.modules.comment.service.FeedbackService;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;

/**
 * 用户反馈
 */
@Controller
@RequestMapping("back/**/system/feedback")
public class FeedbackController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("add")
	public String toFeedbackList(Model model) {
		return "system/feedback/feedback_list";
	}
	
	@RequestMapping("checkFeedback")
	public String toCheckFeedback(Model model, Long id) {
		FeedbackPo po = feedbackService.getById(id);
		model.addAttribute("po", po);
		return "system/feedback/feedback_check";
	}
	
	@RequestMapping("pageList")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = feedbackService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("用户反馈查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	} 
	
}
