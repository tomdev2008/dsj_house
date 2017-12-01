package com.dsj.data.web.system.agentStatistic;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.ResponseUtils;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.system.po.AgentStatisticsPo;
import com.dsj.modules.system.service.AgentStatisticsService;


@Controller
@RequestMapping(value = "back/**/agentStatistic")
public class AgentStatisticController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentStatisticController.class);
	
	@Autowired
	private AgentStatisticsService agentStatisticsService;
	
	@RequestMapping("init")
	public void init(){
		List<Map<String,Object>> houseNews = agentStatisticsService.getHouseNews();
		List<Map<String,Object>> agentNews = agentStatisticsService.getAgentNews();
		List<Map<String,Object>> agentNewsLike = agentStatisticsService.getAgentNewsLike();
		List<Map<String,Object>> agentNewsrReply = agentStatisticsService.getAgentNewsReply();
		List<Map<String,Object>> houseRemark = agentStatisticsService.getHouseRemark();
		List<Map<String,Object>> houseRemarkLike = agentStatisticsService.getHouseRemarkLike();
		List<Map<String,Object>> houseRemarkReply = agentStatisticsService.getHouseRemarkReply();
		List<Map<String,Object>> agentGrade = agentStatisticsService.getAgentGrade();
		
		
		
		List<AgentStatisticsPo> list = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		//1
		if(houseNews!=null&&houseNews.size()>0){
			for(Map<String,Object> item:houseNews){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setHouseNewsNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));

				paramMap.put("time",item.get("time").toString());
				
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功1");
					} catch (Exception e) {
						LOGGER.error("插入失败1",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败1",e);
					}
					
				}
					
			}
		}
		//2
		if(agentNews!=null&&agentNews.size()>0){
			for(Map<String,Object> item:agentNews){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setAgentNewsLikeNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功2");
					} catch (Exception e) {
						LOGGER.error("插入失败2",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败2",e);
					}
					
				}
					
			}
		}
		//3
		if(agentNewsLike!=null&&agentNewsLike.size()>0){
			for(Map<String,Object> item:agentNewsLike){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setAgentNewsLikeNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功3");
					} catch (Exception e) {
						LOGGER.error("插入失败3",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败3",e);
					}
					
				}
					
			}
		}
		//4
		if(agentNewsrReply!=null&&agentNewsrReply.size()>0){
			for(Map<String,Object> item:agentNewsrReply){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setAgentNewsReplyNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功4");
					} catch (Exception e) {
						LOGGER.error("插入失败4",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败4",e);
					}
					
				}
					
			}
		}
		//5
		if(houseRemark!=null&&houseRemark.size()>0){
			for(Map<String,Object> item:houseRemark){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setHouseRemarkNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功5");
					} catch (Exception e) {
						LOGGER.error("插入失败5",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败5",e);
					}
					
				}
					
			}
		}
		//6
		if(houseRemarkLike!=null&&houseRemarkLike.size()>0){
			for(Map<String,Object> item:houseRemarkLike){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setHouseRemarkLikeNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功6");
					} catch (Exception e) {
						LOGGER.error("插入失败6",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败6",e);
					}
					
				}
					
			}
		}
		//7
		if(houseRemarkReply!=null&&houseRemarkReply.size()>0){
			for(Map<String,Object> item:houseRemarkReply){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setHouseRemarkReplyNum(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功7");
					} catch (Exception e) {
						LOGGER.error("插入失败7",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败7",e);
					}
					
				}	
			}
		}
		//8
		if(agentGrade!=null&&agentGrade.size()>0){
			for(Map<String,Object> item:agentGrade){
				AgentStatisticsPo po = new AgentStatisticsPo();
				po.setAgentGrade(Integer.valueOf(item.get("number").toString()));
				if(item.get("user")==null){
					continue;
				}
				po.setUserId(Long.valueOf(item.get("user").toString()));
				try {
					po.setTime(sdf.parse(item.get("time").toString()));
				} catch (ParseException e) {
					e.printStackTrace();
					LOGGER.error("时间转换出错：",e);
				}
				
				Map<String,Object> paramMap = new HashMap<String,Object>();
				paramMap.put("userId", item.get("user"));
				paramMap.put("time",item.get("time").toString());
				AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
				
				if(p==null){
					try {
						po.setCreateTime(new Date());
						agentStatisticsService.saveDynamic(po);
						System.out.println("插入成功8");
					} catch (Exception e) {
						LOGGER.error("插入失败8",e);
					}
					
				}else{
					try {
						po.setId(p.getId());
						agentStatisticsService.updateDynamic(po);
						System.out.println("更新成功");
					} catch (Exception e) {
						LOGGER.error("更新失败8",e);
					}
					
				}
			}
		}
		System.out.println("初始化结束");
		
	}
	
	/**
	 * 经纪人统计页
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("statisticsList")
    public ModelAndView agentList(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();

		mav.setViewName("system/agent/agentStatistics");
		return mav;
	}
	
	/**
	 * 经纪人统计数据
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentStatisticsService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("统计数据查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 经纪人昨日数据统计页
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("yesterday")
    public ModelAndView yesterday(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();

		mav.setViewName("system/agent/yesterday");
		return mav;
	}
	
	/**
	 * 经纪人昨日统计数据
	 * @param request
	 * @param response
	 * @return
	 */
	
	@RequestMapping("yesterdayData")
	@ResponseBody
	public PageDateTable<?> yesterdayData(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		
		Date yesterday = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(yesterday);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        Date beforeDate = calendar.getTime();  
		if(StringUtils.isBlank(requestMap.get("timeStart").toString())){
			requestMap.put("timeStart",sdf.format(beforeDate));
		}
		if(StringUtils.isBlank(requestMap.get("timeEnd").toString())){
			requestMap.put("timeYesterday",sdf.format(yesterday));
		}
		
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentStatisticsService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("昨日统计数据查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//导出
		@RequestMapping("yesterdayExport")
		@ResponseBody
		public Object yesterdayExport(HttpServletRequest request) throws Exception{
			
			Map<String, Object> requestMap = new HashMap<String,Object>();
			
			if(StringUtils.isNotBlank(request.getParameter("companyName"))){
				requestMap.put("companyName", request.getParameter("companyName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("name"))){
				requestMap.put("name", request.getParameter("name"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
				requestMap.put("phone", request.getParameter("phone"));
			}
			
			Date yesterday = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(yesterday);  
	        calendar.add(Calendar.DAY_OF_MONTH, -1);  
	        Date beforeDate = calendar.getTime();  
			if(StringUtils.isNotBlank(request.getParameter("timeStart"))){
				requestMap.put("timeStart", request.getParameter("timeStart"));
			}else{
				requestMap.put("timeStart",sdf.format(beforeDate));
			}
			if(StringUtils.isNotBlank(request.getParameter("timeEnd"))){
				requestMap.put("timeEnd", request.getParameter("timeEnd"));
			}else{
				requestMap.put("timeYesterday",sdf.format(yesterday));
			}
			
	        
			return ResponseUtils.downloadExcel(ConfigUtils.instance.getAgentStatisticsPath(),(PageParam pageParam)->{
	            PageBean page = null;
	    		try {
	    			page = agentStatisticsService.listNewPage(pageParam, requestMap);
	    		} catch (Exception e) {
	    			LOGGER.error("导出异常", e);
	    			e.printStackTrace();
	    		}
	    		return page;
			},"timeString","name","phone","companyName","houseNewsNum","agentNewsNum","agentNewsReplyNum","agentNewsLikeNum","houseRemarkNum","houseRemarkReplyNum","houseRemarkLikeNum","agentGrade");
			
		}
		
		
		@RequestMapping("export")
		@ResponseBody
		public Object export(HttpServletRequest request) throws Exception{
			
			Map<String, Object> requestMap = new HashMap<String,Object>();
			if(StringUtils.isNotBlank(request.getParameter("companyName"))){
				requestMap.put("companyName", request.getParameter("companyName"));
			}
			if(StringUtils.isNotBlank(request.getParameter("name"))){
				requestMap.put("name", request.getParameter("name"));
			}
			if(StringUtils.isNotBlank(request.getParameter("phone"))){
				requestMap.put("phone", request.getParameter("phone"));
			}
			if(StringUtils.isNotBlank(request.getParameter("timeStart"))){
				requestMap.put("timeStart", request.getParameter("timeStart"));
			}
			if(StringUtils.isNotBlank(request.getParameter("timeEnd"))){
				requestMap.put("timeEnd", request.getParameter("timeEnd"));
			}
			
			return ResponseUtils.downloadExcel(ConfigUtils.instance.getAgentStatisticsPath(),(PageParam pageParam)->{
	            PageBean page = null;
	    		try {
	    			page = agentStatisticsService.listNewPage(pageParam, requestMap);
	    		} catch (Exception e) {
	    			LOGGER.error("导出异常", e);
	    			e.printStackTrace();
	    		}
	    		return page;
			},"timeString","name","phone","companyName","houseNewsNum","agentNewsNum","agentNewsReplyNum","agentNewsLikeNum","houseRemarkNum","houseRemarkReplyNum","houseRemarkLikeNum","agentGrade");
			
		}
		
	
}
