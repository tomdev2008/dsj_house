package com.dsj.data.web.rentHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.jmx.Agent;
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
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.enums.HouseTypeEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.rent.enums.RentHouseStatusEnum;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.po.RentHousePicturePo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.service.RentHousePictureService;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;
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
	@Autowired
	private AgentService agentService;
	
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();

	static Map<String, Object> yesNoMap = Maps.newLinkedHashMap();

	static {
		for (int i = 1; i <= 10; i++) {
			roomMap.put(String.valueOf(i), i);
		}
	}
	
	//跳转列表页
	@RequestMapping({ "origin_list", "" })
	public String oldHouseList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "rentHouse/general/origin/origin_list";
	}

	//分页查询
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> newHouseDirectoryList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		PageBean page = null;
		try {
			page = rentHouseOriginService.listRentOriginPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("普通租房房源查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//回收站
	@RequestMapping({ "recycle_list"})
	public String recycleList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "rentHouse/general/origin/recycle_list";
	}
	
	//回收站分页
	@RequestMapping("page/recycle_list")
	@ResponseBody
	public PageDateTable<?> recycleList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		requestMap.put("deleteFlag", DeleteStatusEnum.DEL.getValue());
		PageBean page = null;
		try {
			page = rentHouseOriginService.listRentOriginPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("回收站普通租房房源查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//房源form跳转
	@RequestMapping("to_origin_add")
	public String originAdd(Model model, Long id , String show) {
		if (id != null) {
			RentHouseOriginPo rentOrigin = rentHouseOriginService.getById(id);
			setSetectModel(model);
			model.addAttribute("rentOrigin", rentOrigin);
			if(rentOrigin.getDicId()!=null){
				HouseDirectoryPo dic = houseDirectoryService.getById(rentOrigin.getDicId());
				model.addAttribute("dic", dic);
			}
			//关联的经纪人
			AgentVo agentVo = agentService.getAgentByHouseId(id);
			if (agentVo != null) {
				model.addAttribute("agentVo", agentVo);
			}
			if ("yes".equals(show)) {
				model.addAttribute("showOrigin", show);
			}
		}
		setSetectModel(model);
		if ("yes".equals(show)) {
			return "rentHouse/general/origin/origin_show";
		}else{
			return "rentHouse/general/origin/origin_add";
		}
	}
	
	//保存房源form表单
	@RequestMapping("saveOrUpdate")
	@ResponseBody
	public AjaxResultVo saveOrUpdate(RentHouseOriginPo po,Long agentUserSelect) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			if (po.getId() == null) {//新建房源
				po.setCreateTime(new Date());
				po.setUpdateTime(new Date());
				po.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
				po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
				po.setStatus(RentHouseStatusEnum.NOT_ON.getValue());
				Long id = rentHouseOriginService.saveDynamic(po);
				ajaxVo.setData(id);
				
				//插入经纪人房源维护中间表
				if (agentUserSelect != null) {
					rentHouseOriginService.saveAgentOrigin
						(HouseTypeEnum.RENT_ORIGIN.getValue(),id,
							agentUserSelect,ShiroUtils.getSessionUser().getId());
				}
			} else {//编辑房源
				po.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				po.setUpdateTime(new Date());
				rentHouseOriginService.updateDynamic(po);
				ajaxVo.setData(po.getId());
				
				//删除中间表中uerId再更新
				if (agentUserSelect != null) {
					rentHouseOriginService.deleteAgentOrigin
						(HouseTypeEnum.RENT_ORIGIN.getValue(),po.getId());
					rentHouseOriginService.saveAgentOrigin
						(HouseTypeEnum.RENT_ORIGIN.getValue(),po.getId(),
							agentUserSelect,ShiroUtils.getSessionUser().getId());
				}
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房保存修改错误", e);
		}
		return ajaxVo;
	}
	
	//删除房源
	@RequestMapping("origin_delete")
	@ResponseBody
	public AjaxResultVo originDelete(@RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHouseOriginService.updateDeleteFlag(ids,DeleteStatusEnum.DEL.getValue(),ShiroUtils.getSessionUser().getId());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房删除错误", e);
		}
		return ajaxVo;
	}
	
	//还原房源  把已删除的还原
	@RequestMapping("restore_origin")
	@ResponseBody
	public AjaxResultVo restoreOrigin(Model model, @RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHouseOriginService.updateDeleteFlag(ids, DeleteStatusEnum.NDEL.getValue(),ShiroUtils.getSessionUser().getId());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房还原错误", e);
		}
		return ajaxVo;
	}
	
	//房源上下架
	@RequestMapping("origin_upDown")
	@ResponseBody
	public AjaxResultVo originUpDown(@RequestParam("id[]")String[] id,Integer status) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHouseOriginService.updateOriginStatus(id,status);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("普通租房保存上下架错误", e);
		}
		return ajaxVo;
	}
	
	//跳转至图片页
	@RequestMapping("to_image_list")
	public String imageList(Model model, Long id,String show) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
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
			model.addAttribute("insideImages", insideImages);
			model.addAttribute("houseTypeImages", houseTypeImages);
			model.addAttribute("outInsideImages", outInsideImages);
			model.addAttribute("id", id);
			if ("yes".equals(show)) {
				model.addAttribute("showOrigin", show);
			}
		}
		return "rentHouse/general/origin/origin_image_list";
	}
	
	//图片新增页
	@RequestMapping("origin_image_add")
	public String imageAdd(Model model, Integer type, Long id) {
		model.addAttribute("type", type);
		model.addAttribute("id", id);
		return "rentHouse/general/origin/origin_image_add";
	}
	
	//保存图片
	@RequestMapping("update_origin_image")
	@ResponseBody
	public AjaxResultVo updateOriginImage(@RequestParam("imageUrls[]") String[] imageUrls,
			Integer pictureType, Long id) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			rentHousePictureService.updateRentImage(imageUrls, pictureType, id ,
					PictureObjectEnum.RENT_ORIGIN.getValue(),
					ShiroUtils.getSessionUser().getId().intValue());
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("保存二手房图片错误", e);
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		return ajaxVo;
	}
	
	//设置图片首页
	@RequestMapping("set_cover")
	@ResponseBody
	public AjaxResultVo setCover(Long id) {
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
	
	//删除图片
	@RequestMapping("delete_image")
	@ResponseBody
	public AjaxResultVo deleteImage(@RequestParam("ids[]") String[] ids) {
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
	/*@RequestMapping("publish_origin")
	@ResponseBody
	public AjaxResultVo publishOrigin(@RequestParam("id") String id) {
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
	}*/
	
	private void setSetectModel(Model model) {
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
		model.addAttribute("houseTypeMap", houseTypeMap);
		model.addAttribute("renvationMap", renvationMap);
		model.addAttribute("orientationsMap", orientationsMap);
		model.addAttribute("houseDeployMap", houseDeployMap);
		model.addAttribute("payTypeMap", payTypeMap);
		model.addAttribute("sexTypeMap", sexTypeMap);
		// 枚举
		model.addAttribute("yesNoMap", YesNoEnum.toMap());
		// 静态数据
		model.addAttribute("roomMap", roomMap);
	}
	
	/**
	 *下拉模糊查新
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
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("status", RentHouseStatusEnum.ON.getValue());
				map.put("name", name);
				List<RentHouseOriginPo> list = rentHouseOriginService.getRentHouse(map);
				for (RentHouseOriginPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getRoomName());
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
	 * 房源创建时关联的经纪人查询
	 */
	@RequestMapping("getAgentUser")
	@ResponseBody
	public AjaxResultVo getAgentUser(String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("delFlag", DeleteStatusEnum.NDEL.getValue() );
				map.put("userType", UserType.AGENT.getValue());
				map.put("name", name);
				List<AgentVo> list = agentService.getAgentBySelect(map);
				for (AgentVo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getUserId());
					vo.setText(po.getName()+"-"+po.getTellPhone()+"-"+po.getCompanyName());
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
	
}
