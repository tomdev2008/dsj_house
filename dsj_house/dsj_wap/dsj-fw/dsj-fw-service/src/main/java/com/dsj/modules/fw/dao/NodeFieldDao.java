package com.dsj.modules.fw.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.NodeFieldPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
public interface NodeFieldDao extends BaseDao<NodeFieldPo> {

	List<NodeFieldPo> listNewBy(HashMap<String, Object> hashMap);
	
}