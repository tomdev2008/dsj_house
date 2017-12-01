package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.vo.FwOrderVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:35.
 * @版本: 1.0 .
 */
public interface FwOrderService extends BaseService<FwOrderPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	FwOrderVo getOrderDetailVoByDetailId(HashMap<String, Object> hashMap);

	void updateFwJd(Long orderId,Long currentNodeId, Long nodeId, Long id, String username);

	void updateEndFwJd(Long id,Long currentNodeId, Long orderDetailId, Long userId, String username);

	Long saveOrder(FwOrderPo order , Long skuId);

	List<FwOrderVo> findOrderPage(Map<String, Object> map);

	Long findOrderPageCount(Map<String, Object> map);

	PageBean listOrderPage(PageParam pageParam, Map<String, Object> map);

	FwOrderVo findVoByMap(Map<String, Object> map1);
	
	void updateDealOrderOverTime();

	void updateSucOrder(FwOrderPo orderPo, Long id, String username);

	void updateEndDynamic(FwOrderPo fwOrderPo);

	void updateOrderAndDetail(FwOrderPo orderPo);

	String updateDealOrderQueryTime();

}