package com.dsj.data.web.evaluate.agent.standard;

import java.util.ArrayList;
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
import com.dsj.modules.evaluate.po.AgentStandardPo;
import com.dsj.modules.evaluate.service.AgentStandardService;
import com.dsj.modules.evaluate.vo.AgentStandardVo;

@Controller
@RequestMapping(value = "back/**/agentStandard")
public class AgentStandardController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentStandardController.class);
	
	@Autowired
	private AgentStandardService agentStandardService;
	
	/**
	 * 权重配置基础分页
	 * @return
	 */
	@RequestMapping
	@ResponseBody
	public ModelAndView baseStandard() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("evaluate/agent/standard/base_standard");
		return mv;
	}
	
	/**
	 * 权重配置业务分页
	 * @return
	 */
	@RequestMapping("business")
	@ResponseBody
	public ModelAndView businessStandard() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("evaluate/agent/standard/business_standard");
		return mv;
	}
	
	/**
	 * 权重配置评价分页
	 * @return
	 */
	@RequestMapping("evaluate")
	@ResponseBody
	public ModelAndView evaluateStandard() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("evaluate/agent/standard/evaluate_standard");
		return mv;
	}
	
	/**
	 * 基础分权重配置信息
	 * @return
	 */
	@RequestMapping("baseList")
	@ResponseBody
	public AjaxResultVo baseList() {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AgentStandardVo> listVo = new ArrayList<AgentStandardVo>();
		AgentStandardVo vo1 = new AgentStandardVo();
		AgentStandardVo vo2 = new AgentStandardVo();
		
		try {
			paramMap.put("sectionNo", 792);
			List<AgentStandardPo> list = agentStandardService.listBy(paramMap);
			for (AgentStandardPo po : list) {
				if (po.getSort() == 1) {
					vo1.setId1(po.getId().intValue());
					vo1.setValue1(po.getScore());
					vo2.setId1(po.getId().intValue());
					vo2.setValue1(po.getCount());
					continue;
				}
				if (po.getSort() == 2) {
					vo1.setId2(po.getId().intValue());
					vo1.setValue2(po.getScore());
					vo2.setId2(po.getId().intValue());
					vo2.setValue2(po.getCount());
					continue;
				}
				if (po.getSort() == 3) {
					vo1.setId3(po.getId().intValue());
					vo1.setValue3(po.getScore());
					vo2.setId3(po.getId().intValue());
					vo2.setValue3(po.getCount());
					continue;
				}
				if (po.getSort() == 4) {
					vo1.setId4(po.getId().intValue());
					vo1.setValue4(po.getScore());
					vo2.setId4(po.getId().intValue());
					vo2.setValue4(po.getCount());
					continue;
				}
				if (po.getSort() == 5) {
					vo1.setId5(po.getId().intValue());
					vo1.setValue5(po.getScore());
					vo2.setId5(po.getId().intValue());
					vo2.setValue5(po.getCount());
					continue;
				}
			}
			listVo.add(vo2);
			listVo.add(vo1);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(listVo);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取业务分权重配置信息异常", e);
		}
		return ajax;
	}
	
	/**
	 * 业务分权重配置信息
	 * @return
	 */
	@RequestMapping("businessList")
	@ResponseBody
	public AjaxResultVo businessList() {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AgentStandardVo> listVo = new ArrayList<AgentStandardVo>();
		AgentStandardVo vo1 = new AgentStandardVo();
		AgentStandardVo vo2 = new AgentStandardVo();
		AgentStandardVo vo3 = new AgentStandardVo();
		
		try {
			paramMap.put("sectionNo", 793);
			List<AgentStandardPo> list = agentStandardService.listBy(paramMap);
			for (AgentStandardPo po : list) {
				if (po.getSort() == 1) {
					vo1.setId1(po.getId().intValue());
					vo1.setValue1(po.getScore());
					vo2.setId1(po.getId().intValue());
					vo2.setValue1(po.getCount());
					vo3.setId1(po.getId().intValue());
					vo3.setValue1(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 2) {
					vo1.setId2(po.getId().intValue());
					vo1.setValue2(po.getScore());
					vo2.setId2(po.getId().intValue());
					vo2.setValue2(po.getCount());
					vo3.setId2(po.getId().intValue());
					vo3.setValue2(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 3) {
					vo1.setId3(po.getId().intValue());
					vo1.setValue3(po.getScore());
					vo2.setId3(po.getId().intValue());
					vo2.setValue3(po.getCount());
					vo3.setId3(po.getId().intValue());
					vo3.setValue3(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 4) {
					vo1.setId4(po.getId().intValue());
					vo1.setValue4(po.getScore());
					vo2.setId4(po.getId().intValue());
					vo2.setValue4(po.getCount());
					vo3.setId4(po.getId().intValue());
					vo3.setValue4(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 5) {
					vo1.setId5(po.getId().intValue());
					vo1.setValue5(po.getScore());
					vo2.setId5(po.getId().intValue());
					vo2.setValue5(po.getCount());
					vo3.setId5(po.getId().intValue());
					vo3.setValue5(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 6) {
					vo1.setId6(po.getId().intValue());
					vo1.setValue6(po.getScore());
					vo2.setId6(po.getId().intValue());
					vo2.setValue6(po.getCount());
					vo3.setId6(po.getId().intValue());
					vo3.setValue6(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 7) {
					vo1.setId7(po.getId().intValue());
					vo1.setValue7(po.getScore());
					vo2.setId7(po.getId().intValue());
					vo2.setValue7(po.getCount());
					vo3.setId7(po.getId().intValue());
					vo3.setValue7(po.getDailyScore());
					continue;
				}
				if (po.getSort() == 8) {
					vo1.setId5(po.getId().intValue());
					vo1.setValue8(po.getScore());
					vo2.setId8(po.getId().intValue());
					vo2.setValue8(po.getCount());
					vo3.setId8(po.getId().intValue());
					vo3.setValue8(po.getDailyScore());
					continue;
				}
			}
			listVo.add(vo3);
			listVo.add(vo2);
			listVo.add(vo1);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(listVo);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取基础分权重配置信息异常", e);
		}
		return ajax;
	}
	
	/**
	 * 评价分权重配置信息
	 * @return
	 */
	@RequestMapping("evaluateList")
	@ResponseBody
	public AjaxResultVo evaluateList() {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<AgentStandardVo> listVo = new ArrayList<AgentStandardVo>();
		AgentStandardVo vo1 = new AgentStandardVo();
		
		try {
			paramMap.put("sectionNo", 794);
			List<AgentStandardPo> list = agentStandardService.listBy(paramMap);
			for (AgentStandardPo po : list) {
				if (po.getSort() == 1) {
					vo1.setId1(po.getId().intValue());
					vo1.setValue1(po.getCount());
					continue;
				}
				if (po.getSort() == 2) {
					vo1.setId2(po.getId().intValue());
					vo1.setValue2(po.getCount());
					continue;
				}
				if (po.getSort() == 3) {
					vo1.setId3(po.getId().intValue());
					vo1.setValue3(po.getCount());
					continue;
				}
				if (po.getSort() == 4) {
					vo1.setId4(po.getId().intValue());
					vo1.setValue4(po.getCount());
					continue;
				}
				if (po.getSort() == 5) {
					vo1.setId5(po.getId().intValue());
					vo1.setValue5(po.getCount());
					continue;
				}
				if (po.getSort() == 6) {
					vo1.setId6(po.getId().intValue());
					vo1.setValue6(po.getCount());
					continue;
				}
			}
			listVo.add(vo1);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(listVo);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取评价分权重配置信息异常", e);
		}
		return ajax;
	}
	
	@RequestMapping("saveStandard")
	@ResponseBody
	public AjaxResultVo saveStandard(@RequestBody List<AgentStandardPo> list){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (!CollectionUtils.isEmpty(list)) {
				list.removeAll(Collections.singleton(null));
				for (AgentStandardPo po : list) {
					po.setUpdatePerson(ShiroUtils.getSessionUser().getId()
							.intValue());
					agentStandardService.updateDynamic(po);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改经纪人基础分异常", e);
		}
		return ajax;
	}

}
