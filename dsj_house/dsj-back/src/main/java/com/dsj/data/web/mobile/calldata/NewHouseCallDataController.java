package com.dsj.data.web.mobile.calldata;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.BeanToMap;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.StringUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.ResponseUtils;
import com.dsj.modules.mobile400.service.NewHouseMobileService;
import com.dsj.modules.mobile400.vo.NewHouseMobileVo;

@Controller
@RequestMapping(value = "back/**/mobileManager/calldata/newHouse")
public class NewHouseCallDataController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseCallDataController.class);

	@Autowired
	private NewHouseMobileService newHouseMobileService;

	@RequestMapping({ "yesterday", "" })
	public String yesterday(Model model) {
		return "mobileManager/calldata/newHouse/yesterday";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		if (StringUtils.isBlank(requestMap.get("startTime")) && StringUtils.isBlank(requestMap.get("endTime"))) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			requestMap.put("yesterday", yesterday);
		}
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseMobileService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房来电数据统计查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("total")
	public String total(Model model) {
		return "mobileManager/calldata/newHouse/total";
	}

	@RequestMapping("totalPage/list")
	@ResponseBody
	public PageDateTable<?> totalPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseMobileService.listTotalNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房来电总数据统计查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	// 导出昨日数据
	@RequestMapping("exportYesterday")
	@ResponseBody
	public Object exportYesterday(HttpServletResponse response, NewHouseMobileVo vo) throws Exception {
		BeanToMap beanToMap = new BeanToMap();
		Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
		if (StringUtils.isBlank(requestMap.get("startTime")) && StringUtils.isBlank(requestMap.get("endTime"))) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			requestMap.put("yesterday", yesterday);
		}
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getNewHouseExcelPath(), (PageParam pageParam) -> {
			PageBean page = null;
			try {
				page = newHouseMobileService.listNewPage(pageParam, requestMap);
			} catch (Exception e) {
				LOGGER.error("按日期统计楼盘400导出数据异常", e);
				e.printStackTrace();
			}
			return page;
		}, "time", "callCount", "callSuccess", "callSuccessLv", "callBusy", "callBusyLv", "callNot",
				"callNotLv");
	}

	// 导出
	@RequestMapping("exportTotal")
	@ResponseBody
	public Object exportTotal(HttpServletResponse response, NewHouseMobileVo vo) throws Exception {
		BeanToMap beanToMap = new BeanToMap();
		Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getNewHouseTotalExcelPath(), (PageParam pageParam) -> {
			PageBean page = null;
			try {
				page = newHouseMobileService.listTotalNewPage(pageParam, requestMap);
			} catch (Exception e) {
				LOGGER.error("按日期统计楼盘400导出数据异常", e);
				e.printStackTrace();
			}
			return page;
		}, "callCount", "callSuccess", "callSuccessLv", "callBusy", "callBusyLv", "callNot",
				"callNotLv");
	}
}
