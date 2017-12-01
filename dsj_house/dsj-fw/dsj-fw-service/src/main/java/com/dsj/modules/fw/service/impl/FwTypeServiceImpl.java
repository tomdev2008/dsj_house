package com.dsj.modules.fw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.service.FwTypeService;
import com.dsj.modules.fw.dao.FwTypeDao;
import com.dsj.modules.fw.po.FwTypePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 12:44:56.
 * @版本: 1.0 .
 */
@Service
public class FwTypeServiceImpl  extends BaseServiceImpl<FwTypeDao,FwTypePo> implements FwTypeService {
	
}