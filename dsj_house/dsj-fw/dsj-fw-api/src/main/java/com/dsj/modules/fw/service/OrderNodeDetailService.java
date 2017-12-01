package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwNodeFieldPo;
import com.dsj.modules.fw.po.OrderNodeDetailPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
public interface OrderNodeDetailService extends BaseService<OrderNodeDetailPo>{

	void saveNodeList(HashMap<String, Object> map, Long id, String username);

	List<FwNodeFieldPo> getDateAndAddress(HashMap<String, Object> map);


	
}