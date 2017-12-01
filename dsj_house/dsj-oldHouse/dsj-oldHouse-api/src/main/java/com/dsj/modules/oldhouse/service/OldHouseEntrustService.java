package com.dsj.modules.oldhouse.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:13:06.
 * @版本: 1.0 .
 */
public interface OldHouseEntrustService extends BaseService<OldHouseEntrustPo>{

	/**
	 * 自建分页查询
	 * @param pageParam
	 * @param requestMap
	 * @return
	 */
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	void updateOldHouseEntrust(String[] ids, int value);


	
}