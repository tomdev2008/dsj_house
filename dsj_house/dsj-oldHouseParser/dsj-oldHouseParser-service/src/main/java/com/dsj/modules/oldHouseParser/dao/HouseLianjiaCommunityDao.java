package com.dsj.modules.oldHouseParser.dao;

import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
public interface HouseLianjiaCommunityDao extends BaseDao<HouseLianjiaCommunityPo> {

	HouseLianjiaCommunityPo selectLianjia(Map<String, Object> map);

	HouseLianjiaCommunityPo selectLianjiaCommunity(Map<String, Object> map);
	
}