package com.dsj.modules.mobile400.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.mobile400.po.NewHouseMobilePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-16 14:47:02.
 * @版本: 1.0 .
 */
public interface NewHouseMobileService extends BaseService<NewHouseMobilePo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	PageBean listTotalNewPage(PageParam pageParam, Map<String, Object> requestMap);

	PageBean newHouseTotal(PageParam pageParam, Map<String, Object> requestMap);

	PageBean newHouseYesterday(PageParam pageParam, Map<String, Object> requestMap);

	void saveYearterDayData();


	
}