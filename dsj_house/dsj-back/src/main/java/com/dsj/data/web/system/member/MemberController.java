package com.dsj.data.web.system.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.system.service.MemberService;

/**
 * 后台员工管理
 */
@Controller
@RequestMapping(value = "back/**/member")
public class MemberController {
	private final Logger LOGGER = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("memberList")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/member/member_list");
		//mav.addObject(attributeName, attributeValue);
		return mav;
	}
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> adminList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = memberService.listNewPage(pageParam, requestMap);
		
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 拉黑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("black")
	@ResponseBody
    public AjaxResultVo black(String ids) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			memberService.updateBlackMany(ids);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("拉黑异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 解除拉黑
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("removeBlack")
	@ResponseBody
    public AjaxResultVo removeBlack(String id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			
			memberService.updateRemoveBlack(id);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("解除拉黑异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
}
