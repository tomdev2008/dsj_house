package com.dsj.modules.pagelayout.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.PcAgentDao;
import com.dsj.modules.pagelayout.po.PcAgentPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Repository
public class PcAgentDaoImpl extends BaseDaoImpl<PcAgentPo> implements PcAgentDao{

	@Override
	public PcAgentPo getAgentOldHouse(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getAgentOldHouse", paramMap);
	}

	@Override
	public void updateAgentOldHouse(PcAgentPo pcAgentPo) {
		sessionTemplate.update("updateAgentOldHouse", pcAgentPo);
	}

	@Override
	public PcAgentPo getAgent(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getAgentPc", paramMap);
	}
	
}