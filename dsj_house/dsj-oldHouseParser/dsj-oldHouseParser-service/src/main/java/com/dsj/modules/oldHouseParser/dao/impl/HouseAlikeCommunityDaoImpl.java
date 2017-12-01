package com.dsj.modules.oldHouseParser.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.HouseAlikeCommunityDao;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class HouseAlikeCommunityDaoImpl extends BaseDaoImpl<HouseAlikeCommunityPo> implements HouseAlikeCommunityDao{

	@Override
	public List<HouseAlikeCommunityPo> getListCommunity(Map<String, Object> map) {
		return sessionTemplate.selectList("getListCommunity", map);
	}
	
}