package com.dsj.modules.mobile400.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.mobile400.dao.AgentMobileDao;
import com.dsj.modules.mobile400.po.AgentMobilePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-15 15:08:50.
 * @版本: 1.0 .
 */
@Repository
public class AgentMobileDaoImpl extends BaseDaoImpl<AgentMobilePo> implements AgentMobileDao{

	@Override
	public long saveToLeadAgent() {
		return sessionTemplate.insert("toLeadAgent");
	}

}