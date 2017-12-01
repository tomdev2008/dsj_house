package com.dsj.modules.rent.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.rent.po.RentHouseRequirePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
public interface RentHouseRequireService extends BaseService<RentHouseRequirePo>{

	void updateRentHouseRequire(String[] ids, int value);
	
}