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
import com.dsj.modules.mobile400.service.AgentMobileService;
import com.dsj.modules.mobile400.vo.AgentMobileVo;
@Controller
@RequestMapping(value = "back/**/mobileManager/calldata/byAgent")
public class CallDataByAgentController {
	private final Logger LOGGER = LoggerFactory.getLogger(CallDataByAgentController.class);
	
	@Autowired
	private AgentMobileService agentMobileService;
	
	@RequestMapping({ "agent_yesterday", ""})
	public String toAgentAgentYesterday(Model model) {
		return "mobileManager/calldata/agent_yesterday";
	}
	
	@RequestMapping("agentTotal")
	public String toAgentAgentTotal(Model model) {
		return "mobileManager/calldata/agent_total";
	}
	
	@RequestMapping("yesterdayPageList")
	@ResponseBody
	public PageDateTable<?> agentPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		if(StringUtils.isBlank(requestMap.get("startTime"))&&StringUtils.isBlank(requestMap.get("endTime"))){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE,-1);
			String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
			requestMap.put("yesterday",yesterday);
		}
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = agentMobileService.agentYesterday(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("按经纪人来电昨天新增统计查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	
	@RequestMapping("totalPageList")
	@ResponseBody
	public PageDateTable<?> agentTotal(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = agentMobileService.agentTotal(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("按经纪人来电累计数据统计查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	
	//按经纪人昨天新增导出
		@RequestMapping("exportAgentYesterday")
		@ResponseBody
		public Object exportAgentYesterday(HttpServletResponse response,AgentMobileVo vo) throws Exception{
			BeanToMap beanToMap = new BeanToMap();
			Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
			return ResponseUtils.downloadExcel(ConfigUtils.instance.getAgentAgentYesterdayExcelPath(),(PageParam pageParam)->{
			        PageBean page = null;
					try {
						page = agentMobileService.agentYesterday(pageParam, requestMap);
					} catch (Exception e) {
						LOGGER.error("导出异常", e);
						e.printStackTrace();
					}
			    return page;
		},"time","agentName","tellPhone","callCount","callSuccess","callSuccessLv","callBusy","callBusyLv","callNot","callNotLv");
			
		}
		
		
		//按经纪人累计导出
		@RequestMapping("exportAgentTotal")
		@ResponseBody
		public Object exportAgentTotal(HttpServletResponse response,AgentMobileVo vo) throws Exception{
			BeanToMap beanToMap = new BeanToMap();
			Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
			return ResponseUtils.downloadExcel(ConfigUtils.instance.getAgentAgentTotalExcelPath(),(PageParam pageParam)->{
	            PageBean page = null;
	    		try {
	    			page = agentMobileService.agentTotal(pageParam, requestMap);
	    		} catch (Exception e) {
	    			LOGGER.error("导出异常", e);
	    			e.printStackTrace();
	    		}
	        return page;
		},"agentName","tellPhone","callCount","callSuccess","callSuccessLv","callBusy","callBusyLv","callNot","callNotLv");
			
		}
}
