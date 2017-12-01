package com.dsj.data.web.other.house;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.PinyinUtil;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;
import com.dsj.modules.other.enums.SubwayObjectTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.SubwayObjPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.service.SubwayObjService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.other.vo.HouseDirectoryVo;
import com.dsj.modules.other.vo.HousePictureVo;

/**
 * 二手房楼盘字典管理
 */
@Controller
@RequestMapping(value = "back/**/houseDirectory")
public class HouseDirectoryController {

	private final Logger logger = LoggerFactory
			.getLogger(HouseDirectoryController.class);

	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private GroupTypeService groupTypeService;
	
	@Autowired
	private HousePictureService housePictureService;
	
	@Autowired
	private SubwayService subwayService;
	
	@Autowired
	private SubwayObjService subwayObjService;
	
	@Autowired
	private TradeAreaService tradeAreaService;
	
	@RequestMapping({"to_directory_list",""})
	public String toFlatList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		
		return "system/directory/directory_list";
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageFlatList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		PageBean page = null;
		try {
			page = houseDirectoryService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			logger.error("楼盘字典查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 跳转至新增或修改页面
	 */
	@RequestMapping("to_add_edit")
	public String addEdit(Long id,Model model) {
		try {
			Map<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("parentId", 0);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pid", 0);
			List<SubwayPo> subwayList = subwayService.listBy(map);
			List<AreaPo> firstAreaList = areaService.listBy(hashMap);
			model.addAttribute("firstAreaList", firstAreaList);//区域商圈
			model.addAttribute("subwayList", subwayList);//地铁
			if (id != null) {
				//edit初始化数据
				setEditData(id,model);
			}
			//字典map
			setDicMap(model);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			subwayService.listBy(hashMap);
		} catch (Exception e) {
			logger.error("楼盘字典跳转之新增或修改页面异常", e);
		}
		return "system/directory/directory_add_edit";
	}
	
	/**
	 * 新增或保存修改
	 * @param id
	 * @param subwayStr 地铁拼接
	 * @param vo
	 * @param model
	 * @return
	 */
	@RequestMapping("do_add_edit")
	@ResponseBody
	public AjaxResultVo doAddEdit(Long id, String subwayStr ,HouseDirectoryVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> hashMap = new HashMap<String, Object>();
		try {
			//地铁
			hashMap.put("parentId", 0);
			vo.setUpdatePreson(ShiroUtils.getSessionUser().getId().intValue());
			vo.setUpdateTime(new Date());
			vo.setSpellName(PinyinUtil.getPinyin(vo.getSprayName()));
			vo.setSpellHead(PinyinUtil.getFirstWords(vo.getSprayName()));
			if (id == null) {
				//add
				vo.setCreatePreson(ShiroUtils.getSessionUser().getId().intValue());
				vo.setCreateTime(new Date());
				long addId = houseDirectoryService.saveDirectory(vo);
				saveSubway(addId,subwayStr);
				ajax.setData(addId);
			}else{
				//edit
				houseDirectoryService.updateDirectory(vo);
				saveSubway(id,subwayStr);
				ajax.setData(id);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("二手房增改异常", e);
			ajax.setMessage("二手房增改异常");
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("directory_delete")
	@ResponseBody
	public AjaxResultVo directoryDelete(String[] ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			houseDirectoryService.updateDeleteFlag(ids, DeleteStatusEnum.DEL.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("楼盘字典删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("to_image_list/{id}")
	public String imageList(@PathVariable("id") Long id, Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (id != null) {
			paramMap.put("objId", id);
			paramMap.put("objType", PictureObjectEnum.DIC.getValue());
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			paramMap.put("pictureType", PictureTypeEnum.HOUSETYPE.getValue());
			List<HousePictureVo> houseTypeImages = housePictureService.listVoBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.BUILDING.getValue());
			List<HousePictureVo> buildingImages = housePictureService.listVoBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.WHOLESET.getValue());
			List<HousePictureVo> wholesetImages = housePictureService.listVoBy(paramMap);

			model.addAttribute("houseTypeImages", houseTypeImages);
			model.addAttribute("buildingImages", buildingImages);
			model.addAttribute("wholesetImages", wholesetImages);
			model.addAttribute("id", id);
		}
		return "system/directory/directory_image";
	}
	
	@RequestMapping("directory_image_add")
	public String imageAdd(Model model, Integer type, Long id) {
		model.addAttribute("type", type);
		model.addAttribute("id", id);
		return "system/directory/directory_image_add";
	}
	
	@RequestMapping("update_directory_image")
	@ResponseBody
	public AjaxResultVo updateDirectoryImage(Model model, @RequestParam("imageUrls[]") String[] imageUrls,
			Integer pictureType, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			housePictureService.updateDirectoryImage(imageUrls, pictureType, id ,ShiroUtils.getSessionUser().getId() );
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			logger.error("保存楼盘字典图片错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}
	
	@RequestMapping("delete_directory_image")
	@ResponseBody
	public AjaxResultVo deleteDirectoryImage(Model model, @RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			housePictureService.deleteDirectoryImage(ids);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			logger.error("删除楼盘字典图片错误", e);
		}
		return ajaxVo;
	}
	
	@RequestMapping("set_cover")
	@ResponseBody
	public AjaxResultVo setCover(Model model, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			housePictureService.updateCover(PictureObjectEnum.DIC.getValue(), id);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			logger.error("设置楼盘字典图片封面错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}
	
	public void setEditData(Long id,Model model){
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> twoMap = new HashMap<String, Object>();
		Map<String, Object> threeMap = new HashMap<String, Object>();
		Map<String, Object> fourMap = new HashMap<String, Object>();
		HouseDirectoryPo houseDirectory = houseDirectoryService.getById(id);
		
		//根据一级地区id查找二级地区
		twoMap.put("parentId", houseDirectory.getAreaCode1());
		twoMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> twoAreaList = areaService.listBy(twoMap);
		
		//根据二级地区id查找三级地区
		threeMap.put("parentId", houseDirectory.getAreaCode2());
		threeMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> threeAreaList = areaService.listBy(threeMap);
		
		//根据三级地区id查找商圈
		fourMap.put("parentId", houseDirectory.getAreaCode3());
		fourMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<TradeAreaPo> fourAreaList = tradeAreaService.listBy(fourMap);
		
		map.put("objType", SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue());
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("objId", id);
		List<SubwayObjPo> subObjList = subwayObjService.listBy(map);
		List<Map<String, Object>> subList = new ArrayList<>();
		for (SubwayObjPo po : subObjList) {
			Map<String, Object> resMap = new HashMap<String, Object>();
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			map.put("pid", po.getSubwayPid());
			List<SubwayPo> stationList = subwayService.listBy(map);
			resMap.put("lineId", po.getSubwayPid());
			resMap.put("stationId", po.getSubwayId());
			resMap.put("distance", po.getDistance());
			resMap.put("stationList", stationList);
			subList.add(resMap);
		}
		model.addAttribute("twoAreaList", twoAreaList);
		model.addAttribute("threeAreaList", threeAreaList);
		model.addAttribute("fourAreaList", fourAreaList);
		model.addAttribute("subwayNum", subList.size());
		model.addAttribute("subList", subList);//地铁
		model.addAttribute("oldDirectory", houseDirectory);//物业公司、基本信息
	}
	
	public void setDicMap(Model model){
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);//物业类型
		Map<String, Object> dicTraitMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.DICTRAIT);
		model.addAttribute("dicTraitMap", dicTraitMap);//楼盘特点
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACH_H_TYPE);
		model.addAttribute("achTypeMap", achTypeMap);//建筑类型
		Map<String, Object> certificateMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("certificateMap", certificateMap);//产权年限
		Map<String, Object> heatingModeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HEATING_MODE);
		model.addAttribute("heatingModeMap", heatingModeMap);//供暖方式
		Map<String, Object> propertyRightModeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PROPERTY_RIGHT);
		model.addAttribute("propertyRightModeMap", propertyRightModeMap);//产权性质
		logger.info(">>>>>>>>>>>wyTypeMap:"+wyTypeMap );
		logger.info(">>>>>>>>>>>dicTraitMap:"+dicTraitMap );
		logger.info(">>>>>>>>>>>achTypeMap:"+achTypeMap );
		logger.info(">>>>>>>>>>>certificateMap:"+certificateMap );
		logger.info(">>>>>>>>>>>heatingModeMap:"+heatingModeMap );
	}
	
	public void saveSubway(Long id,String subwayStr) throws Exception{
		if(StringUtils.isEmpty(subwayStr)) return;
		String[] subwayArr = subwayStr.split(",");
		List<SubwayObjPo> list = new ArrayList<SubwayObjPo>();
		for(String subway : subwayArr){
			if(StringUtils.isEmpty(subwayStr)) break;
			String[] sub = subway.split("-");
			SubwayObjPo po = new SubwayObjPo();
			po.setObjType(SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue());
			po.setObjId(id);
			po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			po.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
			po.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
			po.setCreateTime(new Date());
			po.setUpdateTime(new Date());
			po.setSubwayPid(Integer.parseInt(sub[0]));
			po.setSubwayId(Long.parseLong(sub[1]));
			po.setDistance(sub[2]);
			list.add(po);
		}
		Map<String, Object> mapDel = new HashMap<String, Object>();
		mapDel.put("objType", SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue());
		mapDel.put("objId", id);
		//删除已有关联再保存最新的
		subwayObjService.deleteByObjTypeAndObjId(mapDel);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subList", list);
		subwayObjService.saveList(map);
	};
	
}



