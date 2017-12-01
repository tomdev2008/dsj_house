package com.dsj.data.web.oldHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.redis.JedisProxy;
import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.one.RedisPoolUtil;
import com.dsj.common.utils.redis.serialize.SerializingUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.oldhouse.enums.CertificateTypeEnum;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.enums.RoomNumber1Enum;
import com.dsj.modules.oldhouse.enums.RoomNumber2Enum;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.oldhouse.vo.OldHouseAgentVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.oldhouse.vo.SelectIdVo;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.GroupTypePo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.google.common.collect.Maps;

/**
 * 新房管理
 */
@Controller
@RequestMapping(value = "back/**/oldHouse/master")
public class OldHouseMasterController {
	private final Logger LOGGER = LoggerFactory.getLogger(OldHouseMasterController.class);

	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private OldHousePictureService oldHousePictureService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private OldHouseAgentService oldHouseAgentService;
	
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
		return "oldHouse/master/master_list";
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
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
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
	 * 添加,修改二手房页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_add")
	public String masterAdd(Model model, Long id) {
		if (id != null) {
			OldHouseMasterPo oldMaster = oldHouseMasterService.getById(id);
			setSetectModel(model);
			model.addAttribute("oldMaster", oldMaster);
			if(oldMaster.getDicId()!=null){
				HouseDirectoryPo dic=houseDirectoryService.getById(oldMaster.getDicId());
				model.addAttribute("dic", dic);
			}
			
			
			OldHouseAgentVo agent=oldHouseAgentService.getByMasterIdAndNullUserId(id);
			if(agent!=null){
				SelectVo vo = new SelectVo();
				vo.setId(agent.getId());
				vo.setText(agent.getName()+"-"+agent.getTellPhone()+"-"+agent.getCompanyName());
			  model.addAttribute("agent", vo);
			}
		}
		setSetectModel(model);
		return "oldHouse/master/master_add";
	}
	
