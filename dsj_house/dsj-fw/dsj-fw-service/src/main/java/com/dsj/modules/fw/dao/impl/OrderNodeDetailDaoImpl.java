package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.OrderNodeDetailDao;
import com.dsj.modules.fw.po.FwNodeFieldPo;
import com.dsj.modules.fw.po.OrderNodeDetailPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
@Repository
public class OrderNodeDetailDaoImpl extends BaseDaoImpl<OrderNodeDetailPo> implements OrderNodeDetailDao{

	@Override
	public void saveList(List<OrderNodeDetailPo> orderNodeDetailList) {
		sessionTemplate.insert(getStatement("saveList"), orderNodeDetailList);
	}

	@Override
	public void deleteByOrderDetailIdAndNodeId(HashMap<String, Object> hashMap) {
		sessionTemplate.delete(getStatement("deleteByOrderDetailIdAndNodeId"), hashMap);
	}

	@Override
	public List<FwNodeFieldPo> getDateAndAddress(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getDateAndAddress"), map);
	}
	
}