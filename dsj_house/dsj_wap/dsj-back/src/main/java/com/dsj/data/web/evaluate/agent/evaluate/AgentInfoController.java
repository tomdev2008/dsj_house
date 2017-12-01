package com.dsj.data.web.evaluate.agent.evaluate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.dsj.modules.evaluate.po.AgentGradePo;
import com.dsj.modules.evaluate.service.AgentGradeService;
import com.dsj.modules.evaluate.service.AgentInfoService;

@Controller
@RequestMapping(value = "back/**/agentInfos")
public class AgentInfoController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentInfoController.class);
	
	@Autowired
	private AgentInfoService agentInfoService;
	
	@Autowired
	private AgentGradeService agentGradeService;
	
	/**
	 * 经纪人评价信息列表页
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public ModelAndView agentEvaluateList(Model model){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			List<AgentGradePo> list = agentGradeService.listBy(paramMap);
			model.addAttribute("gradeList", list);
		} catch (Exception e) {
			LOGGER.error("获取等级设定信息异常", e);
		}
		mv.setViewName("evaluate/agent/evaluate/evaluate_list");
		return mv;
	}
	
	/**
	 * 经纪人评价信息列表页数据
	 * @return
	 */
	@RequestMapping("evaluateList")
	@ResponseBody
	public PageDateTable<?> evaluateList(Model model,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer
				.parseInt((String)paramMap.get("iDisplayStart"));
		Integer pageSize = Integer
				.parseInt((String)paramMap.get("iDisplayLength"));
		if (paramMap.get("gradeScore") != null 
				&& StringUtils.isNotEmpty((String)paramMap
						.get("gradeScore"))) {
			String gradeScore = (String)paramMap.get("gradeScore");
			String[] grades = gradeScore.split("-");
			paramMap.put("minScore", grades[0]);
			paramMap.put("maxScore", grades[1]);
		}
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = agentInfoService.listPageByParams(pageParam, paramMap);
		} catch (Exception e) {
			LOGGER.error("经纪人评价信息查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

}