	/**
	 * 添加,修改二手房页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_view")
	public String masterView(Model model, Long id) {
		if (id != null) {
			OldHouseMasterPo oldMaster = oldHouseMasterService.getById(id);
			setSetectModel(model);
			if(StringUtils.isNotBlank(oldMaster.getFeatures())){
				oldMaster.setFeatures(","+oldMaster.getFeatures()+",");
			}
			model.addAttribute("oldMaster", oldMaster);
			if(oldMaster.getDicId()!=null){
				HouseDirectoryPo dic=houseDirectoryService.getById(oldMaster.getDicId());
				model.addAttribute("dic", dic);
			}
			
			
			OldHouseAgentVo agent=oldHouseAgentService.getByMasterIdAndNullUserId(id);
			if(agent!=null){
				SelectVo vo = new SelectVo();
				vo.setId(agent.getId());
				vo.setText(agent.getName()+"-"+agent.getTellPhone()+"-"+agent.getCompanyName());
			  model.addAttribute("agent", vo);
			}
		}
		setSetectModel(model);
		return "oldHouse/master/master_view";
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
		Map<String, Object> featuresMap = groupTypeService.getGroupTypeBackMapByPid(BusinessConst.FEATURES);
		model.addAttribute("houseTypeMap", houseTypeMap);
		model.addAttribute("renvationMap", renvationMap);
		model.addAttribute("orientationsMap", orientationsMap);
		model.addAttribute("certificateMap", certificateMap);
		model.addAttribute("featuresMap", featuresMap);

		// 枚举
		model.addAttribute("roomNo1Map", RoomNumber1Enum.toMap());
		model.addAttribute("roomNo2Map", RoomNumber2Enum.toMap());
		model.addAttribute("yesNoMap", YesNoEnum.toMap());
		model.addAttribute("floorTypeMap", FloorTypeEnum.toMap());
		model.addAttribute("certificateTypeMap", CertificateTypeEnum.toMap());

		// 静态数据
		model.addAttribute("roomMap", roomMap);
	}

	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public AjaxResultVo saveOrUpdate(OldHouseMasterPo po) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		po.setStatus(HouseStatusEnum.NOT_ON.getValue());
		try {
			if (po.getId() == null) {
				po.setCreateTime(new Date());
				po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			
				po.setHouseId(CodeUtils.getErshouCode());
				po.setCompanyType(CompanyTypeEnum.DSJ.getValue());
				po.setCompanyTypes(String.valueOf(CompanyTypeEnum.DSJ.getValue()));
				Long id = oldHouseMasterService.saveOldMaster(po,ShiroUtils.getSessionUser().getId());
				ajaxVo.setData(id);
			} else {
			
				oldHouseMasterService.updateOldMasterBack(po,ShiroUtils.getSessionUser().getId());
				ajaxVo.setData(po.getId());
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("二手房保存修改错误", e);
		}
		return ajaxVo;
	}
	
	@RequestMapping("master_delete")
	@ResponseBody
	public AjaxResultVo deleteMaster(@RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHouseMasterService.updateDeleteFlag(ids,DeleteStatusEnum.DEL.getValue());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("二手房保存删除错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 上下架
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping("master_upDown")
	@ResponseBody
	public AjaxResultVo masterUpDown(@RequestParam("ids[]") String[] ids,Integer status) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHouseMasterService.updateMasterStatus(ids,status);
			if(status==HouseStatusEnum.ON.getValue()){
				oldHouseMasterService.saveErshoufangSolr(ids);
			}else{
				oldHouseMasterService.deleteErshoufangSolr(ids);
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("二手房保存上下架错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 更新solr
	 * @param ids
	 * @param status
	 * @return
	 */
	@RequestMapping("update_solr")
	@ResponseBody
	public AjaxResultVo updateSolr() {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			int pageSize=100;
			for(int i=0;i<600;i++){
				Map<String,Object> paramMap=new HashMap<String,Object>();
				
				paramMap.put("status", HouseStatusEnum.ON.getValue());
				PageParam pageParam = new PageParam(i+ 1, pageSize);
				PageBean page=oldHouseMasterService.listPage(pageParam, paramMap);
				String ids="";
				if(page.getRecordList().size()>0){
					for(int j=0;j<page.getRecordList().size();j++){
						ids+=((OldHouseMasterPo)page.getRecordList().get(j)).getId()+",";
					}
					if(ids.length()>0){
						ids=ids.substring(0, ids.length()-1);
					}
					oldHouseMasterService.saveErshoufangSolr(ids.split(","));
				}
				ajaxVo.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("二手房Solr更新错误", e);
		}
		return ajaxVo;
	}

	/**
	 * 字典加入redis
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateRedisDicType")
	@ResponseBody
	public AjaxResultVo updateRedisDicType() throws Exception {
		AjaxResultVo ajaxVo = new AjaxResultVo();
	/*	*/
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap=groupTypeService.getGroupTypeByMysql();
		
