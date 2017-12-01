package com.dsj.modules.other.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.TradeAreaPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:24:01.
 * @版本: 1.0 .
 */
public interface TradeAreaService extends BaseService<TradeAreaPo>{
	String findNameByTradeAreaCode(String tradeAreaCode);

	List<TradeAreaPo> listByIds(HashMap<String, Object> map2);

	TradeAreaPo getMaxIDArea();

	Map<String, List<TradeAreaPo>> getMapByOrderByLp(Map<String, Object> mapArea);

}