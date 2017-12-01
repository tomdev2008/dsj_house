package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.dao.HouseAlikeCommunityTempDao;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityTempService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class HouseAlikeCommunityTempServiceImpl  extends BaseServiceImpl<HouseAlikeCommunityTempDao,HouseAlikeCommunityTempPo> implements HouseAlikeCommunityTempService {

	@Override
	public List<HouseAlikeCommunityTempPo> selectList() {
		return dao.selectList();
	}

	@Override
	public HouseAlikeCommunityTempPo selectIdCommunit(long id) {
		return dao.getSelectid(id);
	}

	@Override
	public List<HouseAlikeCommunityTempPo> selectListCommunity(String name) {
      Map<String, Object> map=new HashMap<String, Object>();
      map.put("name", name);
		return dao.selectListCommunity(map);
	}
	


	
}