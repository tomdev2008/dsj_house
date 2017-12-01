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
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.redis.JedisProxy;
import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.serialize.SerializingUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.evaluate.service.AgentDailyScoreService;
import com.dsj.modules.oldhouse.enums.CertificateTypeEnum;
import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.enums.RoomNumber1Enum;
import com.dsj.modules.oldhouse.enums.RoomNumber2Enum;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.oldhouse.service.OldMasterAgentRecommendService;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.GroupTypePo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.google.common.collect.Maps;

/**
 * 新房管理
 */
@Controller
@RequestMapping(value = "back/**/oldHouse/master")
public class OldHouseMasterController {
	private final Logger LOGGER = LoggerFactory.getLogger(OldHouseMasterController.class);
	private static Integer pageSize=15;
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private OldHousePictureService oldHousePictureService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private OldMasterAgentRecommendService oldMasterAgentRecommendService;
	@Autowired
	private OldHouseAgentService oldHouseAgentService;
	@Autowired
	AgentService agentService;
	@Autowired
	AgentDailyScoreService agentDailyScoreService;
	
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


	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseDirectoryList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		System.out.println(aoData);
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		PageParam pageParam = new PageParam(pageNumber , pageSize);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("userId",ShiroUtils.getSessionUser().getId());
		//requestMap.put("agentId",ShiroUtils.getSessionUser().getAgentId());
		PageBean page = null;
		try {
			page = oldHouseMasterService.listAgentOldMasterPage(pageParam, requestMap);
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
	@RequestMapping("master_add")
	@ResponseBody
	public AjaxResultVo masterAdd(Model model) {
		Map<String,Object> map=new HashMap<String,Object>();
		AjaxResultVo vo=new AjaxResultVo();
		vo.setStatusCode(StatusCode.SUCCESS);
		setSetectModel(map);
		vo.setData(map);
		return vo;
	}
	
	/**
	 * 添加二手房页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_edit")
	@ResponseBody
	public AjaxResultVo masterEdit(Model model, Long id) {
		Map<String,Object> map=new HashMap<String,Object>();
		AjaxResultVo vo=new AjaxResultVo();
		vo.setStatusCode(StatusCode.SUCCESS);
		setSetectModel(map);
		OldHouseMasterPo oldMaster=oldHouseMasterService.getById(id);
		map.put("oldMaster", oldMaster);
		if(oldMaster.getDicId()!=null){
			map.put("dic", houseDirectoryService.getById(oldMaster.getDicId()));
		}
		vo.setData(map);
		return vo;
	}

	private void setSetectModel(Map<String,Object> mapType) {
		// 房屋类型
		Map<String, Object> houseTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_TYPE);
		// 装修情况
		Map<String, Object> renvationMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.RENVATION);
		// 朝向
		Map<String, Object> orientationsMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		// 产权
		Map<String, Object> certificateMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.CERTIFICATE);
		// 特色
		Map<String, Object> featuresMap =groupTypeService.getGroupTypeBackMapByPid(BusinessConst.FEATURES);
		mapType.put("houseTypeMap", houseTypeMap);
		mapType.put("renvationMap", renvationMap);
		mapType.put("orientationsMap", orientationsMap);
		mapType.put("certificateMap", certificateMap);
		mapType.put("featuresMap", featuresMap);

		// 枚举
		mapType.put("roomNo1Map", RoomNumber1Enum.toMap());
		mapType.put("roomNo2Map", RoomNumber2Enum.toMap());
		mapType.put("yesNoMap", YesNoEnum.toMap());
		mapType.put("certificateTypeMap", CertificateTypeEnum.toMap());
		
		mapType.put("floorTypeMap", FloorTypeEnum.toMap());
		// 静态数据
		mapType.put("roomMap", roomMap);
	
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
				po.setUserId(ShiroUtils.getSessionUser().getId());
				Long id = oldHouseMasterService.saveOldMaster(po,ShiroUtils.getSessionUser().getId());
				ajaxVo.setData(id);
				addDailyHouseScore(1);
				if(StringUtils.isNotBlank(po.getOwnerName())&&StringUtils.isNotBlank(po.getOwnerPhone())){
					addDailyHouseScore(2);
				}
			} else {
				oldHouseMasterService.updateDynamic(po);
				if(StringUtils.isNotBlank(po.getOwnerName())&&StringUtils.isNotBlank(po.getOwnerPhone())){
					addDailyHouseScore(2);
				}
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
	
	@RequestMapping("master_upDown")
	@ResponseBody
	public AjaxResultVo masterUpDown(@RequestParam("ids[]") String[] ids ,Integer status) {
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
	 * 字典加入redis
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("updateRedisDicType")
	@ResponseBody
	public AjaxResultVo updateRedisDicType() throws Exception {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("del", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("parentId", 0);
		List<GroupTypePo> pos = groupTypeService.listBy(paramMap);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		for (GroupTypePo po : pos) {
			paramMap.put("parentId", po.getId());
			List<GroupTypePo> pos1 = groupTypeService.listBy(paramMap);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (GroupTypePo po1 : pos1) {
				dataMap.put(po1.getId().toString(), po1.getTypegroupname());
			}
			resultmap.put(po.getId().toString(), dataMap);
		}
		JedisProxy.setByte(SerializingUtil.serialize(JedisConstant.DIC_TYPE), resultmap);
		LOGGER.info(JedisProxy.get(JedisConstant.DIC_TYPE).toString());
		return ajaxVo;
	}

	/**
	 * 二手房图片列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("master_image_list")
	@ResponseBody
	public AjaxResultVo imageList(Model model, Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String,Object> resultMap=new HashMap<String,Object>();
		try{
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
			
			
			resultMap.put("insideImages", insideImages);
			resultMap.put("houseTypeImages", houseTypeImages);
			resultMap.put("outInsideImages", outInsideImages);
		}
		}catch(Exception e){
			LOGGER.error("图片列表错误",e);
			 ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			 return ajaxVo;
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(resultMap);
		return ajaxVo;
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
		List<OldHousePicturePo> list=new ArrayList<OldHousePicturePo>();
		try {
			list=oldHousePictureService.updateMasterImage(imageUrls, pictureType, id);
			addDailyHouseScore(3);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("保存二手房图片错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(list);
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
	
	@RequestMapping("recomment")
	@ResponseBody
	public AjaxResultVo recomment(Model model, Long id,Integer type) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			//oldMasterAgentRecommendService.saveOrDel(id,type,ShiroUtils.getSessionUser().getId());
			if(type==YesNoEnum.YES.getValue()){
				Map<String,Object> paramMap=new HashMap<String,Object>();
				paramMap.put("userId", ShiroUtils.getSessionUser().getId());
				paramMap.put("isRecomend", type);
				List<OldHouseAgentPo> lists=oldHouseAgentService.listBy(paramMap);
				if(lists.size()>=10){
					ajaxVo.setStatusCode(StatusCode.AGENT_RECOMENT_COUNT);
					return ajaxVo;
				}
				
			}
			oldHouseAgentService.updateIsRecomend(id,type,ShiroUtils.getSessionUser().getId());
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("推荐出错啦", e);
			return ajaxVo;
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



	/**
	 * 展示在货架/取消展示
	 */
	@RequestMapping("showShelves")
	@ResponseBody
	public AjaxResultVo showShelves(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		int agentId = ShiroUtils.getSessionUser().getId().intValue();
		try {
			AgentDirectoryVo agentDirectoryVos = agentService.selectShowShelves(id,2);
			if (agentDirectoryVos == null) {
				AgentDirectoryVo agentDirectoryVo = new AgentDirectoryVo();
				agentDirectoryVo.setAgentId(agentId);
				agentDirectoryVo.setHouseId(id);
				agentDirectoryVo.setCreateTime(new Date());
				agentDirectoryVo.setType(2);
				long result = agentService.insertShowShelves(agentDirectoryVo);
				if (result > 0) {
					ajax.setStatusCode(StatusCode.SUCCESS);
					ajax.setMessage("展示成功");
				} else {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("展示失败");
				}
			} else {
				long result = agentService.deleteShowShelves(id,2);
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
				if(agentVo!=null){
					agentService.deleteShelves(agentVo.getId());
				}
			}
		} catch (Exception e) {
			LOGGER.info("系统错误", e.getMessage(), e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}	 
	//积分增加
	private void addDailyHouseScore(Integer itemMark){
		AgentPo agent=agentService.getByUserId(ShiroUtils.getSessionUser().getId());
		Long cityCode=null;
		if(StringUtils.isNotBlank(agent.getCity())){
			cityCode=Long.parseLong(agent.getCity());
		}
		String cityName="";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("areaCode", agent.getCity());
		if(StringUtils.isNotBlank( agent.getCity())){
			AreaPo area=areaService.getBy(paramMap);
			if(area!=null){
				cityName=area.getName();
			}
		}
		agentDailyScoreService.addDailyHouseScore(agent.getAgentCode().longValue(), agent.getName(), cityCode, cityName, itemMark, DateUtils.date2String(new Date()), ShiroUtils.getSessionUser().getId().intValue());
	}

}
