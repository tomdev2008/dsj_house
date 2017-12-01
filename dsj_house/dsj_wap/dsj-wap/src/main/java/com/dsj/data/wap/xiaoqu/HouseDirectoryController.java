package com.dsj.data.wap.xiaoqu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.vo.HouseDirectoryFrontVo;

@Controller
@RequestMapping(value = "houseDirectory")
public class HouseDirectoryController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(HouseDirectoryController.class);
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private HousePictureService rentHousePictureService;
	@Autowired
	GroupTypeService groupTypeService;
	
	//小区详情
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResultVo detail(Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			HouseDirectoryPo dic = houseDirectoryService.getById(id);
			
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("objId", id);
			paramMap.put("objType", PictureObjectEnum.DIC.getValue());
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<HousePicturePo> picList = rentHousePictureService.listBy(paramMap);
			
			Map<String,Object> groupTypesMap=new HashMap<String,Object>();
			//初始化二手房字典
			Map<String,Object> groupTypes=groupTypeService.getHouseGroupType();
			if(groupTypes.get(BusinessConst.HEATING_MODE)!=null){//供暖方式
				groupTypesMap.put("heatingModeMap",JsonTools.mapToKvJson((Map<String,Object>) groupTypes.get(BusinessConst.HEATING_MODE)));
			}
			map.put("dic", dic);
			map.put("picList", picList);
			map.put("groupTypesMap", groupTypesMap);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小区详情查看错误", e);
		}
		ajaxVo.setData(map);
		return ajaxVo;
	}
	
	

	//小区详情
	@RequestMapping("coordinate")
	@ResponseBody
	public AjaxResultVo coordinate(Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			HouseDirectoryPo dic = houseDirectoryService.getById(id);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
			ajaxVo.setData(dic);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小区详情查看错误", e);
		}
		return ajaxVo;
	}
	
	//小区详情
	@SuppressWarnings("unchecked")
	@RequestMapping("detail2")
	@ResponseBody
	public AjaxResultVo detail2(Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		HouseDirectoryPo dic = null;
		HouseDirectoryFrontVo frontVo = new HouseDirectoryFrontVo();
		try {
			dic = houseDirectoryService.getById(id);
			MyBeanUtils.copyBean2Bean(frontVo, dic);
			Map<String, Object> map = new HashMap<String,Object>();
			//初始化二手房字典
			Map<String,Object> groupTypes=groupTypeService.getHouseGroupType();
			//供暖方式
			if(groupTypes.get(BusinessConst.HEATING_MODE)!=null){
				map = (Map<String,Object>) groupTypes.get(BusinessConst.HEATING_MODE);
				if (frontVo.getHeatingMode()!=null && map.get(frontVo.getHeatingMode().toString())!=null) {
					frontVo.setHeatingModeName(map.get(frontVo.getHeatingMode().toString()).toString());
				}
			}
			//产权性质
			if(frontVo.getCertificate()!= null && groupTypes.get(BusinessConst.CERTIFICATE)!=null && map.get(frontVo.getCertificate().toString())!=null){
				map = (Map<String,Object>) groupTypes.get(BusinessConst.CERTIFICATE);
				if (frontVo.getCertificate()!=null) {
					frontVo.setCertificateName(map.get(frontVo.getCertificate().toString()).toString());
				}
			}
			//物业类型
			if(groupTypes.get(BusinessConst.WY_TYPE)!=null){
				map = (Map<String,Object>) groupTypes.get(BusinessConst.WY_TYPE);
				if (frontVo.getWyType()!=null && map.get(frontVo.getWyType().toString()) != null) {
					frontVo.setWyTypeName(map.get(frontVo.getWyType().toString()).toString());
				}
			}
			//建筑类型
			if(groupTypes.get(BusinessConst.ACH_H_TYPE)!=null){
				map = (Map<String,Object>) groupTypes.get(BusinessConst.ACH_H_TYPE);
				if (frontVo.getAchType()!=null && map.get(frontVo.getAchType().toString())!=null) {
				frontVo.setAchTypeName(map.get(frontVo.getAchType().toString()).toString());
				}
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小区详情查看错误", e);
		}
		ajaxVo.setData(frontVo);
		return ajaxVo;
	}
	
}
