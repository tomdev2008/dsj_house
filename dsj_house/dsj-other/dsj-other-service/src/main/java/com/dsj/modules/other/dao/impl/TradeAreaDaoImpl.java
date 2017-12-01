package com.dsj.modules.other.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.TradeAreaDao;
import com.dsj.modules.other.po.TradeAreaPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:24:01.
 * @版本: 1.0 .
 */
@Repository
public class TradeAreaDaoImpl extends BaseDaoImpl<TradeAreaPo> implements TradeAreaDao{
	@Override
	public String findNameByTradeAreaCode(String tradeAreaCode) {
		String name = sessionTemplate.selectOne(getStatement("findNameByTradeAreaCode"), tradeAreaCode);
		return name;
	}

	@Override
	public List<TradeAreaPo> listByIds(HashMap<String, Object> map2) {
		return sessionTemplate.selectList(getStatement("listByIds"), map2);
	}

	@Override
	public TradeAreaPo getMaxIDArea() {
		return sessionTemplate.selectOne(getStatement("getMaxIDArea"),new HashMap<String, Object>());
	}

	@Override
	public List<TradeAreaPo> listByOrderByLp(Map<String, Object> mapArea) {
		return sessionTemplate.selectList(getStatement("listByOrderByLp"), mapArea);
	}
}