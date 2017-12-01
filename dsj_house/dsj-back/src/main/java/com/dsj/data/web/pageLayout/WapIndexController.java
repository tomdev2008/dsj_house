package com.dsj.data.web.pageLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.pagelayout.enums.WapPublishStatusEnums;
import com.dsj.modules.pagelayout.po.WapItemSetPo;
import com.dsj.modules.pagelayout.po.WapLabelPo;
import com.dsj.modules.pagelayout.po.WapSwiperPo;
import com.dsj.modules.pagelayout.service.WapItemSetService;
import com.dsj.modules.pagelayout.service.WapLabelService;
import com.dsj.modules.pagelayout.service.WapSwiperService;
import com.dsj.modules.pagelayout.vo.LabelTypeVo;


@Controller
@RequestMapping(value = "back/**/wapIndex")
public class WapIndexController {
	private final Logger LOGGER = LoggerFactory.getLogger(WapIndexController.class);
	
	@Autowired
	private WapSwiperService wapSwiperService;
	@Autowired
	private WapLabelService wapLabelService;
	@Autowired
	private WapItemSetService wapItemSetService;
	
	@RequestMapping("indexManage")
	public ModelAndView indexManage(){
		ModelAndView mav = new ModelAndView();
		List<WapSwiperPo> list = wapSwiperService.listBy(null);
		List<WapLabelPo> list1 = wapLabelService.listBy(null);
		
		//List<WapLabelVo> list2 = wapLabelService.getNewList(list1);
		mav.addObject("swiperList",list);
		mav.addObject("LabelList",list1);
		mav.setViewName("pageLayout/wap/wapIndex");
		return mav;
	}
	/**
	 * wap首页轮播更新
	 * @param p
	 * @return
	 */
	@RequestMapping("updateSwiper")
	@ResponseBody
	public AjaxResultVo updateSwiper(WapSwiperPo p){
		AjaxResultVo result = new AjaxResultVo();
		try {
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			wapSwiperService.updateDynamic(p);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页轮播更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 获取轮播
	 * @param p
	 * @return
	 */
	@RequestMapping("getThisSwiper")
	@ResponseBody
	public AjaxResultVo getThisSwiper(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			
			result.setData(wapSwiperService.getById(id));
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取轮播异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 发布轮播图
	 * @param id
	 * @return
	 */
	@RequestMapping("publishSwiper")
	@ResponseBody
	public AjaxResultVo publishSwiper(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapSwiperPo p = new WapSwiperPo();
			p.setId(id);
			p.setStatus(WapPublishStatusEnums.PUBLISH.getValue());
			p.setUpdateTime(new Date());
			p.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			wapSwiperService.updateDynamic(p);			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页轮播发布异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 取消发布轮播图
	 * @param id
	 * @return
	 */
	@RequestMapping("cancleSwiper")
	@ResponseBody
	public AjaxResultVo cancleSwiper(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapSwiperPo p = new WapSwiperPo();
			p.setId(id);
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			p.setUpdateTime(new Date());
			p.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			wapSwiperService.updateDynamic(p);			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页轮播取消发布异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping("getLabelById")
	@ResponseBody
	public AjaxResultVo getLabelById(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapLabelPo p = wapLabelService.getById(id);
			result.setData(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页签更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * wap首页页签更新
	 * @param p
	 * @return
	 */
	@RequestMapping("updateLabel")
	@ResponseBody
	public AjaxResultVo updateLabel(WapLabelPo p){
		AjaxResultVo result = new AjaxResultVo();
		try {
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			wapLabelService.updateDynamic(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页签更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 发布Label
	 * @param id
	 * @return
	 */
	@RequestMapping("publishLabel")
	@ResponseBody
	public AjaxResultVo publishLabel(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapLabelPo p = new WapLabelPo();
			p.setId(id);
			p.setStatus(WapPublishStatusEnums.PUBLISH.getValue());
			p.setUpdateUser(ShiroUtils.getSessionUser().getId().intValue());
			wapLabelService.updateDynamic(p);			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页轮播发布异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 获取类型分组
	 * @return
	 */
	@RequestMapping("getTypeGroup")
	@ResponseBody
	public AjaxResultVo getTypeGroup(){
		AjaxResultVo result = new AjaxResultVo();
		try {

			List<String> list = wapLabelService.getTypeGroup();	
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("类型分组获取异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 获取所有类型
	 * @return
	 */
	@RequestMapping("getType")
	@ResponseBody
	public AjaxResultVo getType(){
		AjaxResultVo result = new AjaxResultVo();
		try {

			List<LabelTypeVo> list = wapLabelService.getType();	
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("类型获取异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * wap首页页签更新
	 * @param p
	 * @return
	 */
	@RequestMapping("updateTypes")
	@ResponseBody
	public AjaxResultVo updateTypes(WapLabelPo p){
		AjaxResultVo result = new AjaxResultVo();
		try {
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			wapLabelService.updateDynamic(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页签类型更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 获取前两项
	 * @param label_id
	 * @return
	 */
	
	@RequestMapping("getItem")
	@ResponseBody
	public AjaxResultVo getItem(Integer labelIndex){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("labelId", labelIndex);
			List<WapItemSetPo> list =  wapItemSetService.listBy(paramMap);
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页签类型更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 更新自定义项
	 * @param p
	 * @param smallPic
	 * @param manyPic
	 * @param bigPic
	 * @return
	 */
	@RequestMapping("updateItem")
	@ResponseBody
	public AjaxResultVo updateItem(WapItemSetPo p,String smallPic,String manyPic,String bigPic){
		AjaxResultVo result = new AjaxResultVo();
		try {
			if(p.getType()==WapPublishStatusEnums.BIGPICTURE.getValue()){
				p.setPicture(bigPic);
			}else if(p.getType()==WapPublishStatusEnums.SMALLPICTURE.getValue()){
				p.setPicture(smallPic);
			}else if(p.getType()==WapPublishStatusEnums.MANYPIC.getValue()){
				p.setPicture(manyPic);
			}
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			p.setCreateTime(new Date());
			wapItemSetService.updateDynamic(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页自定义项更新异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 发布自定义项
	 * @param id
	 * @return
	 */
	@RequestMapping("publishItem")
	@ResponseBody
	public AjaxResultVo publishItem(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapItemSetPo p = new WapItemSetPo();
			p.setId(id);
			p.setStatus(WapPublishStatusEnums.PUBLISH.getValue());
			wapItemSetService.updateDynamic(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页自定义项发布异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 取消发布自定义项
	 * @param id
	 * @return
	 */
	@RequestMapping("canclePublishItem")
	@ResponseBody
	public AjaxResultVo canclePublishItem(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapItemSetPo p = new WapItemSetPo();
			p.setId(id);
			p.setStatus(WapPublishStatusEnums.NOTPUBLISH.getValue());
			wapItemSetService.updateDynamic(p);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("wap首页页自定义项取消发布异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
}
