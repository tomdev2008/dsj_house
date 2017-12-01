package com.dsj.modules.fw.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.vo.FwOrderDetailVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:36.
 * @版本: 1.0 .
 */
public interface FwOrderDetailDao extends BaseDao<FwOrderDetailPo> {

	FwOrderDetailVo getFwComment(Long orderId);
	
}