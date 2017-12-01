package com.dsj.data.web.evaluate.agent.grade;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.evaluate.po.AgentGradePo;
import com.dsj.modules.evaluate.service.AgentGradeService;

@Controller
@RequestMapping(value = "back/**/agentGrade")
public class AgentGradeController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentGradeController.class);
	
	@Autowired
	private AgentGradeService agentGradeService;
	
	/**
	 * 等级设定信息页面
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public ModelAndView evaluateGrade() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("evaluate/agent/grade/evaluate_grade");
		return mv;
	}
	
	/**
	 * 等级设定信息
	 * @return
	 */
	@RequestMapping("gradeList")
	@ResponseBody
	public AjaxResultVo gradeList() {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			List<AgentGradePo> list = agentGradeService.listBy(paramMap);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(list);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取等级设定信息页面异常", e);
		}
		return ajax;
	}
	
	@RequestMapping("saveGrade")
	@ResponseBody
	public AjaxResultVo saveGrade(@RequestBody List<AgentGradePo> list){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (!CollectionUtils.isEmpty(list)) {
				list.removeAll(Collections.singleton(null));
				for (AgentGradePo po : list) {
					po.setUpdatePerson(ShiroUtils.getSessionUser().getId()
							.intValue());
					agentGradeService.updateDynamic(po);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改等级设定信息异常", e);
		}
		return ajax;
	}
	
}
