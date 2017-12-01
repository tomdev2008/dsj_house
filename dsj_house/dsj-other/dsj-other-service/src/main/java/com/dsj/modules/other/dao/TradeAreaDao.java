package com.dsj.modules.other.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.TradeAreaPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:24:01.
 * @版本: 1.0 .
 */
public interface TradeAreaDao extends BaseDao<TradeAreaPo> {
	String findNameByTradeAreaCode(String TradeAreaCode);

	List<TradeAreaPo> listByIds(HashMap<String, Object> map2);

	TradeAreaPo getMaxIDArea();

	List<TradeAreaPo> listByOrderByLp(Map<String, Object> mapArea);
}