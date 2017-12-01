package com.dsj.modules.fw.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.vo.FwOrderDetailVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 16:19:36.
 * @版本: 1.0 .
 */
public interface FwOrderDetailService extends BaseService<FwOrderDetailPo>{

	FwOrderDetailVo getFwComment(Long orderId);


	
}