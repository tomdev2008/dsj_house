package com.dsj.data.web.evaluate.agent.evaluate;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.modules.evaluate.service.AgentEvaluateService;

@Controller
@RequestMapping(value = "back/**/agentEvaluate")
public class AgentEvaluateController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentEvaluateController.class);
	
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	
	/**
	 * 服务态度评价列表页
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public ModelAndView attitudeEvaluate(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("evaluate/agent/evaluate/attitude_evaluate");
		return mv;
	}
	
	/**
	 * 专业水平评价列表页
	 * @return
	 */
	@RequestMapping("professional")
	@ResponseBody
	public ModelAndView professionalEvaluate(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("evaluate/agent/evaluate/professional_evaluate");
		return mav;
	}
	
	/**
	 * 服务态度评价列表页数据
	 * @return
	 */
	@RequestMapping("attitudeList")
	@ResponseBody
	public PageDateTable<?> attitudeList( Model model, 
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) paramMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) paramMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		paramMap.put("paragraphNo", 79401);
		PageBean page = null;
		try {
			page = agentEvaluateService.listPageByParams(pageParam, paramMap);
		} catch (Exception e) {
			LOGGER.error("服务态度评价信息查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 专业水平评价列表页数据
	 * @return
	 */
	@RequestMapping("evaluateList")
	@ResponseBody
	public PageDateTable<?> evaluateList( Model model, 
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) paramMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) paramMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		paramMap.put("paragraphNo", 79402);
		PageBean page = null;
		try {
			page = agentEvaluateService.listPageByParams(pageParam, paramMap);
		} catch (Exception e) {
			LOGGER.error("专业水平评价信息查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

}
