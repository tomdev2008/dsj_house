package com.dsj.data.web.rentHouse;

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
import com.dsj.common.enums.RecommendEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.other.enums.HouseTypeEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.rent.enums.RentHouseStatusEnum;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.po.RentHousePicturePo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.service.RentHousePictureService;
import com.google.common.collect.Maps;

/**
 * 租房管理
 */
@Controller
@RequestMapping(value = "back/**/rentHouse/general/origin")
public class RentHouseOriginController {
	private final Logger LOGGER = LoggerFactory.getLogger(RentHouseOriginController.class);
	
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private RentHousePictureService rentHousePictureService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	@Autowired
	private AreaService areaService;
	
	private static Integer pageSize=8;
	
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();

	static Map<String, Object> yesNoMap = Maps.newLinkedHashMap();

	static {
		for (int i = 1; i <= 10; i++) {
			roomMap.put(String.valueOf(i), i);
		}
	}
	//查询列表
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseDirectoryList(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("agentUser", ShiroUtils.getSessionUser().getId());
		requestMap.put("houseType", HouseTypeEnum.RENT_ORIGIN.getValue());
		PageBean page = null;
		try {
			page = rentHouseOriginService.listAgentRentOriginPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("普通租房房源查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//跳转编辑新增页
	@RequestMapping("to_origin_add")
	@ResponseBody
	public AjaxResultVo originAdd(Long id ,String show) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			if (id != null) {
				RentHouseOriginPo rentOrigin = rentHouseOriginService.getById(id);
				map.put("rentOrigin", rentOrigin);
				map.put("show", show);
				if(rentOrigin.getDicId()!=null){
					HouseDirectoryPo dic = houseDirectoryService.getById(rentOrigin.getDicId());
					map.put("dic", dic);
				}
			}
			setSetectData(map);
			ajaxVo.setData(map);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房跳单页错误", e);
		}
		return ajaxVo;
	}
	
	//新增保存房源
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public AjaxResultVo saveOrUpdate(RentHouseOriginPo po) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			if (po.getId() == null) {
				po.setCreateTime(new Date());
				po.setUpdateTime(new Date());
				po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
				po.setStatus(RentHouseStatusEnum.NOT_ON.getValue());
				po.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
				Long id = rentHouseOriginService.saveDynamic(po);
				rentHouseOriginService.saveAgentOrigin
				(HouseTypeEnum.RENT_ORIGIN.getValue(),id,
						ShiroUtils.getSessionUser().getId(),
						ShiroUtils.getSessionUser().getId());
				ajaxVo.setData(id);
			} else {
				po.setUpdateTime(new Date());
				po.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				rentHouseOriginService.updateDynamic(po);
				ajaxVo.setData(po.getId());
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房保存修改错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 删除租房房源
	 */
	@RequestMapping("origin_delete")
	@ResponseBody
	public AjaxResultVo originDelete(@RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHouseOriginService.updateDeleteFlag
				(ids,DeleteStatusEnum.DEL.getValue(),ShiroUtils.getSessionUser().getId());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房删除错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 房源上下架
	 */
	@RequestMapping("origin_upDown")
	@ResponseBody
	public AjaxResultVo originUpDown(@RequestParam("ids[]") String[] ids ,Integer status) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHouseOriginService.updateOriginStatus(ids,status);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房保存上下架错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 推荐/取消推荐
	 */
	@RequestMapping("recommendMaster")
	@ResponseBody
	public AjaxResultVo recommendMaster(@RequestParam("ids[]") String[] ids ,Integer recommend) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			//推荐最大10个
			Integer count = rentHouseOriginService.getCountRecommend
				(ShiroUtils.getSessionUser().getId(),RecommendEnum.RECOMMEND.getValue());
			if (count<10) {
				rentHouseOriginService.updateOriginRecommend
					(ids,recommend,ShiroUtils.getSessionUser().getId());
			}
			ajaxVo.setData(count);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房推荐操作错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 设置展示首页房源
	 */
	@RequestMapping("showOrigin")
	@ResponseBody
	public AjaxResultVo showOrigin(@RequestParam("ids[]") String[] ids ,Integer show) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			if (show==1) { //展示操作
				//展示最多3个
				Integer count = rentHouseOriginService.getCountShow(ShiroUtils.getSessionUser().getId());
				ajaxVo.setData(count);
				if (count<3) {
					rentHouseOriginService.saveAgentShow
						(ids ,ShiroUtils.getSessionUser().getId());
				}
			}else if (show==2){ // 取消展示操作
				rentHouseOriginService.deleteAgentShow
					(ids ,ShiroUtils.getSessionUser().getId());
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房推荐操作错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 跳转到图片上传页
	 */
	@RequestMapping("to_image_list")
	@ResponseBody
	public AjaxResultVo imageList(Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		Map<String,Object> resultMap=new HashMap<String,Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try{
			if (id != null) {
				paramMap.put("objId", id);
				paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
				paramMap.put("pictureType", PictureTypeEnum.INSIDE.getValue());
				paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				List<RentHousePicturePo> insideImages = rentHousePictureService.listBy(paramMap);
				paramMap.put("pictureType", PictureTypeEnum.HOUSETYPE.getValue());
				List<RentHousePicturePo> houseTypeImages = rentHousePictureService.listBy(paramMap);
				paramMap.put("pictureType", PictureTypeEnum.OUTSIDE.getValue());
				List<RentHousePicturePo> outInsideImages = rentHousePictureService.listBy(paramMap);
	
				resultMap.put("insideImages", insideImages);
				resultMap.put("houseTypeImages", houseTypeImages);
				resultMap.put("outInsideImages", outInsideImages);
				
				ajaxVo.setStatusCode(StatusCode.SUCCESS);
				ajaxVo.setData(resultMap);
			}
		}catch(Exception e){
			LOGGER.error("图片列表错误",e);
			 ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			 return ajaxVo;
		}
		return ajaxVo;
	}
	
	/**
	 * 保存图片
	 */
	@RequestMapping("update_origin_image")
	@ResponseBody
	public AjaxResultVo updateOriginImage(@RequestParam("imageUrls[]") String[] imageUrls,
			Integer pictureType, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<RentHousePicturePo> list = new ArrayList<RentHousePicturePo>(); 
		try {
			list = rentHousePictureService.updateRentImage(imageUrls, pictureType, id ,
					PictureObjectEnum.RENT_ORIGIN.getValue(),
					ShiroUtils.getSessionUser().getId().intValue());
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("保存二手房图片错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(list);
		return ajaxVo;
	}
	
	/**
	 * 设置封面
	 */
	@RequestMapping("set_cover")
	@ResponseBody
	public AjaxResultVo setCover(Model model, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHousePictureService.updateCover(PictureObjectEnum.RENT_ORIGIN.getValue(),id);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("设置普通租房图片封面错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}
	
	/**
	 * 删除图片
	 */
	@RequestMapping("delete_image")
	@ResponseBody
	public AjaxResultVo deleteImage(Model model, @RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHousePictureService.updateDeleteFlag(ids, DeleteStatusEnum.DEL.getValue());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("删除普租房源图片错误", e);
		}
		return ajaxVo;
	}
	
	/**
	 * 发布房源
	 */
	@RequestMapping("publish_origin")
	@ResponseBody
	public AjaxResultVo publishOrigin(Model model, @RequestParam("id") String id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			RentHouseOriginPo po = rentHouseOriginService.getById(Long.parseLong(id));
			po.setStatus(RentHouseStatusEnum.ON.getValue());
			rentHouseOriginService.updateDynamic(po);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("发布普通房源错误", e);
		}
		return ajaxVo;
	}
	
	private void  setSetectData(Map<String, Object>  map) {
		// 房屋类型
		Map<String, Object> houseTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_TYPE);
		// 装修情况
		Map<String, Object> renvationMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.RENVATION);
		// 朝向
		Map<String, Object> orientationsMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		// 房屋配置
		Map<String, Object> houseDeployMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_DEPLOY);
		// 付款方式
		Map<String, Object> payTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PAY_TYPE);
		// 性别限制
		Map<String, Object> sexTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.SEX_TYPE);
		map.put("houseTypeMap", houseTypeMap);
		map.put("renvationMap", renvationMap);
		map.put("orientationsMap", orientationsMap);
		map.put("houseDeployMap", houseDeployMap);
		map.put("payTypeMap", payTypeMap);
		map.put("sexTypeMap", sexTypeMap);

		// 枚举
		map.put("yesNoMap", YesNoEnum.toMap());

		// 静态数据
		map.put("roomMap", roomMap);
	}
	
}
