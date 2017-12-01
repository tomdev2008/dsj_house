package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.NodeFieldDao;
import com.dsj.modules.fw.po.NodeFieldPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
@Repository
public class NodeFieldDaoImpl extends BaseDaoImpl<NodeFieldPo> implements NodeFieldDao{

	@Override
	public List<NodeFieldPo> listNewBy(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("listNewBy"), hashMap);
	}
	
}