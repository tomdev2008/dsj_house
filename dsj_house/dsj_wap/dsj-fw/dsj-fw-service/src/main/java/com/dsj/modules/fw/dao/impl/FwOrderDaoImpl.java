package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwOrderDao;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.vo.FwOrderVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 16:19:35.
 * @版本: 1.0 .
 */
@Repository
public class FwOrderDaoImpl extends BaseDaoImpl<FwOrderPo> implements FwOrderDao{

	@Override
	public FwOrderVo getOrderDetailVoByDetailId(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectOne(getStatement("getOrderDetailVoByDetailId"), hashMap);
	}

	@Override
	public Long findOrderPageCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findOrderPageCount"), map);
	}

	@Override
	public List<FwOrderVo> findOrderPage(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findOrderPage"), map);
	}

	@Override
	public FwOrderVo findVoByMap(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findVoByMap"), map);
	}

	@Override
	public void updateOverTimeOrder() {
		sessionTemplate.update(getStatement("updateOverTimeOrder"));
	}

	@Override
	public String getDealOrderQueryIds() {
		return sessionTemplate.selectOne(getStatement("getDealOrderQueryIds"));
	}
	
}