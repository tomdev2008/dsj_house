package com.dsj.modules.oldHouseParser.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.HouseAlikeCommunityTempDao;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class HouseAlikeCommunityTempDaoImpl extends BaseDaoImpl<HouseAlikeCommunityTempPo> implements HouseAlikeCommunityTempDao{

	@Override
	public List<HouseAlikeCommunityTempPo> selectList() {
		return sessionTemplate.selectList("selectList");
	}

	@Override
	public HouseAlikeCommunityTempPo getSelectid(long id) {
		Map<String, Object> map=new HashMap<>();
		map.put("id", id);
		return sessionTemplate.selectOne("getSelectid", map);
	}

	@Override
	public List<HouseAlikeCommunityTempPo> selectListCommunity(Map<String, Object> map) {
		return sessionTemplate.selectList("selectListCommunity", map);
	}
	
}