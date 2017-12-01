package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.dao.HouseLianjiaCommunityDao;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;
import com.dsj.modules.oldHouseParser.service.HouseLianjiaCommunityService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class HouseLianjiaCommunityServiceImpl  extends BaseServiceImpl<HouseLianjiaCommunityDao,HouseLianjiaCommunityPo> implements HouseLianjiaCommunityService {


	@Override
	public HouseLianjiaCommunityPo selectLianjia(long lianjiaId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", lianjiaId);
		return dao.selectLianjia(map);
	}

	@Override
	public HouseLianjiaCommunityPo selectLianjiaCommunity(Long dicId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("dicId", dicId);
		return dao.selectLianjiaCommunity(map);
	}

	@Override
	public Long saveInsertLianjiaHouse(HouseLianjiaCommunityPo houseLianjiaCommunity) {
		return dao.insertDynamic(houseLianjiaCommunity);
	}
	
}