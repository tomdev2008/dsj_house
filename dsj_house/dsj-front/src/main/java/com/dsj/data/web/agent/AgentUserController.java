package com.dsj.data.web.agent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FrontAgentCompanyTypeEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.GroupTypePo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.system.enums.AgentFeatureEnum;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.CompanyService;
import com.google.common.collect.Maps;


@Controller
@RequestMapping(value = "front/agent")
public class AgentUserController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(AgentUserController.class);
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private TradeAreaService tradeAreaService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	GroupTypeService groupTypeService;

	static Map<String,Object> agentFeature = Maps.newLinkedHashMap();
	static Map<String,Object> companyType = Maps.newLinkedHashMap();
	
	static {
		agentFeature = AgentFeatureEnum.toMap();
		companyType = FrontAgentCompanyTypeEnum.toMap();
	}
	
	/**
	 * 获取经纪人列表
	 */
	@RequestMapping
    public String listAgentUsers(Model model, 
    		@RequestParam(value="params", defaultValue="") String params,
    		@RequestParam(value="k", defaultValue="") String k) {
		LOGGER.info("经纪人列表查询listAgentUsers ： ", 
				params + "?keywords=" + k);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> paramDealMap = new HashMap<String, Object>();
		List<AreaPo> areaFirstList = null;
		Map<String,List<TradeAreaPo>> tradeAreaMap = null;
		PageBean page = null;
		int pageNumber = 1;
		int pageSize = 10;
		
		try {
			paramMap.put("parentId", BusinessConst.BEIJING_AREA_CODE);
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			areaFirstList = areaService.listBy(paramMap);
			
			if (StringUtils.isNotEmpty(k)) {
				paramDealMap.put("keywords", k);
				model.addAttribute("keywords", k);
			}
			paramDealMap = dealParams(model, paramDealMap, params, k);
			if (paramDealMap.get("area") != null) {
				paramMap.clear();
				paramMap.put("parentId", paramDealMap.get("area"));
				tradeAreaMap = tradeAreaService.getMapByOrderByLp(paramMap);
			}
			
			if (paramDealMap.get("pageNumber") != null) { 
				pageNumber = (Integer)paramDealMap.get("pageNumber");
			}
			PageParam pageParam = new PageParam(pageNumber, pageSize);
			paramDealMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
			page = agentService.listNewPage(pageParam, paramDealMap);
		} catch (Exception e) {
			LOGGER.error("经纪人列表查询异常", e);
		}
		model.addAttribute("areaFirstList", areaFirstList);
		model.addAttribute("tradeAreaMap", tradeAreaMap);
		model.addAttribute("agentFeature", agentFeature);
		model.addAttribute("companyType", companyType);
		model.addAttribute("page", page);
		return "agent/agent_list";
	}
	
	/**
	 * seo参数优化
	 * 
		1. 区域      areaCode      ar  url_ar
		2. 商圈      tradeAreaCode ta  url_ta
		3. 公司      companyId     ct  url_ct
		4. 服务      featureNum    fw  url_fw
		5. 条件      conditionMap
		6. 排序      order         or  url_orderding
		7. 页码      ordering      pg  url_pg
	 */
	private Map<String, Object> dealParams(Model model, 
			Map<String, Object> paramDealMap, String params, String k) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String,Object> conditionMap = Maps.newLinkedHashMap();
		String areaName = null;
		String urlAr = null;
		
		paramDealMap.put("auditStatus", 1);
		if (StringUtils.isBlank(params)) {
			paramDealMap.put("sortId", 1);
			model.addAttribute("url_pg", "/front/agent/" 
					+ BusinessConst.PAGE_NUM);
			return paramDealMap;
		}
		
		Pattern p = Pattern.compile(BusinessConst.PAGE_NUM_P);
		Matcher m = p.matcher(params);
		if (m.find(0)) {
			Integer pageNumber = Integer.parseInt(m.group(0)
					.replace(BusinessConst.PAGE_NUM, ""));
			paramDealMap.put("pageNumber", pageNumber);
			model.addAttribute("pageNumber", pageNumber);
			model.addAttribute("url_pg", "/front/agent/" 
					+ params.replace(m.group(0), "") + BusinessConst.PAGE_NUM);
		} else {
			model.addAttribute("url_pg", "/front/agent/" + params 
					+ BusinessConst.PAGE_NUM);
		}
		
		model.addAttribute("url_orderding", params);
		model.addAttribute("url_ar", params);
		model.addAttribute("url_ta", params);
		model.addAttribute("url_ct", params);
		model.addAttribute("url_fw", params);
		
		if (StringUtils.isNotEmpty(k)) {
			paramDealMap.put("sortId", 1);
			model.addAttribute("conditionMap", conditionMap);
			return paramDealMap;
		}
		
		p = Pattern.compile(BusinessConst.ORDER_OD_P);
		m = p.matcher(params);
		if (m.find(0)) {
			Integer ordering = Integer.parseInt(m.group(0)
					.replace(BusinessConst.ORDER_OD, ""));
			model.addAttribute("ordering", ordering);
			model.addAttribute("url_orderding", params.replace(m.group(0), ""));
			if (ordering == 1) {
				paramDealMap.put("yearOrder", ordering);
			} else if (ordering == 2) {
				paramDealMap.put("scoreOrder", ordering);
			} else if (ordering == 3) {
				paramDealMap.put("dealOrder", ordering);
			} else if (ordering == 4) {
				paramDealMap.put("takeOrder", ordering);
			}
		} else {
			paramDealMap.put("sortId", 1);
		}

		p = Pattern.compile(BusinessConst.BUILD_AREA_P);
		m = p.matcher(params);
		if(m.find(0)){
			Integer areaCode = Integer.parseInt(m.group(0)
					.replace(BusinessConst.BUILD_AREA, ""));
			areaName = areaService.getById(areaCode).getName();
			conditionMap.put(areaName, params.replace(m.group(0), ""));
			paramDealMap.put("area", areaCode);
			model.addAttribute("areaCode", areaCode);
			urlAr = params.replace(m.group(0), "");
			model.addAttribute("url_ar", urlAr);
		}
		
		p = Pattern.compile(BusinessConst.BUILD_TRADE_P);
		m = p.matcher(params);
		if(m.find(0)){
			Integer tradeAreaCode = Integer.parseInt(m.group(0)
					.replace(BusinessConst.BUILD_TRADE, ""));
			paramMap.put("areaCode", tradeAreaCode);
			TradeAreaPo po = tradeAreaService.getBy(paramMap);
			if (po != null) {
				String tradeAreaName = po.getName();
				conditionMap.put(tradeAreaName, params.replace(m.group(0), ""));
			}
			conditionMap.put(areaName, conditionMap.get(areaName).toString()
					.replace(m.group(0), ""));
			paramDealMap.put("business", tradeAreaCode);
			model.addAttribute("tradeAreaCode", tradeAreaCode);
			model.addAttribute("url_ta", params.replace(m.group(0), ""));
			model.addAttribute("url_ar", urlAr.replace(m.group(0), ""));
		}
		
		p = Pattern.compile(BusinessConst.COMPANY_P);
		m = p.matcher(params);
		if(m.find(0)){
			Integer companyNum = Integer.parseInt(m.group(0)
					.replace(BusinessConst.COMPANY, ""));
			String companyName = FrontAgentCompanyTypeEnum.getEnum(companyNum)
					.getDesc();
			
			if (companyNum == 99) {
				GroupTypePo po = groupTypeService.getById(482);
				paramDealMap.put("companyOther", po.getTypegroupname());
			} else {
//				paramMap.clear();
//				paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
//				paramMap.put("shortName", companyName);
//				CompanyPo company = companyService.getBy(paramMap);
//				if (company != null) {
//					paramDealMap.put("company", company.getId());
//				}
				if (companyNum == 1) { //大搜家精选 11
					paramDealMap.put("company", 11);
				} else if (companyNum == 2) {  //链家 2
					paramDealMap.put("company", 2);
				} else if (companyNum == 3) {  //麦田房产  9
					paramDealMap.put("company", 9);
				} else if (companyNum == 4) {  //中原地产  3
					paramDealMap.put("company", 3);
				} else if (companyNum == 5) {  //我爱我家 10
					paramDealMap.put("company", 10);
				}
			}
			conditionMap.put(companyName, params.replace(m.group(0), ""));
			model.addAttribute("companyNum", companyNum);
			model.addAttribute("url_ct", params.replace(m.group(0), ""));
		}
		
		p = Pattern.compile(BusinessConst.SERVICE_TYPE_P);
		m = p.matcher(params);
		if(m.find(0)){
			Integer featureNum = Integer.parseInt(m.group(0)
					.replace(BusinessConst.SERVICE_TYPE, ""));
			conditionMap.put(AgentFeatureEnum.getEnum(featureNum).getDesc(), 
					params.replace(m.group(0), ""));
			paramDealMap.put("feature", 
					AgentFeatureEnum.getEnum(featureNum).getDesc());
			model.addAttribute("featureNum", featureNum);
			model.addAttribute("url_fw", params.replace(m.group(0), ""));
		}
		model.addAttribute("conditionMap", conditionMap);
		return paramDealMap;
	}

}
