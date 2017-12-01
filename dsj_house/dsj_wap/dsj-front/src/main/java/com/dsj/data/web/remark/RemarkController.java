package com.dsj.data.web.remark;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.evaluate.service.AgentDailyEvaluateService;
import com.dsj.modules.evaluate.service.AgentEvaluateService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;

@Controller
@RequestMapping(value = "remark")
public class RemarkController {
	private final Logger LOGGER = LoggerFactory.getLogger(RemarkController.class);
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private AgentDailyEvaluateService agentDailyEvaluateService;
	
	/**
	 * 评价页面
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "page")
	public ModelAndView page(long userId){
		ModelAndView mav = new ModelAndView();
		try {
			AgentVo vo = agentService.getByUserId(userId);
			
			Map<String,Object> map = new HashMap<String,Object>();
			
			map = agentEvaluateService.getRankAndScore(vo.getAgentCode());
			mav.addObject("userId",userId);
			mav.addObject("agent",vo);
			mav.addObject("rankAndScore",map);
		} catch (Exception e) {
			LOGGER.error("获取经纪人信息异常",e);
		}
		
		
		mav.setViewName("remark/evaluate");
		return mav;
	}
	/**
	 * 
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public AjaxResultVo add(HttpServletRequest request,long userId){
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentVo agent = agentService.getByUserId(userId);
			Long cityCode = null;
			String cityName = null;
			if(agent.getCity()!=null){
				Map<String,Object> map1 = new HashMap<String,Object>();
				map1.put("areaCode", agent.getCity());
				AreaPo a = areaService.getBy(map1);
				cityCode = Long.parseLong(agent.getCity());
				cityName = a.getName();
			}
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");  

			if(request.getParameter("service")!=null){
				agentDailyEvaluateService.addDailyEvaluate(Long.valueOf(agent.getAgentCode()), agent.getName(), cityCode, cityName,79401, 
						Integer.parseInt(request.getParameter("service")), format.format(new Date()).toString(), null);
			}
			if(request.getParameter("major")!=null){
				agentDailyEvaluateService.addDailyEvaluate(Long.valueOf(agent.getAgentCode()), agent.getName(), cityCode, cityName,79402, 
						Integer.parseInt(request.getParameter("major")), format.format(new Date()).toString(), null);
			}
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加评价失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		
		
		return result;
	}
	
}
