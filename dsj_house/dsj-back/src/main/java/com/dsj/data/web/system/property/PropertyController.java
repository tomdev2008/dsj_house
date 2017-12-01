package com.dsj.data.web.system.property;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.service.UploadService;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileService;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.oldhouse.vo.SelectVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.enums.CompanyEnums;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.PropertyVo;

/**
 * 权证代办人管理
 */
@Controller
@RequestMapping(value = "back/**/system/property")
public class PropertyController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(PropertyController.class);
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private FwSpuService fwSpuService;
	@Autowired
	private MobileService mobileService;
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping("propertyList")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/property/property_list");
		return mav;
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> adminList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = propertyService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("权证代办人查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("property_add")
	public String toEvelopersAdd(Model model,HttpServletRequest request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<CompanyPo> companyList = null;
		List<FwSpuPo> fwSpuList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		try {
			map1.put("companyType", CompanyEnums.BUSINESS.getValue());
			companyList = companyService.listBy(map1);
		} catch (Exception e) {
			LOGGER.error("商家查询异常", e);
		}
		try {
			fwSpuList = fwSpuService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("服务查询异常", e);
		}
		model.addAttribute("fwSpuList",fwSpuList);
		model.addAttribute("firstAreaList", firstAreaList);
		model.addAttribute("companyLisy", companyList);
		return "system/property/property_add";
	}
	
	
	/*@RequestMapping("authPhone")
	@ResponseBody
	public AjaxResultVo authPhone(String phone){
		AjaxResultVo ajax = new AjaxResultVo();
		try{
			long count = propertyService.getPoCountByPhone(phone);
			if(count>0){
				ajax.setData("<span style='color:red'>手机号已被注册</span>");
			}else{
				
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}*/
	
	@RequestMapping("save_property_add")
	@ResponseBody
	public AjaxResultVo savePropertyAdd(HttpServletRequest request, PropertyVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
				map.put("phone", vo.getTellPhone());
				long count = propertyService.getPoCountByPhone(map);
				if(count>0){
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setMessage("手机号已被注册");
				}else{
					String avatarUrl=uploadService.uploadHeadProtrait(vo.getAvatarUrl());//正方形
					String avatarReUrl=uploadService.uploadHeadProtrait(vo.getAvatarReUrl());//长方形
					vo.setAvatarUrl(avatarUrl);
					vo.setAvatarReUrl(avatarReUrl);
					vo.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
					String a = vo.getArea().substring(1);
					String[] area = a.split(",");
					AreaPo areaPo ;
					String newArea = "";
					for(String str:area){
						map.clear();
						map.put("areaCode", str);
						areaPo = areaService.getBy(map);
						newArea+=","+areaPo.getName();
					}
					vo.setArea(a);
					vo.setAreaName(newArea.substring(1));
					propertyService.savePropertyAdd(vo);
					ajax.setStatusCode(StatusCode.SUCCESS);
				}
		} catch (Exception e) {
			LOGGER.error("添加代办人账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("property_update")
    public ModelAndView edit(long id) { 
		ModelAndView mav = new ModelAndView();
		PropertyPo property = propertyService.getById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> twoMap = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		twoMap.put("parentId", property.getCity());
		twoMap.put("isCustom", AreaEnum.IS_CUSTOM_NO.getValue());
		twoMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<AreaPo> threeAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
			threeAreaList = areaService.listBy(twoMap);

		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
		}
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		List<CompanyPo> companyList = null;
		try {
			map1.put("companyType", CompanyEnums.BUSINESS.getValue());
			companyList = companyService.listBy(map1);
		} catch (Exception e) {
			LOGGER.error("商家查询异常", e);
		}
		List<FwSpuPo> fwSpuList = null;
		try {
			fwSpuList = fwSpuService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("服务查询异常", e);
		}
		mav.addObject("fwSpuList",fwSpuList);
		mav.addObject("companyList", companyList);
		property.setProvinceName(areaService.findNameByAreaCode(property.getProvince()));
		property.setCityName(areaService.findNameByAreaCode(property.getCity()));
		String[] areaIds = property.getArea().split(",");
		String newName = "";
		for(String str:areaIds){
			if (StringUtils.isNotBlank(str)) {
				newName += ","+areaService.findNameByAreaCode(str);
			}
		}
		
		property.setAreaName(newName.substring(1));
		mav.addObject("firstAreaList", firstAreaList);
		mav.addObject("property", property);
		mav.addObject("threeAreaList", threeAreaList);
		mav.setViewName("system/property/property_update");
		return mav;
	}
	
	
	@RequestMapping("save_property_update")
	@ResponseBody
    public AjaxResultVo updateAgent(PropertyPo property) { 
		AjaxResultVo result = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			map.put("phone", property.getTellPhone());
			map.put("id", property.getUserId());
			long count = propertyService.getPoCountByPhone(map);
			if(count>0){
				result.setStatusCode(StatusCode.SERVER_ERROR);
				result.setMessage("手机号已被注册");
			}else{
				if(property.getAvatarUrl().contains("data:image/png;base64")){
					String avatarUrl=uploadService.uploadHeadProtrait(property.getAvatarUrl());//正方形
					String avatarReUrl=uploadService.uploadHeadProtrait(property.getAvatarReUrl());//长方形
					property.setAvatarUrl(avatarUrl);
					property.setAvatarReUrl(avatarReUrl);
				}
				String a = property.getArea().substring(1);
				String[] area = a.split(",");
				AreaPo areaPo ;
				String newArea = "";
				for(String str:area){
					map.put("areaCode", str);
					areaPo = areaService.getBy(map);
					newArea+=","+areaPo.getName();
				}
				property.setArea(a);
				property.setAreaName(newArea.substring(1));
				property.setUpdateTime(new Date());
				property.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				propertyService.updateDynamic(property);
				//修改用户表中代办人的信息
				UserPo user = new UserPo();
				user.setId(property.getUserId());
				user.setRealname(property.getName());
				user.setUsername(property.getTellPhone());
				user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				user.setAvatar(property.getAvatarUrl());
				user.setPhone(property.getTellPhone());
				userService.updateDynamic(user);
				//修改400
				HashMap<String, Object> mobileMap = new HashMap<String,Object>();
				mobileMap.put("propertyId", property.getId());
				MobilePo mobile = mobileService.getBy(mobileMap);
				if(mobile!=null){
					mobile.setPropertyName(property.getName());
					mobile.setPhone(property.getTellPhone());
					mobileService.updateMobileByFwId(mobile);
				}
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("编辑代办人异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
	/*
	 * 下架代办人
	 * */
	@RequestMapping("xiaJiaProperty")
	@ResponseBody
    public AjaxResultVo xiaJiaProperty(Integer id) { 
		AjaxResultVo result = new AjaxResultVo();

		try {
			PropertyPo property = propertyService.getById(id);
			property.setUpdateTime(new Date());
			property.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			property.setAuditStatus(0);
			propertyService.updateDynamic(property);
			result.setStatusCode(StatusCode.SUCCESS);

		} catch (Exception e) {
			LOGGER.error("下架代办人异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
	/*
	 * 上架代办人
	 * */
	@RequestMapping("shangJiaProperty")
	@ResponseBody
    public AjaxResultVo shangJiaProperty(Integer id) { 
		AjaxResultVo result = new AjaxResultVo();

		try {
			PropertyPo property = propertyService.getById(id);
			property.setUpdateTime(new Date());
			property.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			property.setAuditStatus(1);
			propertyService.updateDynamic(property);
			UserPo userPo = userService.getById(property.getUserId());
			userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
			userService.updateDynamic(userPo);
			result.setStatusCode(StatusCode.SUCCESS);

		} catch (Exception e) {
			LOGGER.error("上架代办人异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
	/*
	 * 关闭代办人
	 * */
	@RequestMapping("guanBiProperty")
	@ResponseBody
    public AjaxResultVo guanBiProperty(Integer id) { 
		AjaxResultVo result = new AjaxResultVo();

		try {
			List<Integer> idList = propertyService.getProIdByOrderStatus();
			if(idList.contains(id)){
				result.setMessage("有在办订单，无法关闭账号");
			}else{
				PropertyPo property = propertyService.getById(id);
				property.setUpdateTime(new Date());
				property.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				property.setAuditStatus(2);
				propertyService.updateDynamic(property);
				UserPo userPo = userService.getById(property.getUserId());
				userPo.setDelFlag(DeleteStatusEnum.DEL.getValue());
				userService.updateDynamic(userPo);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("关闭代办人异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/*
	 * 推荐
	 * */
	@RequestMapping("tuiJianProperty")
	@ResponseBody
    public AjaxResultVo tuiJianProperty(Integer id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			int count = propertyService.getTuiJianPropertyCount();
			if(count < 4){
				PropertyPo property = propertyService.getById(id);
				property.setUpdateTime(new Date());
				property.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				property.setRecommend(1);
				property.setRecommendTime(new Date());
				propertyService.updateDynamic(property);
				result.setStatusCode(StatusCode.SUCCESS);
			}else{
				PropertyPo property = propertyService.getTuiJianTimeLimitYi();
				property.setRecommend(0);
				propertyService.updateDynamic(property);
				PropertyPo propertyPo = propertyService.getById(id);
				propertyPo.setUpdateTime(new Date());
				propertyPo.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
				propertyPo.setRecommend(1);
				propertyPo.setRecommendTime(new Date());
				propertyService.updateDynamic(propertyPo);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("推荐代办人异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 *下拉模糊查询
	 * @param 
	 * @return
	 */
	@RequestMapping("getProperty")
	@ResponseBody
	public AjaxResultVo getDic(Model model, String name) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		List<SelectVo> selectList = new ArrayList<SelectVo>();
		if (StringUtils.isNotBlank(name)) {
			try {
				HashMap<String, Object> map = new HashMap<String,Object>();
				map.put("preName", name);
				map.put("delFlag", DeleteStatusEnum.NDEL.getValue());
				List<PropertyPo> list = propertyService.listNewBy(map);
				for (PropertyPo po : list) {
					SelectVo vo = new SelectVo();
					vo.setId(po.getId());
					vo.setText(po.getName());
					vo.setCode(po.getId().toString());
					vo.setPhone(po.getTellPhone());
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
	 * 重置密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("resetPassword")
	@ResponseBody
    public AjaxResultVo resetPassword(String id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			propertyService.updateResetPwdMany(id);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("重置密码异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
	/*
	 * 取消推荐
	 * */
	@RequestMapping("delTJ")
	@ResponseBody
	public AjaxResultVo delTJ(Integer id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			PropertyPo propertyPo = propertyService.getById(id);
			propertyPo.setRecommend(0);
			propertyService.updateDynamic(propertyPo);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("取消推荐异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
}
