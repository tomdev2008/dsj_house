package com.dsj.data.web.rentHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.rent.po.RentHousePicturePo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.service.RentHousePictureService;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.solr.service.RentHouseIndexService;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;

/**
 * 租房管理
 */
@Controller
@RequestMapping(value = "rentHouseDetail")
public class RentHouseDetailController {
	private final Logger LOGGER = LoggerFactory.getLogger(RentHouseDetailController.class);
	
	@Autowired
	RentHouseIndexService rentHouseIndexService;
	
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private RentHousePictureService rentHousePictureService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	
	//租房详情页
	@RequestMapping({"detail"})
	public String rentHouseDetails(Model model, Long id ){
		//房源详情
		RentHouseOriginVo originVo = rentHouseOriginService.getVoById(id);
		//小区
		HouseDirectoryPo directoryPo = houseDirectoryService.getById(originVo.getDicId());
		//经纪人详情
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("houseId", id);
		List<AgentVo> agentVoList = agentService.getVoByRentHouseId(paramMap);
		AgentVo agentVo = new AgentVo();
		if (agentVoList!=null && agentVoList.size()!=0) {
			agentVo = agentVoList.get(0);
		}
		//房源图片
		paramMap.clear();
		paramMap.put("objId", id);
		paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<RentHousePicturePo> picList = rentHousePictureService.listBy(paramMap);
		
		model.addAttribute("originVo", originVo);
		model.addAttribute("agentVo", agentVo);
		model.addAttribute("picList", picList);
		model.addAttribute("directoryPo", directoryPo);
		//字典表回写
		setGroupTypeName(model,originVo);
		
		return "rentHouse/rent_house_details";
	}
	
	// 获取租房推荐
	@RequestMapping("getRecommend")
	@ResponseBody
	public AjaxResultVo getRecommend(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			List<RentHouseOriginVo> recommendList = rentHouseOriginService.getRecommendList(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(recommendList);
		} catch (Exception e) {
			LOGGER.error("楼盘相册查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 通过区域等级获取地图信息
	 * @param level 传入参数级别  城市2  区县3 商圈4
	 * @param id 传入参数的id
	 * @return
	 */
	@RequestMapping("getRentByAreaId")
	@ResponseBody
	public AjaxResultVo getRentByAreaId(Integer level , Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			List<RentCountMapInfoVo> res = new ArrayList<>();
			paramMap.put("id", id);
			if (AreaEnum.CITY_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByCity(paramMap);
			}else if(AreaEnum.COUNTY_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByCounty(paramMap);
			}else if(AreaEnum.TRADE_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByTrade(paramMap);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(res);
		} catch (Exception e) {
			LOGGER.error("通过区域等级获取地图信息失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	private void setGroupTypeName(Model model,RentHouseOriginVo originVo ) {
		// 房屋类型
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_TYPE);
		// 装修情况
		Map<String, Object> zxTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.RENVATION);
		// 朝向
		Map<String, Object> orientationsMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		// 付款方式
		Map<String, Object> payTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PAY_TYPE);
		// 性别限制
		Map<String, Object> sexTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.SEX_TYPE);
		
		//付款
		model.addAttribute("payTypeName", payTypeMap.get(originVo.getPayType().toString()));
		//朝向
		model.addAttribute("orientationName", orientationsMap.get(originVo.getOrientation().toString()));
		//性别
		model.addAttribute("sexTypeName", sexTypeMap.get(originVo.getGenderType().toString()));
		//装修
		model.addAttribute("zxTypeName", zxTypeMap.get(originVo.getZxType().toString()));
		//物业
		model.addAttribute("wyTypeName", wyTypeMap.get(originVo.getWyType().toString()));
	}
	
}
