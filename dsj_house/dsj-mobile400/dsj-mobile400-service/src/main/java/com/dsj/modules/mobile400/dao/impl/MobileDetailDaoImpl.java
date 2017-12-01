package com.dsj.modules.mobile400.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.mobile400.dao.MobileDetailDao;
import com.dsj.modules.mobile400.po.MobileDetailPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-20 13:41:49.
 * @版本: 1.0 .
 */
@Repository
public class MobileDetailDaoImpl extends BaseDaoImpl<MobileDetailPo> implements MobileDetailDao{

	@Override
	public MobileDetailPo getLastMobileDetailPo() {
		return sessionTemplate.selectOne(getStatement("getLastMobileDetailPo"));
	}
	
}