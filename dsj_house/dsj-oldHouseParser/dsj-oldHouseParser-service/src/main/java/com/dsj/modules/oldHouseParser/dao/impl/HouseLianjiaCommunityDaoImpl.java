package com.dsj.modules.oldHouseParser.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.HouseLianjiaCommunityDao;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class HouseLianjiaCommunityDaoImpl extends BaseDaoImpl<HouseLianjiaCommunityPo> implements HouseLianjiaCommunityDao{

	@Override
	public HouseLianjiaCommunityPo selectLianjia(Map<String, Object> map) {
		return sessionTemplate.selectOne("selectLianjia", map);
	}

	@Override
	public HouseLianjiaCommunityPo selectLianjiaCommunity(Map<String, Object> map) {
		return sessionTemplate.selectOne("selectLianjiaCommunity", map);
	}
	
}