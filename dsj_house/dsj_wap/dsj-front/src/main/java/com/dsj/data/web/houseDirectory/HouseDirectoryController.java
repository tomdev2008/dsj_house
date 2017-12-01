package com.dsj.data.web.houseDirectory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.vo.HouseDirectoryVo;

/**
 * 租房管理
 */
@Controller
@RequestMapping(value = "houseDirectory")
public class HouseDirectoryController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseDirectoryController.class);
	
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	@Autowired
	private HousePictureService housePictureService;
	
	@Autowired
	private GroupTypeService groupTypeService;
	
	/**
	 * 跳转至小区详情
	 */
	@RequestMapping("details/{id}")
	public String addEdit(@PathVariable Long id,Model model) {
		try {
			HouseDirectoryPo po = houseDirectoryService.getById(id);
			dealVo( po, model);
		} catch (Exception e) {
			LOGGER.error("小区详情查看", e);
		}
		return "houseDirectory/house_directory_details";
	}
	
	public HouseDirectoryVo dealVo(HouseDirectoryPo po, Model model) throws Exception{
		Map<String, Object> heatingModeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HEATING_MODE);
		Map<String, Object> achtypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACH_H_TYPE);
		HouseDirectoryVo houseVo = new HouseDirectoryVo();
		MyBeanUtils.copyBean2Bean(houseVo,po);
		//供暖方式
		if (houseVo.getHeatingMode()!=null) {
			houseVo.setHeatingModeName(heatingModeMap.get(houseVo.getHeatingMode().toString()).toString());
		}
		//建筑类型
		if (StringUtils.isNotBlank(houseVo.getAchType()) && achtypeMap!=null && achtypeMap.get(houseVo.getAchType().toString())!=null) {
			houseVo.setAchTypeName(achtypeMap.get(houseVo.getAchType().toString()).toString());
		}
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("objId", po.getId());
		paramMap.put("objType", PictureObjectEnum.DIC.getValue());
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<HousePicturePo> picList = housePictureService.listBy(paramMap);
		model.addAttribute("houseVo", houseVo);
		model.addAttribute("picList", picList);
		return houseVo;
	}
	
}