		RedisPoolUtil.set(JedisConstant.DIC_TYPE, resultmap);
		LOGGER.info(RedisPoolUtil.getObj(JedisConstant.DIC_TYPE).toString());
		return ajaxVo;
	}
	
	
	/**
	 * 字典加入redis
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateErshouRedisDicType")
	@ResponseBody
	public AjaxResultVo updateErshoufangRedisDicType() throws Exception {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		
		Map<String, Object> resultmap = new HashMap<String, Object>();
		resultmap=groupTypeService.getFrontGroupType();
		
		RedisPoolUtil.set(JedisConstant.HOUSE_DIC_TYPE, resultmap);
	//	LOGGER.info(RedisPoolUtil.getObj(JedisConstant.HOUSE_DIC_TYPE).toString());
		return ajaxVo;
	}
	

	/**
	 * 二手房图片列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_image_list")
	public String imageList(Model model, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (id != null) {
			paramMap.put("objId", id);
			paramMap.put("objType", PictureObjectEnum.OLD_MASTER.getValue());
			paramMap.put("pictureType", PictureTypeEnum.INSIDE.getValue());
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<OldHousePicturePo> insideImages = oldHousePictureService.listBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.HOUSETYPE.getValue());
			List<OldHousePicturePo> houseTypeImages = oldHousePictureService.listBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.OUTSIDE.getValue());
			List<OldHousePicturePo> outInsideImages = oldHousePictureService.listBy(paramMap);

			model.addAttribute("insideImages", insideImages);
			model.addAttribute("houseTypeImages", houseTypeImages);
			model.addAttribute("outInsideImages", outInsideImages);
			model.addAttribute("id", id);
		}
		return "oldHouse/master/master_image_list";
	}
	

	/**
	 * 二手房图片列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_image_list_view")
	public String imageListView(Model model, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if (id != null) {
			paramMap.put("objId", id);
			paramMap.put("objType", PictureObjectEnum.OLD_MASTER.getValue());
			paramMap.put("pictureType", PictureTypeEnum.INSIDE.getValue());
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<OldHousePicturePo> insideImages = oldHousePictureService.listBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.HOUSETYPE.getValue());
			List<OldHousePicturePo> houseTypeImages = oldHousePictureService.listBy(paramMap);
			paramMap.put("pictureType", PictureTypeEnum.OUTSIDE.getValue());
			List<OldHousePicturePo> outInsideImages = oldHousePictureService.listBy(paramMap);

			model.addAttribute("insideImages", insideImages);
			model.addAttribute("houseTypeImages", houseTypeImages);
			model.addAttribute("outInsideImages", outInsideImages);
			model.addAttribute("id", id);
		}
		return "oldHouse/master/master_image_list_view";
	}

	/**
	 * 二手房图片添加页面
	 */
	@RequestMapping("master_image_add")
	public String imageAdd(Model model, Integer type, Long id) {
		model.addAttribute("type", type);
		model.addAttribute("id", id);
		return "oldHouse/master/master_image_add";
	}

	/**
	 * 新修改图片
	 * 
	 * @param model
	 * @param imageVo
	 * @return
	 */
	@RequestMapping("update_master_image")
	@ResponseBody
	public AjaxResultVo updateMasterImage(Model model, @RequestParam("imageUrls[]") String[] imageUrls,
			Integer pictureType, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHousePictureService.updateMasterImage(imageUrls, pictureType, id);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("保存二手房图片错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}

	@RequestMapping("delete_master_image")
	@ResponseBody
	public AjaxResultVo deleteMasterImage(Model model, @RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHousePictureService.deleteMasterImage(ids);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("删除二手房图片错误", e);
		}
		return ajaxVo;
	}

	@RequestMapping("set_cover")
	@ResponseBody
	public AjaxResultVo setCover(Model model, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			oldHousePictureService.updateCover(PictureObjectEnum.OLD_MASTER.getValue(),id);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("设置二手房图片封面错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}

	@RequestMapping("getDic")
	@ResponseBody
	public AjaxResultVo getDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				List<HouseDirectoryPo> list = houseDirectoryService.getByNamePreMatchding(name);

				for (HouseDirectoryPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getSprayName());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("设置二手房图片封面错误", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	@RequestMapping("getDicOldHouse")
	@ResponseBody
	public AjaxResultVo getDicOldHouse(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				List<HouseDirectoryPo> list = houseDirectoryService.getByNamePreMatchding(name);

				for (HouseDirectoryPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getSprayName());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("设置二手房图片封面错误", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}
	
	@RequestMapping("getPcOldHouse")
	@ResponseBody
	public AjaxResultVo getPcOldHouse(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectIdVo> selectList = new ArrayList<SelectIdVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				List<OldHouseMasterVo> list = oldHouseMasterService.getPcByNamePreMatchding(name);

				for (OldHouseMasterVo po : list) {
					SelectIdVo vo = new SelectIdVo();
					vo.setId(po.getHouseId());
					vo.setText(po.getSprayName());
					selectList.add(vo);
				}
			} catch (Exception e) {
				ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("查询二手房小区错误", e);
			}
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(selectList);
		return ajaxVo;
	}

	/**
	 * 新修改图片
	 * 
	 * @param model
	 * @param imageVo
	 * @return
	 *//*
		 * @RequestMapping("update_master_image")
		 * 
		 * @ResponseBody public AjaxResultVo updateMasterImage(Model model,
		 * OldMasterImageVo imageVo) { AjaxResultVo ajaxVo = new AjaxResultVo();
		 * oldHousePictureService.updateMasterImage(imageVo);
		 * ajaxVo.setStatusCode(StatusCode.SUCCESS); return ajaxVo; }
		 */

}
