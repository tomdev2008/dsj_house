package com.dsj.modules.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.system.service.BusinessService;
import com.dsj.modules.system.dao.BusinessDao;
import com.dsj.modules.system.po.BusinessPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class BusinessServiceImpl  extends BaseServiceImpl<BusinessDao,BusinessPo> implements BusinessService {
	
}