package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.dao.HouseAlikeCommunityDao;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class HouseAlikeCommunityServiceImpl  extends BaseServiceImpl<HouseAlikeCommunityDao,HouseAlikeCommunityPo> implements HouseAlikeCommunityService {

	@Override
	public void insertHouse(HouseAlikeCommunityPo houseAlikeCommunity) {
		dao.insertDynamic(houseAlikeCommunity);
	}

	@Override
	public List<HouseAlikeCommunityPo> selectListCommunity(String name) {
		Map<String, Object> map=new HashMap<>();
		map.put("name", name);
		return dao.getListCommunity(map);
	}
	
}