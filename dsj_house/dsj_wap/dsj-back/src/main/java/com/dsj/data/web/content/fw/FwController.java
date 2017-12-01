package com.dsj.data.web.content.fw;

import java.util.Date;
import java.util.HashMap;
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

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.FwContentStatusEnum;
import com.dsj.modules.comment.po.FwContentPo;
import com.dsj.modules.comment.service.FwContentService;
import com.dsj.modules.comment.vo.HouseNewsVo;

@Controller
@RequestMapping(value = "back/**/fwContent")
public class FwController {
	private final Logger LOGGER = LoggerFactory.getLogger(FwController.class);
	@Autowired
	private FwContentService fwContentService;
	
	
	@RequestMapping({ "fwContent_list", "" })
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/fw/fwContent_list");
		return mav;
	}
	
	@RequestMapping("newFwContent")
    public ModelAndView newFwContent(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("content/fw/fwContent_add");
		return mav;
	}
	
	@RequestMapping("pageList")
	@ResponseBody
	public PageDateTable<?> dataList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = fwContentService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("权证内容查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 新增权证内容
	 * @param houseNews
	 * @return
	 */
	@RequestMapping("addFwContent")
	@ResponseBody
	public AjaxResultVo addFwContent(FwContentPo fwContent){
		AjaxResultVo result = new AjaxResultVo();
		try {
			fwContent.setCreateTime(new Date());
			fwContent.setCreateUser(ShiroUtils.getSessionUser().getId());
			fwContent.setStatus(FwContentStatusEnum.WEI_SHANG_XIAN.getValue());
			long id = fwContentService.saveDynamic(fwContent);
			result.setData(id);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("存草稿异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	
	
	/**
	 * 发布
	 * @param houseNews
	 * @return
	 */
	@RequestMapping("faBuFwContent")
	@ResponseBody
	public AjaxResultVo faBuFwContent(FwContentPo fwContent){
		AjaxResultVo result = new AjaxResultVo();
		if(fwContent.getId()!=null ){
			try{
				fwContent.setStatus(FwContentStatusEnum.SHANG_XIAN.getValue());
				fwContent.setUpdateUser(ShiroUtils.getSessionUser().getId());
				fwContent.setUpdateTime(new Date());
				fwContentService.updateDynamic(fwContent);
				result.setStatusCode(StatusCode.SUCCESS);
			}catch (Exception e) {
				LOGGER.error("发布异常",e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}else{
			try {
				fwContent.setCreateTime(new Date());
				fwContent.setCreateUser(ShiroUtils.getSessionUser().getId());
				fwContent.setStatus(FwContentStatusEnum.SHANG_XIAN.getValue());
				fwContentService.saveDynamic(fwContent);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("发布异常",e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}

		return result;
	}
	
	/**
	 * 点击查看详情
	 * 
	 */
	@RequestMapping("xiangQing")
	public String xiangQing(Long id,Model model){
		FwContentPo fwContent = fwContentService.getById(id);
		model.addAttribute("fwContent", fwContent);
		return "content/fw/fwContent_xiangQing";
	}
	
	/**
	 * 下线
	 * @param ids
	 * @return
	 */
	@RequestMapping("down")
	@ResponseBody
	public AjaxResultVo down(String ids){
		AjaxResultVo result = new AjaxResultVo();
		try {
			fwContentService.updateMany(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("权证内容下线异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * 编辑
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public ModelAndView edit(long id){
		ModelAndView mav = new ModelAndView();
		try {
			FwContentPo fwContent = fwContentService.getById(id);
			mav.addObject("fwContent",fwContent);
		} catch (Exception e) {
			LOGGER.error("查询异常",e);
		}
		mav.setViewName("content/fw/fwContent_edit");
		return mav;
	}
		
}
