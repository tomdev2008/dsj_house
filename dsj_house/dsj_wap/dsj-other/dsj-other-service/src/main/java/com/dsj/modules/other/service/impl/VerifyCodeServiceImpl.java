package com.dsj.modules.other.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.other.dao.VerifyCodeDao;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.VerifyCodeService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-16 11:58:07.
 * @版本: 1.0 .
 */
@Service
public class VerifyCodeServiceImpl  extends BaseServiceImpl<VerifyCodeDao,VerifyCodePo> implements VerifyCodeService {

	@Override
	public VerifyCodePo getVerifyByPhoneLast(String phone,Integer type) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("phone", phone);
		paramMap.put("type", type);
		return dao.getByLast(paramMap);
	}

}