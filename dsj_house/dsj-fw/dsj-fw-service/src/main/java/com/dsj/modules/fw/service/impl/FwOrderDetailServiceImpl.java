package com.dsj.modules.fw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.vo.FwOrderDetailVo;
import com.dsj.modules.fw.dao.FwOrderDetailDao;
import com.dsj.modules.fw.po.FwOrderDetailPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 16:19:36.
 * @版本: 1.0 .
 */
@Service
public class FwOrderDetailServiceImpl  extends BaseServiceImpl<FwOrderDetailDao,FwOrderDetailPo> implements FwOrderDetailService {

	@Override
	public FwOrderDetailVo getFwComment(Long orderId) {
		return dao.getFwComment(orderId);
	}
	
}