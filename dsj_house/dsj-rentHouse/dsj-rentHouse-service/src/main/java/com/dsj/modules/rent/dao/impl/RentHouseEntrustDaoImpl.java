package com.dsj.modules.rent.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.rent.dao.RentHouseEntrustDao;
import com.dsj.modules.rent.po.RentHouseEntrustPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
@Repository
public class RentHouseEntrustDaoImpl extends BaseDaoImpl<RentHouseEntrustPo> implements RentHouseEntrustDao{

	@Override
	public void updateRentHouseEntrust(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateRentHouseEntrust"),map);
	}
	
}