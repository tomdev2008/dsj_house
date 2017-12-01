package com.dsj.modules.mobile400.service;

import java.util.Map;

import com.dsj.common.exceptions.CommonException;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.mobile400.po.MobilePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 13:41:36.
 * @版本: 1.0 .
 */
public interface MobileService extends BaseService<MobilePo>{

	void saveMobile(MobilePo po);

	boolean saveBindingMobile(MobilePo po) throws CommonException;

	boolean saveRemoveBindingMobile(Long id, Long long1) throws CommonException;

	void saveUpdateBindingMobile(MobilePo po) throws CommonException;

	void updateMobileByFwId(MobilePo mobilePo);

	void updateAgentPhone400(String oldPhone,String phone,Long userId);

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
}