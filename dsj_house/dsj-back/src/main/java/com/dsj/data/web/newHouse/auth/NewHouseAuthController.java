package com.dsj.data.web.newHouse.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
import com.dsj.modules.newhouse.enums.NewHouseAuthStatusEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.other.service.GroupTypeService;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "back/**/newHouse/auth")
public class NewHouseAuthController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseAuthController.class);

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;

	@Autowired
	private NewHouseDirectoryAuthHistoryService newHouseDirectoryAuthHistoryService;

	@Autowired
	private GroupTypeService groupTypeService;

	@Autowired
	private NewHousePictureAuthService newHousePictureAuthService;

	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;

	@Autowired
	private NewHouseTypeAuthHistoryService newHouseTypeAuthHistoryService;

	@Autowired
	private NewHousePictureAuthHistoryService newHousePictureAuthHistoryService;

	@RequestMapping({ "newHouse_list", "" })
	public String newHouseList(Model model) {
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		return "newHouse/auth/newHouse_auth_list";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseAuthList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("authStatus", NewHouseAuthStatusEnum.WAIT_AUTH.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房审核列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("newHouse_auth_history_list")
	public String newHouseAuthHistoryList(Model model) {
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		return "newHouse/auth/newHouse_auth_history_list";
	}

	@RequestMapping("page/history_list")
	@ResponseBody
	public PageDateTable<?> newHouseAuthHistoryPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthHistoryService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房审核列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("newHouse_auth")
	public String newHouse_update(Model model, Long id) {
		NewHouseDirectoryAuthVo vo = newHouseDirectoryAuthService.getVoById(id);
		model.addAttribute("vo", vo);
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		model.addAttribute("dicTraitMap", dicTraitMap);
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		model.addAttribute("achTypeMap", achTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		return "newHouse/auth/newHouse_auth";
	}

	@RequestMapping("newHouse_history_auth")
	public String newHouseHistoryAuth(Model model, Long id) {
		NewHouseDirectoryAuthVo vo = newHouseDirectoryAuthHistoryService.getVoById(id);
		model.addAttribute("vo", vo);
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.WY_TYPE);
		model.addAttribute("wyTypeMap", wyTypeMap);
		Map<String, Object> dicTraitMap = groupTypeService.getDictrait();
		model.addAttribute("dicTraitMap", dicTraitMap);
		Map<String, Object> achTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ACHTYPE);
		model.addAttribute("achTypeMap", achTypeMap);
		Map<String, Object> propertyRightMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		model.addAttribute("propertyRightMap", propertyRightMap);
		model.addAttribute("history", "yes");
		return "newHouse/auth/newHouse_auth";
	}

	@RequestMapping("newHouse_pictures_history_auth")
	public String newHousePicturesHistoryAuth(Model model, long newHouseId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("newHouseId", newHouseId);
		hashMap.put("parentId", BusinessConst.PICTURE_STATUS);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureCountVo> countList = newHousePictureAuthHistoryService.getListCount(hashMap);
		model.addAttribute("countList", countList);
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("history", "yes");
		return "newHouse/auth/newHouse_pictures_auth";
	}

	@RequestMapping("newHouse_pictures_auth")
	public String picturesAdd(Model model, long newHouseId) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("newHouseId", newHouseId);
		hashMap.put("parentId", BusinessConst.PICTURE_STATUS);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureCountVo> countList = newHousePictureAuthService.getListCount(hashMap);
		model.addAttribute("countList", countList);
		model.addAttribute("newHouseId", newHouseId);
		return "newHouse/auth/newHouse_pictures_auth";
	}

	@RequestMapping("newHouse_auth_houseTypeList")
	public String newHouse_houseTypeList(Model model, Long newHouseId) {
		int totalCount = 0;
		for (int i = 1; i <= 5; i++) {
			List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.findHouseTypeList(i, newHouseId);
			long count = newHouseTypeAuthService.findHouseTypeCount(i, newHouseId);
			model.addAttribute("houseTypeList" + i, houseTypeList);
			model.addAttribute("count" + i, count);
			totalCount += count;
		}
		model.addAttribute("code", newHouseDirectoryAuthService.getById(newHouseId).getCode());
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("history", "no");
		return "newHouse/auth/newHouse_auth_houseTypeList";
	}

	@RequestMapping("newHouse_auth_history_houseTypeList")
	public String newHouseAuthHistoryHouseTypeList(Model model, Long newHouseId) {
		int totalCount = 0;
		for (int i = 1; i <= 5; i++) {
			List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthHistoryService.findHouseTypeList(i, newHouseId);
			long count = newHouseTypeAuthHistoryService.findHouseTypeCount(i, newHouseId);
			model.addAttribute("houseTypeList" + i, houseTypeList);
			model.addAttribute("count" + i, count);
			totalCount += count;
		}
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("history", "yes");
		return "newHouse/auth/newHouse_auth_houseTypeList";
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
		return "newHouse/auth/newHouse_pictures_detail";
	}

	@RequestMapping("newHouse_pictures_history_detail")
	public String newHousePicturesHistoryDetail(Model model, Long newHouseId, Long pictureStatus) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("objectId", newHouseId);
		hashMap.put("pictureStatus", pictureStatus);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHousePictureAuthVo> list = newHousePictureAuthHistoryService.listVoBy(hashMap);
		model.addAttribute("list", list);
		model.addAttribute("newHouseId", newHouseId);
		model.addAttribute("pictureStatus", pictureStatus);
		model.addAttribute("history", "yes");
		return "newHouse/auth/newHouse_pictures_detail";
	}

	@RequestMapping("newHouse_auth_newHouseType")
	public String newHouse_updateNewHouseType(Model model, long newHouseId) {
		NewHouseTypeAuthPo newHouseType = newHouseTypeAuthService.findOneNewHouse(newHouseId);
		NewHouseDirectoryAuthPo wyTypeMap = newHouseDirectoryAuthService.getWyType(newHouseType.getDicId());
		List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
		String wyType = wyTypeMap.getWyType();
		String[] wy = wyType.split(",");
		String wyTypeName = wyTypeMap.getWyTypeName();
		String[] wyTypeNames = wyTypeName.split(",");
		NewHouseDirectoryAuthPo newHouse = null;
		for (int i = 0; i < wy.length; i++) {
			newHouse = new NewHouseDirectoryAuthPo();
			newHouse.setWyType(wy[i]);
			newHouse.setWyTypeName(wyTypeNames[i]);
			wyList.add(newHouse);
		}
		model.addAttribute("wyList", wyList);
		model.addAttribute("newHouseType", newHouseType);
		model.addAttribute("newHouseId", wyTypeMap.getId());
		model.addAttribute("houseId", newHouseType.getId());
		model.addAttribute("history", "no");
		return "newHouse/auth/newHouseType_auth";
	}

	@RequestMapping("newHouse_auth_history_newHouseType")
	public String newHouseAuthHistoryNewHouseType(Model model, long newHouseId) {
		NewHouseTypeAuthHistoryPo newHouseType = newHouseTypeAuthHistoryService.getById(newHouseId);
		NewHouseDirectoryAuthHistoryPo wyTypeMap = newHouseDirectoryAuthHistoryService.getById(newHouseType.getDicId());
		List<NewHouseDirectoryAuthPo> wyList = new ArrayList<NewHouseDirectoryAuthPo>();
		String wyType = wyTypeMap.getWyType();
		String[] wy = wyType.split(",");
		String wyTypeName = wyTypeMap.getWyTypeName();
		String[] wyTypeNames = wyTypeName.split(",");
		NewHouseDirectoryAuthPo newHouse = null;
		for (int i = 0; i < wy.length; i++) {
			newHouse = new NewHouseDirectoryAuthPo();
			newHouse.setWyType(wy[i]);
			newHouse.setWyTypeName(wyTypeNames[i]);
			wyList.add(newHouse);
		}
		Map<String, Object> orientations = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		model.addAttribute("orientations", orientations);
		model.addAttribute("wyList", wyList);
		model.addAttribute("newHouseType", newHouseType);
		model.addAttribute("newHouseId", wyTypeMap.getId());
		model.addAttribute("houseId", newHouseType.getId());
		model.addAttribute("history", "yes");
		return "newHouse/auth/newHouseType_auth";
	}

	// 新房审核
	@RequestMapping("save_newHouse_auth")
	@ResponseBody
	public AjaxResultVo saveNewHouseAuth(NewHouseDirectoryAuthPo po) {
		AjaxResultVo ajax = new AjaxResultVo();
		List<NewHouseDirectoryAuthPo> list = new ArrayList<NewHouseDirectoryAuthPo>();
		try {
			list.add(po);
			//1.修改楼盘
			newHouseDirectoryAuthService.saveNewHousesAuth(list, ShiroUtils.getSessionUser().getId().longValue());
			//2.修改solr 楼盘未修改,不能更新solr
			if (po.getAuthStatus() == NewHouseAuthStatusEnum.AUTH_SUCCESS.getValue()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newHouseIds", po.getCode());
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return ajax;
	}

	// 新房批量审核
	@RequestMapping("save_newHouses_auth")
	@ResponseBody
	public AjaxResultVo saveNewHouseSAuth(@RequestBody List<NewHouseDirectoryAuthPo> list) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			//1.修改楼盘
			newHouseDirectoryAuthService.saveNewHousesAuth(list, ShiroUtils.getSessionUser().getId().longValue());
			//2.修改solr 楼盘未修改,不能更新solr
			if (list.get(0).getAuthStatus() == NewHouseAuthStatusEnum.AUTH_SUCCESS.getValue()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				String ids = "";
				for (NewHouseDirectoryAuthPo newHouseDirectoryAuthPo : list) {
					ids += "," + newHouseDirectoryAuthPo.getCode();
				}
				map.put("newHouseIds", ids.substring(1));
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("批量审核审核异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return ajax;
	}

	// 新房批量上架
	@RequestMapping("upNewHouses")
	@ResponseBody
	public AjaxResultVo upNewHouses() {
		AjaxResultVo ajax = new AjaxResultVo();
		int limitNum = 50;
		try {
			for(int i=0;i<40;i++){
				HashMap<String, Object> map = new HashMap<String, Object>();
				String upIds = newHouseDirectoryAuthService.getUpIds(limitNum*i,limitNum*i+50);
				if(StringUtils.isNotBlank(upIds)){
					map.put("newHouseIds", upIds);
				}else{
					break;
				}
				
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新房批量上架异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return ajax;
	}
}
