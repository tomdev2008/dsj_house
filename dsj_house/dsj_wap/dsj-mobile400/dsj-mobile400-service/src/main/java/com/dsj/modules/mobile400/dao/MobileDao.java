package com.dsj.modules.mobile400.dao;

import java.util.HashMap;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.mobile400.po.MobilePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 13:41:36.
 * @版本: 1.0 .
 */
public interface MobileDao extends BaseDao<MobilePo> {

	String getMaxMobile();

	void updateRemoveBinding(HashMap<String, Object> hashMap);

	void updateMobileByFwId(MobilePo mobilePo);
	
}