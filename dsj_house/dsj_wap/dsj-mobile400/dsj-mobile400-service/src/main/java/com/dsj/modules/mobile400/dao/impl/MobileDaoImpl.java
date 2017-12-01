package com.dsj.modules.mobile400.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.mobile400.dao.MobileDao;
import com.dsj.modules.mobile400.po.MobilePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 13:41:36.
 * @版本: 1.0 .
 */
@Repository
public class MobileDaoImpl extends BaseDaoImpl<MobilePo> implements MobileDao{

	@Override
	public String getMaxMobile() {
		return sessionTemplate.selectOne(getStatement("getMaxMobile"));
	}

	@Override
	public void updateRemoveBinding(HashMap<String, Object> hashMap) {
		sessionTemplate.update(getStatement("updateRemoveBinding"), hashMap);
	}

	@Override
	public void updateMobileByFwId(MobilePo mobilePo) {
		sessionTemplate.update(getStatement("updateMobileByFwId"), mobilePo);
	}
	
}