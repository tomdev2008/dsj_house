package com.dsj.modules.pagelayout.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.pagelayout.po.PcAgentPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcAgentDao extends BaseDao<PcAgentPo> {

	PcAgentPo getAgentOldHouse(Map<String, Object> paramMap);

	void updateAgentOldHouse(PcAgentPo pcAgentPo);

	PcAgentPo getAgent(Map<String, Object> paramMap);
	
}