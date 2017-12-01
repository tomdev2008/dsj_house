package com.dsj.modules.rent.dao;

import java.util.HashMap;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.rent.po.RentHouseEntrustPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
public interface RentHouseEntrustDao extends BaseDao<RentHouseEntrustPo> {

	void updateRentHouseEntrust(HashMap<String, Object> map);
	
}