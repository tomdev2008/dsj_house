package com.dsj.modules.other.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.other.dao.TradeAreaDao;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.TradeAreaService;
import com.google.common.collect.Maps;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:24:01.
 * @版本: 1.0 .
 */
@Service
public class TradeAreaServiceImpl  extends BaseServiceImpl<TradeAreaDao,TradeAreaPo> implements TradeAreaService {
	@Autowired
	private TradeAreaDao tradeAreaDao;
	@Override
	public String findNameByTradeAreaCode(String tradeAreaCode) {
		String name  = tradeAreaDao.findNameByTradeAreaCode(tradeAreaCode);
		return name;
	}
	@Override
	public List<TradeAreaPo> listByIds(HashMap<String, Object> map2) {
		return tradeAreaDao.listByIds(map2);
	}
	@Override
	public TradeAreaPo getMaxIDArea() {
		return tradeAreaDao.getMaxIDArea();
	}
	
	@Override
	public Map<String, List<TradeAreaPo>> getMapByOrderByLp(Map<String, Object> mapArea) {
		Map<String, List<TradeAreaPo>> mapList=Maps.newLinkedHashMap();
		
		List<TradeAreaPo> lists=dao.listByOrderByLp(mapArea);
		for(TradeAreaPo po:lists){
			if(StringUtils.isNotBlank(po.getLikePinyin())){
				List<TradeAreaPo> tradeAreaList=null;
				if(mapList.get(po.getLikePinyin())==null){
					tradeAreaList=new ArrayList<TradeAreaPo>();
					tradeAreaList.add(po);
					mapList.put(po.getLikePinyin(), tradeAreaList);
				}else{
					tradeAreaList=mapList.get(po.getLikePinyin());
					tradeAreaList.add(po);
					mapList.put(po.getLikePinyin(), tradeAreaList);
				}
			}
		}
		return mapList;
	}
}




