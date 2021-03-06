package com.dsj.modules.mobile400.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.mobile400.po.MobileDetailPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-20 13:41:49.
 * @版本: 1.0 .
 */
public interface MobileDetailService extends BaseService<MobileDetailPo>{

	void insertDatas();

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);


	
}