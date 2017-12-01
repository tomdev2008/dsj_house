package com.dsj.modules.fw.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.OrderNodeJdPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
public interface OrderNodeJdDao extends BaseDao<OrderNodeJdPo> {

	void updateByOrderDetailId(OrderNodeJdPo orderNodeJdPo);
	
}