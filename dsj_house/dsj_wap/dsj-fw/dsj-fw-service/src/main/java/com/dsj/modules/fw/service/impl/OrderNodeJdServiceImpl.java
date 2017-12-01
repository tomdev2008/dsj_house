package com.dsj.modules.fw.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.dao.OrderNodeJdDao;
import com.dsj.modules.fw.enums.NodeStatusEnum;
import com.dsj.modules.fw.po.FwNodeFieldPo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.OrderNodeDetailService;
import com.dsj.modules.fw.service.OrderNodeJdService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:04.
 * @版本: 1.0 .
 */
@Service
public class OrderNodeJdServiceImpl  extends BaseServiceImpl<OrderNodeJdDao,OrderNodeJdPo> implements OrderNodeJdService {

	@Autowired
	private OrderNodeDetailService orderNodeDetailService;
	
	@Override
	public void updateByOrderDetailId(OrderNodeJdPo orderNodeJdPo) {
		dao.updateByOrderDetailId(orderNodeJdPo);
	}

	@Override
	public List<OrderNodeJdPo> listNewBy(HashMap<String, Object> hashMap) {
		List<OrderNodeJdPo> list = dao.listBy(hashMap);
		HashMap<String, Object> map = new HashMap<String,Object>();
		for (OrderNodeJdPo orderNodeJdPo : list) {
			//获取地址跟时间
			map.put("orderDetailId", orderNodeJdPo.getOrderDetailId());
			map.put("nodeId", orderNodeJdPo.getNodeId());
			List<FwNodeFieldPo> fieldList = orderNodeDetailService.getDateAndAddress(map);
			orderNodeJdPo.setFieldList(fieldList);
		}
		return list;
	}
	
}