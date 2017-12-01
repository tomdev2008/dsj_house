package com.dsj.data.web.content.houseNews;



import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.comment.vo.HouseNewsVo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.system.enums.UserType;
@Controller
@RequestMapping(value = "back/**/houseNews")
public class HouseNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseNewsController.class);
	@Autowired
	private HouseNewsService houseNewsService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	/**
	 * 楼盘动态列表页
	 * @return
	 */
	@RequestMapping("newsList")
	@ResponseBody
	public ModelAndView newsList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/houseNews/houseNews_list");
		return mav;
	}
	/**
	 * 楼盘动态 表数据
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("newsDataList")	
	@ResponseBody
	public PageDateTable<?> newsDataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = houseNewsService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("楼盘动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
    
	/**
	 * 楼盘动态未审核列表
	 * @return
	 */
	@RequestMapping("auditList")
	@ResponseBody
	public ModelAndView auditList(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/houseNews/audit_list");
		return mav;
	}
	/**
	 * 楼盘动态未审核 表数据
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("auditDataList")	
	@ResponseBody
	public PageDateTable<?> auditDataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("auditStatus", HouseNewsStatusEnum.UN_AUDIT.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = houseNewsService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	/**
	 * 添加楼房动态页面
	 * @return
	 */
	@RequestMapping("add")
	@ResponseBody
	public ModelAndView add(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/houseNews/houseNews_add");
		return mav;
	}
	/**
	 * 新增楼盘动态
	 * @param houseNews
	 * @return
	 */
	@RequestMapping("addHouseNews")
	@ResponseBody
	public AjaxResultVo addHouseNews(HouseNewsPo houseNews){
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNews.setCreateTime(new Date());
			houseNews.setUpdateTime(new Date());
			houseNews.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
			houseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
			houseNews.setUpDownLine(HouseNewsStatusEnum.NONE.getValue());
			houseNews.setDelFlag(DeleteStatusEnum.NDEL.getValue());
			houseNews.setType(UserType.EMPLOYEE.getValue());
			houseNews.setStick(0);
			houseNewsService.insertHouseNews(houseNews);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("新增楼盘动态异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	
	@RequestMapping("searchName")
	@ResponseBody
	public AjaxResultVo searchName(String name){
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			List<NewHouseDirectoryAuthPo> nameList = newHouseDirectoryAuthService.listBy(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(nameList);
		} catch (Exception e) {
			LOGGER.error("楼盘名称查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 编辑动态页面
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public ModelAndView edit(long id){
		ModelAndView mav = new ModelAndView();
		try {
			HouseNewsVo h = houseNewsService.getVoById(id);
			mav.addObject("houseNews", h);
		} catch (Exception e) {
			LOGGER.error("查询楼盘动态异常",e);
		}
		mav.setViewName("content/houseNews/houseNews_edit");
		return mav;
	}
	/**
	 * 更新楼盘动态
	 * @param houseNews
	 * @return
	 */
	@RequestMapping("updateHouseNews")
	@ResponseBody
	public AjaxResultVo updateHouseNews(HouseNewsPo houseNews){
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNews.setUpdateTime(new Date());
			houseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
			houseNewsService.updateDynamic(houseNews);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("更新楼盘动态异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 下线动态
	 * @param ids
	 * @return
	 */
	@RequestMapping("down")
	@ResponseBody
	public AjaxResultVo down(String ids){
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNewsService.updateMany(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("楼盘动态下线异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	/**
	 * 楼盘动态审核
	 * @param ids
	 * @return
	 */
	@RequestMapping("audit")
	@ResponseBody
	public AjaxResultVo audit(String ids,int status,String msg){
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNewsService.updateAuditMany(ids,status,msg,ShiroUtils.getSessionUser().getId().intValue());
			if(status==HouseNewsStatusEnum.AUDIT_SUCCESS.getValue()){
				String[] idArr = ids.split(",");
				for (String houseNewsId : idArr) {
					HouseNewsPo houseNewsPo = houseNewsService.getById(Long.parseLong(houseNewsId));
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("newHouseIds", houseNewsPo.getHouseId());
					newHouseDirectoryAuthService.saveNewHouseToSolr(map);
				}
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("楼盘动态审核异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	/**
	 * 审核页面
	 * @param id
	 * @return
	 */
	@RequestMapping("auditPage")
	@ResponseBody
	public ModelAndView auditPage(long id){
		ModelAndView mav = new ModelAndView();
		try {
			HouseNewsVo h = houseNewsService.getVoById(id);
			mav.setViewName("content/houseNews/houseNews_audit");
			mav.addObject("houseNews", h);
		} catch (Exception e) {
			LOGGER.error("查询楼盘动态异常", e);

		}	
		return mav;
	}
	/**
	 * 楼盘动态详情页
	 * @param id
	 * @return
	 */
	@RequestMapping("detail")
	@ResponseBody
	public ModelAndView detail(long id){
		ModelAndView mav = new ModelAndView();
		try {
			HouseNewsVo h = houseNewsService.getVoById(id);
			mav.setViewName("content/houseNews/houseNews_detail");
			mav.addObject("houseNews", h);
		} catch (Exception e) {
			LOGGER.error("查询楼盘动态异常", e);

		}	
		return mav;
	}
	
	/**
	 * 楼盘动态详情页
	 * @param id
	 * @return
	 */
	@RequestMapping("yulanNews")
	public String yulanNews(ServletRequest request,String conStr){
		request.setAttribute("conStr", conStr);
		return "content/houseNews/yulanNews";
	}
}
