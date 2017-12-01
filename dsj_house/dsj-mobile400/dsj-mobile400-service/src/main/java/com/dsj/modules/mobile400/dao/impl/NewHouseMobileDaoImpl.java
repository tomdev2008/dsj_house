package com.dsj.modules.mobile400.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.mobile400.dao.NewHouseMobileDao;
import com.dsj.modules.mobile400.po.NewHouseMobilePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-16 14:47:02.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseMobileDaoImpl extends BaseDaoImpl<NewHouseMobilePo> implements NewHouseMobileDao{

	@Override
	public void saveYearterDayData() {
		sessionTemplate.insert(getStatement("saveYearterDayData"));
	}
	
}