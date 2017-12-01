package com.dsj.modules.mobile400.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.mobile400.po.MobileDetailPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-20 13:41:50.
 * @版本: 1.0 .
 */
public interface MobileDetailDao extends BaseDao<MobileDetailPo> {

	MobileDetailPo getLastMobileDetailPo();
	
}