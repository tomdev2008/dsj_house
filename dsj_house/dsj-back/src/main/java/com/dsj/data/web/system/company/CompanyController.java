package com.dsj.data.web.system.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dsj.common.enums.RoleChecked;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.enums.CompanyEnums;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.vo.RoleVo;


@Controller
@RequestMapping(value = "back/**/company")
public class CompanyController {
	private final Logger LOGGER = LoggerFactory.getLogger(CompanyController.class);
	@Autowired
	private CompanyService companyService;
	@Autowired
	private AreaService areaService;
	
	
	//经纪公司开始
	/**
	 * 经济公司列表页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("companyList")
    public ModelAndView agentList(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();

		mav.setViewName("system/company/company_list");
		return mav;
	}
	/**
	 * 经济公司列表页数据
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("companyType", CompanyEnums.AGENTCOMPANY.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = companyService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪公司查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 经济公司添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addPage")
    public ModelAndView addPage(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("添加经纪公司---地区查询异常", e);
		}
		mav.addObject("firstAreaList", firstAreaList);
		mav.setViewName("system/company/company_add");
		return mav;
	}
	/**
	 * 新增经纪公司
	 * @param company
	 * @return
	 */
	@RequestMapping("addCompany")
	@ResponseBody
    public AjaxResultVo addCompany(CompanyPo company) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			company.setCreatePreson(ShiroUtils.getSessionUser().getId().intValue());
			company.setCreateTime(new Date());
			company.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			company.setCompanyType(CompanyEnums.AGENTCOMPANY.getValue());
			companyService.insertCompany(company);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增经纪公司异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 经纪公司修改页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("editPage")
    public ModelAndView editPage(long id) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		CompanyPo c = companyService.getById(id);
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("编辑经纪公司--地区查询异常", e);
		}
		mav.addObject("company", c);
		mav.addObject("firstAreaList", firstAreaList);
		mav.setViewName("system/company/company_edit");
		return mav;
	}
	/**
	 * 经纪人公司修改提交
	 * @param company
	 * @return
	 */
	@RequestMapping("updateCompany")
	@ResponseBody
    public AjaxResultVo updateCompany(CompanyPo company) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			company.setUpdateTime(new Date());
			company.setUpdatePreson(ShiroUtils.getSessionUser().getId().intValue());
			companyService.updateDynamic(company);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("提交修改经纪公司异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	//经纪公司结束
	
	
	
	//商家开始
	/**
	 * 商家列表页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("businessList")
    public ModelAndView businessList(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();

		mav.setViewName("system/company/business_list");
		return mav;
	}
	/**
	 * 商家列表页数据
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("businessDataList")
	@ResponseBody
	public PageDateTable<?> businessDataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("companyType", CompanyEnums.BUSINESS.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = companyService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("商家查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 商家添加页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addBusinessPage")
    public ModelAndView addBusinessPage(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("添加经纪公司---地区查询异常", e);
		}
		mav.addObject("serviceNames",companyService.serviceTypeList());
		mav.addObject("firstAreaList", firstAreaList);
		mav.setViewName("system/company/business_add");
		return mav;
	}
	/**
	 * 新增商家
	 * @param company
	 * @return
	 */
	@RequestMapping("addBusiness")
	@ResponseBody
    public AjaxResultVo addBusiness(CompanyPo company) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			company.setCreatePreson(ShiroUtils.getSessionUser().getId().intValue());
			company.setCreateTime(new Date());
			company.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			company.setCompanyType(CompanyEnums.BUSINESS.getValue());
			companyService.insertCompany(company);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增商家异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 商家修改页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("editBusinessPage")
    public ModelAndView editBusinessPage(long id) { 
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		CompanyPo c = companyService.getById(id);
		
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			LOGGER.error("编辑商家--地区查询异常", e);
		}
		if(c.getServiceType()!=null){
			mav.addObject("serviceNames",companyService.editPageServiceTypeList(c.getServiceType().split(",")));
		}else{
			mav.addObject("serviceNames",companyService.serviceTypeList());
		}
		mav.addObject("company", c);
		mav.addObject("firstAreaList", firstAreaList);
		
		mav.setViewName("system/company/business_edit");
		return mav;
	}
	/**
	 * 商家修改提交
	 * @param company
	 * @return
	 */
	@RequestMapping("updateBusiness")
	@ResponseBody
    public AjaxResultVo updateBusiness(CompanyPo company) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			company.setUpdateTime(new Date());
			company.setUpdatePreson(ShiroUtils.getSessionUser().getId().intValue());
			companyService.updateDynamic(company);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("提交修改商家异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//商家结束
	
}