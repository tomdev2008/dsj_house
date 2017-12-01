package com.dsj.modules.other.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.VerifyCodePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-16 11:58:07.
 * @版本: 1.0 .
 */
public interface VerifyCodeService extends BaseService<VerifyCodePo>{
	
	/**
	 * 查询新一条
	 * @param phone
	 * @return
	 */
	VerifyCodePo getVerifyByPhoneLast(String phone,Integer type);

	
}