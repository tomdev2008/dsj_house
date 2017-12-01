package com.dsj.modules.fw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.vo.FwOrderVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:35.
 * @版本: 1.0 .
 */
public interface FwOrderDao extends BaseDao<FwOrderPo> {

	FwOrderVo getOrderDetailVoByDetailId(HashMap<String, Object> hashMap);

	Long findOrderPageCount(Map<String, Object> map);

	List<FwOrderVo> findOrderPage(Map<String, Object> map);

	FwOrderVo findVoByMap(Map<String, Object> map);

	void updateOverTimeOrder();

	String getDealOrderQueryIds();

}