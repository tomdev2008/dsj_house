package com.dsj.data.web.newHouse.edit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.newhouse.enums.NewHouseAuthStatusEnum;
import com.dsj.modules.newhouse.enums.NewHouseEditEnum;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.pagelayout.po.PcNewHousePo;
import com.dsj.modules.pagelayout.service.PcNewHouseService;
import com.dsj.modules.solr.service.NewHouseIndexService;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "back/**/newHouse/edit")
public class NewHouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseController.class);

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;
	@Autowired
	private NewHousePictureAuthService newHousePictureAuthService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private SubwayService subwayService;
	@Autowired
	private NewHouseIndexService newHouseIndexService;
	@Autowired
	private PcNewHouseService pcNewHouseService;
	@Autowired
	private HouseNewsService houseNewsService;
	@RequestMapping({ "newHouse_list", "" })
	public String newHouseList(Model model) {
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		return "newHouse/edit/newHouse_list";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseDirectoryList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("edit", NewHouseEditEnum.YES.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("开发商账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("newHouse_Picture_del")
	@ResponseBody
	public AjaxResultVo newHousePictureDel(@RequestBody List<Integer> ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHousePictureAuthService.updateDeleteFlagNewHousesPicture(ids, ShiroUtils.getSessionUser().getId());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("房源图片删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_del")
	@ResponseBody
	public AjaxResultVo newHouseDel(@RequestBody List<Integer> ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHouseDirectoryAuthService.updateDeleteFlagNewHouses(ids, ShiroUtils.getSessionUser().getId());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("房源删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_down")
	@ResponseBody
	public AjaxResultVo newHouseDown(@RequestBody List<Integer> ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			List<PcNewHousePo> pcNewHouse=pcNewHouseService.getFindAll();
			for (PcNewHousePo pcNewHousePo : pcNewHouse) {
				int id=Integer.parseInt(pcNewHousePo.getId().toString());
				if(ids.contains(id)){
					ajax.setMessage("请先在前台首页下线后，再下架楼盘");
					ajax.setStatusCode(StatusCode.SERVER_ERRORS);
					break;
				}
			}
			if(ajax.getMessage()==null){
				//下架
				newHouseDirectoryAuthService.updateIsTrueNewHouses(ids, ShiroUtils.getSessionUser().getId());
				ajax.setStatusCode(StatusCode.SUCCESS);
				//更新solR
				for (Integer id : ids) {
					newHouseIndexService.deleteItemIndex(id.toString());
				}
			}
			
		} catch (Exception e) {
			LOGGER.error("添加图片异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_add")
	public String newHouse_add(Model model) {
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);//物业类型
		Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		model.addAttribute("dicTraitMap", dicTraitMap);//楼盘特色
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		model.addAttribute("achTypeMap", achTypeMap);//建筑类型
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		addAreaModel(model, null, null, null);
		return "newHouse/edit/newHouse_add";
	}

	// 修改/添加
	@RequestMapping("newHouse_save")
	@ResponseBody
	public AjaxResultVo newHouseAdd(@RequestBody HashMap<String, Object> newHouseMap) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			long newHouseId = newHouseDirectoryAuthService.saveOrUpdateNewHouse(newHouseMap,
					ShiroUtils.getSessionUser().getId());
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(newHouseId);
		} catch (Exception e) {
			LOGGER.error("楼盘添加失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_pictures_add")
	public String picturesAdd(Model model, long newHouseId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("newHouseId", newHouseId);
		hashMap.put("parentId", BusinessConst.PICTURE_STATUS);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureCountVo> countList = newHousePictureAuthService.getListCount(hashMap);
		model.addAttribute("countList", countList);
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("newHouseCode", newHouseDirectoryAuthService.getById(newHouseId).getCode());
		
		return "newHouse/edit/newHouse_pictures_add";
	}

	@RequestMapping("newHouse_pictures_add_begin")
	public String picturesAddBegin(Model model) {
		Map<String, Object> pictureStatusMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PICTURE_STATUS);
		model.addAttribute("pictureStatusMap", pictureStatusMap);
		return "newHouse/edit/newHouse_pictures_add_begin";
	}

	@RequestMapping("newHouse_savePictures")
	@ResponseBody
	public AjaxResultVo newHouseSavePictures(@RequestBody List<NewHousePictureAuthPo> NewHousePictureAuthPoList) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHousePictureAuthService.saveList(NewHousePictureAuthPoList,
					ShiroUtils.getSessionUser().getId().longValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("添加图片异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_saveUpdatePicture")
	@ResponseBody
	public AjaxResultVo newHouseSaveUpdatePicture(NewHousePictureAuthPo po) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHousePictureAuthService.updateDynamic(po);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(po.getDescribes());
		} catch (Exception e) {
			LOGGER.error("修改图片描述异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_pictures_detail")
	public String pictures_detail(Model model, Long newHouseId, Long pictureStatus) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("objectId", newHouseId);
		hashMap.put("pictureStatus", pictureStatus);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureAuthVo> list = newHousePictureAuthService.listVoBy(hashMap);
		model.addAttribute("list", list);
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("pictureStatus", pictureStatus);
		return "newHouse/edit/newHouse_pictures_detail";
	}

	//设置封面
	@RequestMapping("newHouse_savePicture_first")
	@ResponseBody
	public AjaxResultVo newHouseSavePictureFirst(Long id, Long newHouseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHousePictureAuthService.saveNewHousePictureFirst(id, newHouseId);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("图片设为封面异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 进入户型展示页面
	 * 
	 * @param model
	 * @param newHouseId
	 * @return
	 */
	@RequestMapping("newHouse_houseTypeList")
	public String newHouse_houseTypeList(Model model, Long newHouseId) {
		int totalCount = 0;
		for (int i = 1; i <= 5; i++) {
			List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.findHouseTypeList(i, newHouseId);
			long count = newHouseTypeAuthService.findHouseTypeCount(i, newHouseId);
			model.addAttribute("houseTypeList" + i, houseTypeList);
			model.addAttribute("count" + i, count);
			totalCount += count;
		}
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("totalCount", totalCount);
		return "newHouse/edit/newHouse_houseTypeList";
	}

	@RequestMapping("newHouse_addHouseType")
	public String newHouse_addHouseType(Model model, Long id) {
		NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(id);
		List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
		String wyType = wyTypeMap.getWyType();
		String wyTypeName = wyTypeMap.getWyTypeName();
		if(StringUtils.isNotBlank(wyType)&&StringUtils.isNotBlank(wyTypeName)){
			String[] wy = wyType.split(",");
			String[] wyTypeNames = wyTypeName.split(",");
			NewHouseDirectoryAuthPo newHouse = null;
			for (int i = 0; i < wy.length; i++) {
				newHouse = new NewHouseDirectoryAuthPo();
				newHouse.setWyType(wy[i]);
				newHouse.setWyTypeName(wyTypeNames[i]);
				wyList.add(newHouse);
			}
		}
		Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		model.addAttribute("orientations", orientations);
		model.addAttribute("wyList", wyList);
		model.addAttribute("newHouseId", id);
		return "newHouse/edit/newHouse_addHouseType";
	}

	/**
	 * 户型保存
	 * 
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("save_layout_add")
	@ResponseBody
	public AjaxResultVo save_layout_add(HttpServletRequest request, NewHouseTypeAuthPo po) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			po.setCreatePreson(ShiroUtils.getSessionUser().getId().longValue());
			po.setCreateDate(new Date());
			po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			po.setHouseStatus(NewHouseAuthStatusEnum.WAIT_COMMIT.getValue().longValue());
			newHouseTypeAuthService.saveLayoutAdd(po);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setMessage("保存成功");
		} catch (Exception e) {
			LOGGER.error("添加户型异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 户型修改
	 * 
	 * @param request
	 * @param po
	 * @return
	 */
	@RequestMapping("updateAddNewHouse")
	@ResponseBody
	public AjaxResultVo updateAddNewHouse(HttpServletRequest request, NewHouseTypeAuthPo po, Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			po.setUpdateDate(new Date());
			newHouseTypeAuthService.updateLayoutAdd(po, id);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setMessage("修改成功");
		} catch (Exception e) {
			LOGGER.error("修改户型异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 编辑户型
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("newHouse_updateNewHouseType")
	public String newHouse_updateNewHouseType(Model model, long id) {
		NewHouseTypeAuthPo newHouseType = newHouseTypeAuthService.findOneNewHouse(id);
		NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(newHouseType.getDicId());
		List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
		String wyType = wyTypeMap.getWyType();
		String wyTypeName = wyTypeMap.getWyTypeName();
		
		if(StringUtils.isNotBlank(wyType)&&StringUtils.isNotBlank(wyTypeName)){
			String[] wy = wyType.split(",");
			String[] wyTypeNames = wyTypeName.split(",");
			NewHouseDirectoryAuthPo newHouse = null;
			for (int i = 0; i < wy.length; i++) {
				newHouse = new NewHouseDirectoryAuthPo();
				newHouse.setWyType(wy[i]);
				newHouse.setWyTypeName(wyTypeNames[i]);
				wyList.add(newHouse);
			}
		}
		
		Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		model.addAttribute("orientations", orientations);
		model.addAttribute("wyList", wyList);
		model.addAttribute("newHouseType", newHouseType);
		model.addAttribute("newHouseId", wyTypeMap.getId());
		model.addAttribute("houseId", newHouseType.getId());
		return "newHouse/edit/newHouse_updateHouseType";
	}

	@RequestMapping("submitAddNewHouse")
	@ResponseBody
	public AjaxResultVo submitAddNewHouse(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHouseDirectoryAuthService.updateNewHouseExamine(id, ShiroUtils.getSessionUser().getId());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("提交审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	@RequestMapping("newHouse_update")
	public String newHouse_update(Model model, Long id) {
		NewHouseDirectoryAuthVo vo = newHouseDirectoryAuthService.getVoById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("code", vo.getCode());
		map.put("edit", NewHouseEditEnum.NO.getValue());
		NewHouseDirectoryAuthVo houseDirectoryAuthVo = newHouseDirectoryAuthService.getVoBy(map);
		if (houseDirectoryAuthVo != null) {
			vo = houseDirectoryAuthVo;
		}
		model.addAttribute("vo", vo);
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		model.addAttribute("dicTraitMap", dicTraitMap);
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		model.addAttribute("achTypeMap", achTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		addAreaModel(model, vo.getAreaLevalOne(), vo.getAreaLevalTwo(), vo.getAreaLevalThree());
		return "newHouse/edit/newHouse_update";
	}

	@RequestMapping("newHouse_pictures_check")
	public String newHousePicturesCheck(Model model, long newHouseId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("newHouseId", newHouseId);
		hashMap.put("parentId", BusinessConst.PICTURE_STATUS);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureCountVo> countList = newHousePictureAuthService.getListCount(hashMap);
		model.addAttribute("countList", countList);
		model.addAttribute("newHouseId", newHouseId);
		return "newHouse/edit/check/newHouse_pictures_check";
	}

	@RequestMapping("newHouse_check")
	public String newHouseCheck(Model model, Long id) {
		NewHouseDirectoryAuthVo vo = newHouseDirectoryAuthService.getVoById(id);
		model.addAttribute("vo", vo);
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		//Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		model.addAttribute("dicTraitMap", dicTraitMap);
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		model.addAttribute("achTypeMap", achTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		return "newHouse/edit/check/newHouse_check";
	}

	@RequestMapping("newHouse_pictures_detail_check")
	public String newHousePicturesDetailCheck(Model model, Long newHouseId, Long pictureStatus) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("objectId", newHouseId);
		hashMap.put("pictureStatus", pictureStatus);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureAuthVo> list = newHousePictureAuthService.listVoBy(hashMap);
		model.addAttribute("list", list);
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("pictureStatus", pictureStatus);
		return "newHouse/edit/check/newHouse_pictures_detail_check";
	}

	@RequestMapping("newHouse_check_newHouseType")
	public String newHouseCheckNewHouseType(Model model, long id) {
		NewHouseTypeAuthPo newHouseType = newHouseTypeAuthService.findOneNewHouse(id);
		NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(newHouseType.getDicId());
		List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
		
		String wyType = wyTypeMap.getWyType();
		String wyTypeName = wyTypeMap.getWyTypeName();
		if(StringUtils.isNotBlank(wyType)&&StringUtils.isNotBlank(wyTypeName)){
			String[] wy = wyType.split(",");
			String[] wyTypeNames = wyTypeName.split(",");
			NewHouseDirectoryAuthPo newHouse = null;
			for (int i = 0; i < wy.length; i++) {
				newHouse = new NewHouseDirectoryAuthPo();
				newHouse.setWyType(wy[i]);
				newHouse.setWyTypeName(wyTypeNames[i]);
				wyList.add(newHouse);
			}
		}
		
		Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		model.addAttribute("orientations", orientations);
		model.addAttribute("wyList", wyList);
		model.addAttribute("newHouseType", newHouseType);
		model.addAttribute("newHouseId", wyTypeMap.getId());
		model.addAttribute("houseId", newHouseType.getId());
		return "newHouse/edit/check/newHouseType_check";
	}

	@RequestMapping("newHouse_check_houseTypeList")
	public String newHouseCheckHouseTypeList(Model model, Long newHouseId) {
		int totalCount = 0;
		for (int i = 1; i <= 5; i++) {
			List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.findHouseTypeList(i, newHouseId);
			long count = newHouseTypeAuthService.findHouseTypeCount(i, newHouseId);
			model.addAttribute("houseTypeList" + i, houseTypeList);
			model.addAttribute("count" + i, count);
			totalCount += count;
		}
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("totalCount", totalCount);
		return "newHouse/edit/check/newHouse_check_houseTypeList";
	}

	/**
	 * 删除选中户型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("newHouseType_del")
	@ResponseBody
	public AjaxResultVo newHouseType_del(@RequestBody List<Integer> ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHouseTypeAuthService.updateDeleteFlagNewHouses(ids, DeleteStatusEnum.DEL.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("户型删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	public void addAreaModel(Model model, Long areaLevalOne, Long areaLevalTwo, Long areaLevalThree) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);

		if (areaLevalOne != null) {
			hashMap.put("parentId", areaLevalOne);
			List<AreaPo> twoAreaList = areaService.listBy(hashMap);
			model.addAttribute("twoAreaList", twoAreaList);
		}
		if (areaLevalTwo != null) {
			hashMap.put("parentId", areaLevalTwo);
			List<AreaPo> threeAreaList = areaService.listBy(hashMap);
			model.addAttribute("threeAreaList", threeAreaList);
		}

		if (areaLevalThree != null) {
			hashMap.put("parentId", areaLevalThree);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<TradeAreaPo> tradeAreaList = tradeAreaService.listBy(hashMap);
			model.addAttribute("tradeAreaList", tradeAreaList);
		}

		// 地铁
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", 0);
		List<SubwayPo> subwayInitList = subwayService.listBy(map);
		model.addAttribute("subwayInitList", subwayInitList);

	}

	/**
	 * 下拉模糊查新
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("getDic")
	@ResponseBody
	public AjaxResultVo getDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("preName", name);
				map.put("name", name);
				map.put("edit", NewHouseEditEnum.YES.getValue());
				map.put("isTure", NewHouseIsTrueEnum.NOUP.getValue() + "," + NewHouseIsTrueEnum.UP.getValue() + ","
						+ NewHouseIsTrueEnum.DOWN.getValue());
				List<NewHouseDirectoryAuthPo> list = newHouseDirectoryAuthService.listBy(map);
				for (NewHouseDirectoryAuthPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getName());
					vo.setCode(po.getCode());
					vo.setPhone(po.getPhone());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	/**
	 * 下拉模糊查新
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("getElevDic")
	@ResponseBody
	public AjaxResultVo getElevDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("preName", name);
				map.put("name", name);
				map.put("edit", NewHouseEditEnum.YES.getValue());
				List<NewHouseDirectoryAuthPo> list = newHouseDirectoryAuthService.getElev(map);
				for (NewHouseDirectoryAuthPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getName());
					vo.setCode(po.getCode());
					vo.setPhone(po.getPhone());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	/**
	 * 下拉模糊查新
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("getDicIsTure")
	@ResponseBody
	public AjaxResultVo getDicIsTure(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("name", name);
				map.put("isTure", NewHouseIsTrueEnum.UP.getValue());
				List<NewHouseDirectoryAuthPo> list = newHouseDirectoryAuthService.listBy(map);
				for (NewHouseDirectoryAuthPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getName());
					vo.setCode(po.getCode());
					vo.setPhone(po.getPhone());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("下拉模糊查询异常", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}

	
	@RequestMapping("updateNewHouseNews")
	@ResponseBody
	public AjaxResultVo updateNew(Model model) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
			try {
				houseNewsService.updateNewHouseNews();
			} catch (Exception e) {
				LOGGER.error("下拉模糊查询异常", e);
			}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
}
