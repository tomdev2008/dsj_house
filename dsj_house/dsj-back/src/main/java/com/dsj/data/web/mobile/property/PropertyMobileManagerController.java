package com.dsj.data.web.mobile.property;

import java.util.HashMap;
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
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.mobile400.enums.MobileTypeEnum;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileHistoryService;
import com.dsj.modules.mobile400.service.MobileService;

@Controller
@RequestMapping(value = "back/**/mobileManager/property")
public class PropertyMobileManagerController {
	private final Logger LOGGER = LoggerFactory.getLogger(PropertyMobileManagerController.class);
	
	@Autowired
	private MobileService mobileService;
	@Autowired
	private MobileHistoryService mobileHistoryService;
	
	@RequestMapping({ "mobile_property_list", "" })
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mobileManager/property/mobile_property_list");
		return mav;
	}
	
	@RequestMapping("mobile_property_binding_history")
	public String mobileHouseHistoryList(Model model, Long id) {
		model.addAttribute("id", id);
		return "mobileManager/property/mobile_property_binding_history";
	}
	
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("type", MobileTypeEnum.PROPERTY.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = mobileService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("400代办人管理查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("historyPage/list")
	@ResponseBody
	public PageDateTable<?> historyPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = mobileHistoryService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("绑定历史查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("binding")
	public String binding(Model model, Long id) {
		MobilePo mobilePo = mobileService.getById(id);
		model.addAttribute("mobilePo", mobilePo);
		return "mobileManager/property/mobile_property_binding";
	}

	@RequestMapping("editBinding")
	public String updateBinding(Model model, Long id) {
		MobilePo mobilePo = mobileService.getById(id);
		model.addAttribute("mobilePo", mobilePo);
		return "mobileManager/property/mobile_property_updateBinding";
	}

	@RequestMapping("saveBinding")
	@ResponseBody
	public AjaxResultVo saveBinding(MobilePo po) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			mobileService.saveBindingMobile(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setMessage("号码在别地已绑定");
			//result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号绑定异常", e);
		}
		return result;
	}

	@RequestMapping("saveUpdateBinding")
	@ResponseBody
	public AjaxResultVo saveUpdateBinding(MobilePo po) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			mobileService.saveUpdateBindingMobile(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号绑定异常", e);
		}
		return result;
	}

	@RequestMapping("removeBinding")
	@ResponseBody
	public AjaxResultVo removeBinding(Long id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			mobileService.saveRemoveBindingMobile(id, ShiroUtils.getSessionUser().getId());
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号解除异常", e);
		}
		return result;
	}
}
