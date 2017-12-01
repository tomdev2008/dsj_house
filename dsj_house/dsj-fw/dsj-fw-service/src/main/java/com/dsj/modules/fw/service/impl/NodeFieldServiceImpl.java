package com.dsj.modules.fw.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.service.NodeFieldService;
import com.dsj.modules.fw.dao.NodeFieldDao;
import com.dsj.modules.fw.po.NodeFieldPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
@Service
public class NodeFieldServiceImpl  extends BaseServiceImpl<NodeFieldDao,NodeFieldPo> implements NodeFieldService {

	@Override
	public List<NodeFieldPo> listNewBy(HashMap<String, Object> hashMap) {
		return dao.listNewBy(hashMap);
	}
	
}