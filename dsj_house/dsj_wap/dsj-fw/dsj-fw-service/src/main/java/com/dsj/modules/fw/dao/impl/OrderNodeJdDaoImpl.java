package com.dsj.modules.fw.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.OrderNodeJdDao;
import com.dsj.modules.fw.po.OrderNodeJdPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
@Repository
public class OrderNodeJdDaoImpl extends BaseDaoImpl<OrderNodeJdPo> implements OrderNodeJdDao{

	@Override
	public void updateByOrderDetailId(OrderNodeJdPo orderNodeJdPo) {
		sessionTemplate.update(getStatement("updateByOrderDetailId"), orderNodeJdPo);
	}
	
}