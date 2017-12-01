package com.dsj.data.web.agent;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.service.HouseNewsService;


@Controller
@RequestMapping(value = "houseNews")
public class HouseNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseNewsController.class);
	
	@Autowired
	private HouseNewsService houseNewsservice; 
	
	int pageSize = 5;
	
	//楼盘动态查询
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(
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
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("createUser", ShiroUtils.getSessionUser().getId());
		PageBean page = null;
		try {
			page = houseNewsservice.listHouseNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
}
