package com.dsj.data.wap.agent;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FrontAgentCompanyTypeEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.StringUtilsOne;
import com.dsj.common.vo.AjaxResultVo;
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
@RequestMapping(value = "agent")
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
	@ResponseBody
    public AjaxResultVo listAgentUsers(String keywords, Integer areaCode,
			Integer tradeAreaCode, Integer companyNum, String feature, 
			Integer ordering, Integer pageNumber, Integer pageSize) {
		LOGGER.info("经纪人列表查询listAgentUsers ：pageSize= ", pageSize 
				+ " pageNumber=" + pageNumber);
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<AreaPo> areaFirstList = null;
		List<TradeAreaPo> tradeAreaList = null;
		PageBean page = null;
		
		try {
			paramMap.put("parentId", BusinessConst.BEIJING_AREA_CODE);
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			areaFirstList = areaService.listBy(paramMap);
			
			if (areaCode != null) {
				paramMap.clear();
				paramMap.put("parentId", areaCode);
				tradeAreaList = tradeAreaService.listBy(paramMap);
			}
			
			paramMap.clear();
			paramMap.put("auditStatus", 1);
			if (StringUtils.isNotEmpty(keywords)) {
				paramMap.put("keywords", keywords);
				paramMap.put("sortId", 1);
			} else {
				if (areaCode != null) paramMap.put("area", areaCode);
				if (tradeAreaCode != null) 
						paramMap.put("business", tradeAreaCode);
				if (companyNum != null) {
					if (companyNum == 99) {
						GroupTypePo po = groupTypeService.getById(482);
						paramMap.put("companyOther", po.getTypegroupname());
					} else {
//						String companyName = FrontAgentCompanyTypeEnum
//								.getEnum(companyNum).getDesc();
//						paramMap.clear();
//						paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
//						paramMap.put("shortName", companyName);
//						CompanyPo company = companyService.getBy(paramMap);
//						if (company != null) {
//							paramMap.put("company", company.getId());
//						}
						if (companyNum == 1) { //大搜家精选 11
							paramMap.put("company", 11);
						} else if (companyNum == 2) {  //链家 2
							paramMap.put("company", 2);
						} else if (companyNum == 3) {  //麦田房产  9
							paramMap.put("company", 9);
						} else if (companyNum == 4) {  //中原地产  3
							paramMap.put("company", 3);
						} else if (companyNum == 5) {  //我爱我家 10
							paramMap.put("company", 10);
						}
					}
				}
				if (feature != null) paramMap.put("feature", feature);
				if (ordering != null && ordering == 1) {
					paramMap.put("yearOrder", ordering);
				} else if (ordering != null && ordering == 2) {
					paramMap.put("scoreOrder", ordering);
				} else if (ordering != null && ordering == 3) {
					paramMap.put("dealOrder", ordering);
				} else if (ordering != null && ordering == 4) {
					paramMap.put("takeOrder", ordering);
				} else {
					paramMap.put("sortId", 1);
				}
			}
			if (pageNumber == null) pageNumber = 1;
			if (pageSize == null) pageSize = 5;
			PageParam pageParam = new PageParam(pageNumber, pageSize);
			paramMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
			page = agentService.listNewPage(pageParam, paramMap);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("经纪人列表查询异常", e);
		}
		resultMap.put("areaFirstList", areaFirstList);
		resultMap.put("tradeAreaList", tradeAreaList);
		resultMap.put("agentFeature", agentFeature);
		resultMap.put("companyType", companyType);
		resultMap.put("page", page);
		resultMap.put("keywords", keywords);
		resultMap.put("areaCode", areaCode);
		resultMap.put("tradeAreaCode", tradeAreaCode);
		resultMap.put("companyNum", companyNum);
		resultMap.put("feature", feature);
		resultMap.put("ordering", ordering);
		ajaxVo.setData(resultMap);
		return ajaxVo;
	}

}
