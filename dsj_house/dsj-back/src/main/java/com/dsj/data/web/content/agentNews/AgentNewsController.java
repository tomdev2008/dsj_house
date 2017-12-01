package com.dsj.data.web.content.agentNews;



import java.text.SimpleDateFormat;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.service.AgentNewsService;
import com.dsj.modules.comment.vo.AgentNewsVo;



@Controller
@RequestMapping(value = "back/**/agentNews")
public class AgentNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsController.class);
	
	@Autowired
	private AgentNewsService agentNewsService;
	/**
	 * 经纪人列表页
	 * @return
	 */
	@RequestMapping("newsList")
	@ResponseBody
	public ModelAndView newsList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/agentNews/agentNews_list");
		return mav;
	}
	
	/**
	 * 经纪人列表页数据
	 * @return
	 */
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentNewsService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 经纪人动态详情页面
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public ModelAndView detail(long id){
		
		ModelAndView mav = new ModelAndView();
		try {
			AgentNewsVo a = agentNewsService.getVoById(id);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        a.setTimeString(sdf.format(a.getCreateTime()));
	         
			mav.addObject("agentNews", a);

			mav.setViewName("content/agentNews/agentNews_detail");
		} catch (Exception e) {
			LOGGER.error("查询经纪人动态异常" ,e);
		}
		
		return mav;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResultVo delete(String ids){
		AjaxResultVo result = new AjaxResultVo();
		try {
			agentNewsService.deleteMany(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除经纪人动态异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
    
	
	
	

}
