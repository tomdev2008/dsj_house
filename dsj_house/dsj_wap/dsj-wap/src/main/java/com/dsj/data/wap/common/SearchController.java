package com.dsj.data.wap.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.solr.service.CommonIndexService;
import com.dsj.modules.solr.vo.CommonIndexFiled;

/**
 * 新房管理
 */
@Controller
@RequestMapping(value = "keyword/search")
public class SearchController {
	private final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	CommonIndexService commonIndexService;

	@RequestMapping
	@ResponseBody
	public AjaxResultVo keywordIndex(String name){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		
		try{
			PageBean page=commonIndexService.getSearchSolr(10, name);
		
			ajaxVo.setData(page.getRecordList());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			LOGGER.error("联想查询错误",e);
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			return ajaxVo;
		}
		
		return ajaxVo;
	}
	
	
	@RequestMapping("newHouse")
	@ResponseBody
	public AjaxResultVo newHousekeywordIndex(String name){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		
		try{
			PageBean page=commonIndexService.getNewHouseSearchSolr(10, name);
		
			ajaxVo.setData(page.getRecordList());
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			LOGGER.error("联想查询错误",e);
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			return ajaxVo;
		}
		
		return ajaxVo;
	}
	

}
