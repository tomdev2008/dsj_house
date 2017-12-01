package com.dsj.modules.oldHouseParser.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
public interface HouseAlikeCommunityDao extends BaseDao<HouseAlikeCommunityPo> {

	List<HouseAlikeCommunityPo> getListCommunity(Map<String, Object> map);
	
}