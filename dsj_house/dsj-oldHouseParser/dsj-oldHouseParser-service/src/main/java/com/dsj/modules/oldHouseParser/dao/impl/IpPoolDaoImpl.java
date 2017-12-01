package com.dsj.modules.oldHouseParser.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.IpPoolDao;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class IpPoolDaoImpl extends BaseDaoImpl<IpPoolPo> implements IpPoolDao{

	@Override
	public IpPoolPo getByLast() {
		return sessionTemplate.selectOne(getStatement("getByLast"));
	}
	
}