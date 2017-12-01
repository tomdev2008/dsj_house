package com.dsj.modules.mobile400.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.mobile400.po.AgentMobilePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-15 15:08:50.
 * @版本: 1.0 .
 */
public interface AgentMobileDao extends BaseDao<AgentMobilePo> {

	long saveToLeadAgent();
	
}