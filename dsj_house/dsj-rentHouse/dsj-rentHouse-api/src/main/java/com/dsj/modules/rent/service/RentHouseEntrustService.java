package com.dsj.modules.rent.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.rent.po.RentHouseEntrustPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
public interface RentHouseEntrustService extends BaseService<RentHouseEntrustPo>{

	void updateRentHouseEntrust(String[] ids, int value);

}