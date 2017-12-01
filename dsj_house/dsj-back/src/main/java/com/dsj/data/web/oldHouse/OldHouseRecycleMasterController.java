package com.dsj.data.web.oldHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
import com.dsj.common.enums.StatusCode;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.oldhouse.enums.RoomNumber1Enum;
import com.dsj.modules.oldhouse.enums.RoomNumber2Enum;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.google.common.collect.Maps;

/**
 * 新房管理
 */
@Controller
@RequestMapping(value = "back/**/oldHouse/recycle/master")
public class OldHouseRecycleMasterController {
	private final Logger LOGGER = LoggerFactory.getLogger(OldHouseRecycleMasterController.class);

	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private AreaService areaService;

	// 户室1
	static Map<String, Object> roomNo1Map = Maps.newLinkedHashMap();
	// 户室2
	static Map<String, Object> roomNo2Map = Maps.newLinkedHashMap();
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();

	static Map<String, Object> yesNoMap = Maps.newLinkedHashMap();

	static {
		for (int i = 1; i <= 10; i++) {
			roomMap.put(String.valueOf(i), i);
		}
	}

	@RequestMapping({ "master_list", "" })
	public String oldHouseList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "oldHouse/master/master_recycle_list";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseDirectoryList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		requestMap.put("deleteFlag", DeleteStatusEnum.DEL.getValue());
		PageBean page = null;
		try {
			page = oldHouseMasterService.listOldMasterPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("二手房房源查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}


	/**
	 * 添加二手房页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_edit")
	public String masterEdit(Long id, Model model) {

		OldHouseMasterPo oldMaster = oldHouseMasterService.getById(id);
		setSetectModel(model);
		model.addAttribute("oldMaster", oldMaster);
		return "oldHouse/master/master_add";
	}

	private void setSetectModel(Model model) {
		// 房屋类型
		Map<String, Object> houseTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_TYPE);
		// 装修情况
		Map<String, Object> renvationMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.RENVATION);
		// 朝向
		Map<String, Object> orientationsMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		// 产权
		Map<String, Object> certificateMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		// 特色
		Map<String, Object> featuresMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.FEATURES);
		model.addAttribute("houseTypeMap", houseTypeMap);
		model.addAttribute("renvationMap", renvationMap);
		model.addAttribute("orientationsMap", orientationsMap);
		model.addAttribute("certificateMap", certificateMap);
		model.addAttribute("featuresMap", featuresMap);

		// 枚举
		model.addAttribute("roomNo1Map", RoomNumber1Enum.toMap());
		model.addAttribute("roomNo2Map", RoomNumber2Enum.toMap());
		model.addAttribute("yesNoMap", YesNoEnum.toMap());

		// 静态数据
		model.addAttribute("roomMap", roomMap);
	}

	
	@RequestMapping("master_recovery")
	@ResponseBody
	public AjaxResultVo deleteMaster(@RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHouseMasterService.updateDeleteFlag(ids,DeleteStatusEnum.NDEL.getValue());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("二手房恢复错误", e);
		}
		return ajaxVo;
	}


}
