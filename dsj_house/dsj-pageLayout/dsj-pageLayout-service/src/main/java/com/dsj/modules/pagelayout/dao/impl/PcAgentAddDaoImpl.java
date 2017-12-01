package com.dsj.modules.pagelayout.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.PcAgentAddDao;
import com.dsj.modules.pagelayout.po.PcAgentAddPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Repository
public class PcAgentAddDaoImpl extends BaseDaoImpl<PcAgentAddPo> implements PcAgentAddDao{

	@Override
	public List<PcAgentAddPo> getAgent() {
		return sessionTemplate.selectList("getAgent");
	}

	@Override
	public void updateAgentPage(PcAgentAddPo pcAgentAddPo) {
		sessionTemplate.update("updateAgentPage", pcAgentAddPo);
	}
	
}