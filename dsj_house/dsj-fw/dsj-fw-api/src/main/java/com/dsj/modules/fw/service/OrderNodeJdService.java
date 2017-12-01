package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.OrderNodeJdPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
public interface OrderNodeJdService extends BaseService<OrderNodeJdPo>{

	void updateByOrderDetailId(OrderNodeJdPo orderNodeJdPo);

	List<OrderNodeJdPo> listNewBy(HashMap<String, Object> hashMap);

}