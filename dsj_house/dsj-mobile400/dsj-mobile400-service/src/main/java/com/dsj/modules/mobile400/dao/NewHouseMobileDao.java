package com.dsj.modules.mobile400.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.mobile400.po.NewHouseMobilePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-16 14:47:02.
 * @版本: 1.0 .
 */
public interface NewHouseMobileDao extends BaseDao<NewHouseMobilePo> {

	void saveYearterDayData();
	
}