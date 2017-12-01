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

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.BeanToMap;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.StringUtils;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.ResponseUtils;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.mobile400.service.NewHouseMobileService;
import com.dsj.modules.mobile400.vo.NewHouseMobileVo;

@Controller
@RequestMapping(value = "back/**/mobileManager/calldata/byNewHouse")
public class CallDataByNewHouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(CallDataByNewHouseController.class);

	@Autowired
	private NewHouseMobileService newHouseMobileService;

	@RequestMapping({ "newHouseYesterday", "" })
	public String newHouseYesterday(Model model) {
		return "mobileManager/calldata/newHouse/newHouse_yesterday";
	}

	@RequestMapping("yesterdayPageList")
	@ResponseBody
	public PageDateTable<?> newHouseYesterdayPageList(ServletResponse repsonse, Model model, ServletRequest request,
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
			page = newHouseMobileService.newHouseYesterday(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("按经纪人来电昨日数据统计查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("newHouseTotal")
	public String toAgentAgentTotal(Model model) {
		return "mobileManager/calldata/newHouse/newHouse_total";
	}

	@RequestMapping("totalPageList")
	@ResponseBody
	public PageDateTable<?> newHouseTotalPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseMobileService.newHouseTotal(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("按经纪人来电累计数据统计查询异常", e);
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
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getByNewHouseExcelPath(), (PageParam pageParam) -> {
			PageBean page = null;
			try {
				page = newHouseMobileService.newHouseYesterday(pageParam, requestMap);
			} catch (Exception e) {
				LOGGER.error("按楼盘导出统计数据异常", e);
				e.printStackTrace();
			}
			return page;
		}, "time", "newHouseName", "callCount", "callSuccess", "callSuccessLv", "callBusy", "callBusyLv", "callNot",
				"callNotLv");
	}

	// 导出
	@RequestMapping("exportTotal")
	@ResponseBody
	public Object exportTotal(HttpServletResponse response, NewHouseMobileVo vo) throws Exception {
		BeanToMap beanToMap = new BeanToMap();
		Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getByNewHouseTotalExcelPath(), (PageParam pageParam) -> {
			PageBean page = null;
			try {
				page = newHouseMobileService.newHouseTotal(pageParam, requestMap);
			} catch (Exception e) {
				LOGGER.error("按楼盘导出统计数据异常", e);
				e.printStackTrace();
			}
			return page;
		}, "newHouseName", "callCount", "callSuccess", "callSuccessLv", "callBusy", "callBusyLv", "callNot",
				"callNotLv");
	}
}
