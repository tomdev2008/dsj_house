package com.dsj.modules.rent.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.rent.dao.RentHouseRequireDao;
import com.dsj.modules.rent.po.RentHouseRequirePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
@Repository
public class RentHouseRequireDaoImpl extends BaseDaoImpl<RentHouseRequirePo> implements RentHouseRequireDao{

	@Override
	public void updateRentHouseRequire(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateRentHouseRequire"),map);
	}
	
}