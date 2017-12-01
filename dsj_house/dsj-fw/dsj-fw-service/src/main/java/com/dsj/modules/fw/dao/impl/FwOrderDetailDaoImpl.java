package com.dsj.modules.fw.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwOrderDetailDao;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.vo.FwOrderDetailVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 16:19:36.
 * @版本: 1.0 .
 */
@Repository
public class FwOrderDetailDaoImpl extends BaseDaoImpl<FwOrderDetailPo> implements FwOrderDetailDao{

	@Override
	public FwOrderDetailVo getFwComment(Long orderId) {
		return sessionTemplate.selectOne("getFwComment", orderId);
	}
	
}