package com.dsj.modules.oldHouseParser.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public interface IpPoolService extends BaseService<IpPoolPo>{
	
	IpPoolPo getByLast();


	
}