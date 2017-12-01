package com.dsj.modules.comment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.comment.service.ClickHouseNewsService;
import com.dsj.modules.comment.dao.ClickHouseNewsDao;
import com.dsj.modules.comment.po.ClickHouseNewsPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-14 11:47:28.
 * @版本: 1.0 .
 */
@Service
public class ClickHouseNewsServiceImpl  extends BaseServiceImpl<ClickHouseNewsDao,ClickHouseNewsPo> implements ClickHouseNewsService {
	
}