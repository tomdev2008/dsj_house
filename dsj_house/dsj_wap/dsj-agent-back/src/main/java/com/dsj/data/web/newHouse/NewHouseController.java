package com.dsj.data.web.newHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.digester.plugins.strategies.FinderSetProperties;
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
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
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
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.GroupTypePo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.FindVo;

@Controller
@RequestMapping(value = "agent/back/**/newHouse")
public class NewHouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseController.class);
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;
	@Autowired
	private NewHousePictureAuthService newHousePictureAuthService;

	@Autowired
	private SubwayService subwayService;

	private static Integer pageSize = 9;

	@RequestMapping("newHouseDirectoryList")
	@ResponseBody
	public Map<String, Object> newHouseDirectoryList(FindVo findVo, Model model, ServletRequest request) {
		Map<String, Object> paramMap = new HashMap<>();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		int agentId = ShiroUtils.getSessionUser().getId().intValue();
		requestMap.put("isTure", NewHouseIsTrueEnum.UP.getValue());
		requestMap.put("status", findVo.getStatus());
		requestMap.put("agentId", agentId);
		requestMap.put("name", findVo.getName());
		Integer pageNumber = 1;
		if (findVo.getPage() != null) {
			pageNumber = findVo.getPage();
		}
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listAgentNewHousePage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房列表查询异常", e);
		}
		// 上架楼盘
		List<NewHouseDirectoryAuthVo> data = (List<NewHouseDirectoryAuthVo>) page.getRecordList();
		if(data.size()==0 && findVo.getStatus()==null){
			Map<String, Object> requestMaps = new HashMap<String, Object>();
			requestMaps.put("dataType", 1);
			page=newHouseDirectoryAuthService.getListDataType(pageParam,requestMaps);
			data=(List<NewHouseDirectoryAuthVo>) page.getRecordList();
		}
		paramMap.put("totalPage", page.getPageCount());
		paramMap.put("data", data);
		return paramMap;
	}

	/**
	 * 推荐/取消推荐
	 */
	@RequestMapping("recommend")
	@ResponseBody
	public AjaxResultVo recommend(Long newHouseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		int agentId = ShiroUtils.getSessionUser().getId().intValue();
		try {
			AgentDirectoryVo agentDirectoryVos = agentService.selectAgentDirectory(newHouseId);
			if (agentDirectoryVos == null) {
				long count = agentService.findAgentRecommend(agentId);
				if (count >= 10) {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("推荐楼盘不能大于十条");
				} else {
					AgentDirectoryVo agentDirectoryVo = new AgentDirectoryVo();
					agentDirectoryVo.setAgentId(agentId);
					agentDirectoryVo.setNewHouseId(newHouseId);
					agentDirectoryVo.setCreateTime(new Date());
					long result = agentService.insertAgentDirectory(agentDirectoryVo);
					if (result > 0) {
						ajax.setStatusCode(StatusCode.SUCCESS);
						ajax.setMessage("推荐成功");
					} else {
						ajax.setStatusCode(StatusCode.SERVER_ERROR);
						ajax.setMessage("推荐失败");
					}
				}
			} else {
				long result = agentService.deleteAgentDircetory(newHouseId);
				if (result > 0) {
					ajax.setStatusCode(StatusCode.SUCCESS);
					ajax.setMessage("取消成功");
				} else {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("取消失败");
				}
			}
		} catch (Exception e) {
			LOGGER.info("推荐错误", e.getMessage(), e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 展示在货架/取消展示
	 */
	@RequestMapping("showShelves")
	@ResponseBody
	public AjaxResultVo showShelves(Long newHouseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		int agentId = ShiroUtils.getSessionUser().getId().intValue();
		int type=1;
		try {
			AgentDirectoryVo agentDirectoryVos = agentService.selectShowShelves(newHouseId,type);
			if (agentDirectoryVos == null) {
				AgentDirectoryVo agentDirectoryVo = new AgentDirectoryVo();
				agentDirectoryVo.setAgentId(agentId);
				agentDirectoryVo.setHouseId(newHouseId);
				agentDirectoryVo.setCreateTime(new Date());
				agentDirectoryVo.setType(1);
				long result = agentService.insertShowShelves(agentDirectoryVo);
				if (result > 0) {
					ajax.setStatusCode(StatusCode.SUCCESS);
					ajax.setMessage("展示成功");
				} else {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("展示失败");
				}
			} else {
				long result = agentService.deleteShowShelves(newHouseId,type);
				if (result > 0) {
					ajax.setStatusCode(StatusCode.SUCCESS);
					ajax.setMessage("取消成功");
				} else {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("取消失败");
				}
			}
			long count = agentService.findShowShelves(agentId);
			if (count > 3) {
				AgentDirectoryVo agentVo = agentService.selectDateMin(agentId);
				agentService.deleteShelves(agentVo.getId());
			}
		} catch (Exception e) {
			LOGGER.info("系统错误", e.getMessage(), e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	// 楼盘form start

	// 基本信息
	@RequestMapping("newHouseData")
	@ResponseBody
	public AjaxResultVo newHouseAdd(Long houseId) {
		Map<String, Object> map = new HashMap<String, Object>();
		AjaxResultVo vo = new AjaxResultVo();
		vo.setStatusCode(StatusCode.SUCCESS);
		setSetectModel(map);
		NewHouseDirectoryAuthVo houseVo = newHouseDirectoryAuthService.getVoById(houseId);
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		hashmap.put("code", houseVo.getCode());
		hashmap.put("edit", NewHouseEditEnum.NO.getValue());
		NewHouseDirectoryAuthVo houseDirectoryAuthVo = newHouseDirectoryAuthService.getVoBy(hashmap);
		if (houseDirectoryAuthVo != null) {
			houseVo = houseDirectoryAuthVo;
		}
		map.put("houseVo", houseVo);
		addAreaModel(map, houseVo.getAreaLevalOne(), houseVo.getAreaLevalTwo(), houseVo.getAreaLevalThree());
		vo.setData(map);
		return vo;
	}

	// 相册信息
	@RequestMapping("photoData")
	@ResponseBody
	public AjaxResultVo photoData(Long houseId) {
		AjaxResultVo vo = new AjaxResultVo();
		try {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("newHouseId", houseId);
			hashMap.put("parentId", BusinessConst.PICTURE_STATUS);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<NewHousePictureCountVo> countList = newHousePictureAuthService.getListCount(hashMap);
			vo.setStatusCode(StatusCode.SUCCESS);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("countList", countList);
			map.put("newHouseId", houseId);
			map.put("newHouseCode", newHouseDirectoryAuthService.getById(houseId).getCode());
			vo.setData(map);
		} catch (Exception e) {
			LOGGER.error("相册查询异常", e);
			vo.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return vo;
	}

	// 相册类别
	@RequestMapping("photoTypeData")
	@ResponseBody
	public AjaxResultVo photoTypeData() {
		AjaxResultVo vo = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			Map<String, Object> pictureStatusMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PICTURE_STATUS);
			map.put("pictureStatusMap", pictureStatusMap);
			vo.setData(map);
		} catch (Exception e) {
			LOGGER.error("相册类型查询异常", e);
			vo.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return vo;
	}
	
	//相册详情
	@RequestMapping("newHousePicturesDetail")
	@ResponseBody
	public AjaxResultVo picturesDetail(Long houseId, Long pictureStatus) {
		AjaxResultVo vo = new AjaxResultVo();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("objectId", houseId);
		hashMap.put("pictureStatus", pictureStatus);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureAuthVo> list = null;
		try {
			list = newHousePictureAuthService.listVoBy(hashMap);
			HashMap<String, Object> map = new HashMap<String,Object>();
			map.put("pictureList", list);
			map.put("newHouseId", houseId);
			map.put("pictureStatus", pictureStatus);
			
			Map<String, Object> pictureStatusMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PICTURE_STATUS);
			
			map.put("pictureStatusName", pictureStatusMap.get(pictureStatus.toString()));
			vo.setStatusCode(StatusCode.SUCCESS);
			vo.setData(map);
		} catch (Exception e) {
			LOGGER.error("相册详情查询异常", e);
			vo.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return vo;
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
			LOGGER.error("楼盘操作失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	//保存图片
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
	
	//图片删除异常
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

	// 字典表默认值
	public String setSetectModel(Map<String, Object> mapType) {
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		mapType.put("wyTypeMap", wyTypeMap);
		Map<String, Object> dicTraitMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.DICTRAIT);
		mapType.put("dicTraitMap", dicTraitMap);
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		mapType.put("achTypeMap", achTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		mapType.put("propertyRightMap", propertyRightMap);
		return "newHouse/edit/newHouse_add";
	}

	// 地区默认值
	public void addAreaModel(Map<String, Object> mapType, Long areaLevalOne, Long areaLevalTwo, Long areaLevalThree) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		mapType.put("firstAreaList", firstAreaList);

		if (areaLevalOne != null) {
			hashMap.put("parentId", areaLevalOne);
			List<AreaPo> twoAreaList = areaService.listBy(hashMap);
			mapType.put("twoAreaList", twoAreaList);
		}
		if (areaLevalTwo != null) {
			hashMap.put("parentId", areaLevalTwo);
			List<AreaPo> threeAreaList = areaService.listBy(hashMap);
			mapType.put("threeAreaList", threeAreaList);
		}
		if (areaLevalThree != null) {
			hashMap.put("parentId", areaLevalThree);
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<TradeAreaPo> tradeAreaList = tradeAreaService.listBy(hashMap);
			mapType.put("tradeAreaList", tradeAreaList);
		}
		// 地铁
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pid", 0);
		List<SubwayPo> subwayList = subwayService.listBy(map);
		mapType.put("subwayInitList", subwayList);
	}

	/**
	 * 进入户型展示页面
	 * 
	 * @param model
	 * @param newHouseId
	 * @return
	 */
	@RequestMapping("newHouse_houseTypeList")
	@ResponseBody
	public AjaxResultVo newHouse_houseTypeList(Long newHouseId) {
		AjaxResultVo result = new AjaxResultVo();
		Map<String, Object> resData = new HashMap<>();
		try {
			int totalCount = 0;
			for (int i = 1; i <= 5; i++) {
				List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.findHouseTypeList(i, newHouseId);
				long count = newHouseTypeAuthService.findHouseTypeCount(i, newHouseId);
				resData.put("houseTypeList" + i, houseTypeList);
				resData.put("count" + i, count);
				totalCount += count;
			}
			resData.put("newHouseId", newHouseId);
			resData.put("totalCount", totalCount);
			result.setData(resData);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("进入户型详情页面异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

	/**
	 * 进入户型添加页面
	 * @param id
	 * @return
	 */
	@RequestMapping("newHouse_addHouseType")
	@ResponseBody
	public AjaxResultVo newHouse_addHouseType(Long id) {
		AjaxResultVo result = new AjaxResultVo();
		Map<String, Object> resData = new HashMap<>();
		try {
			NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(id);
			List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
			String wyType = wyTypeMap.getWyType();
			if(StringUtils.isNotBlank(wyType)){
				String[] wy = wyType.split(",");
				NewHouseDirectoryAuthPo newHouse = null;
				for (int i = 0; i < wy.length; i++) {
					GroupTypePo typePo = groupTypeService.getById(Long.parseLong(wy[i]));
					newHouse = new NewHouseDirectoryAuthPo();
					newHouse.setWyType(wy[i]);
					newHouse.setWyTypeName(typePo.getTypegroupname());
					wyList.add(newHouse);
				}
			}
			Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
			resData.put("orientations", orientations);
			resData.put("wyList", wyList);
			resData.put("newHouseId", id);
			result.setData(resData);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("跳转户型添加页面异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
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
	 * 编辑户型
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("newHouse_updateNewHouseType")
	@ResponseBody
	public AjaxResultVo newHouse_updateNewHouseType(Model model, long id) {
		AjaxResultVo result = new AjaxResultVo();
		Map<String, Object> resData = new HashMap<>();
		try {
			NewHouseTypeAuthPo newHouseType = newHouseTypeAuthService.findOneNewHouse(id);
			NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(newHouseType.getDicId());
			String wyType = wyTypeMap.getWyType();
			List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
			if(StringUtils.isNotBlank(wyType)){
				String[] wy = wyType.split(",");
				NewHouseDirectoryAuthPo newHouse = null;
				for (int i = 0; i < wy.length; i++) {
					GroupTypePo typePo = groupTypeService.getById(Long.parseLong(wy[i]));
					newHouse = new NewHouseDirectoryAuthPo();
					newHouse.setWyType(wy[i]);
					newHouse.setWyTypeName(typePo.getTypegroupname());
					wyList.add(newHouse);
				}
			}
			Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
			resData.put("orientations", orientations);
			resData.put("wyList", wyList);
			resData.put("newHouseType", newHouseType);
			resData.put("newHouseId", wyTypeMap.getId());
			resData.put("houseId", newHouseType.getId());
			result.setData(resData);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("跳转编辑户型页面异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
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
	 * 提交
	 * @param id
	 * @return
	 */
	@RequestMapping("submitAddNewHouse")
	@ResponseBody
	public AjaxResultVo submitAddNewHouse(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			newHouseDirectoryAuthService.updateNewHouseExamine(id, ShiroUtils.getSessionUser().getId());
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setMessage("成功");
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			ajax.setMessage("失败");
		}
		return ajax;
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
}
