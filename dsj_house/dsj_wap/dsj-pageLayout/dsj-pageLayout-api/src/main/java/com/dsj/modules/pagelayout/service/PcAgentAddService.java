package com.dsj.modules.pagelayout.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcAgentAddPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcAgentAddService extends BaseService<PcAgentAddPo>{

	List<PcAgentAddPo> getAgent();

	PcAgentAddPo getAgentOne(Long id);

	void updateAgentAdd(PcAgentAddPo pcAgentAddPo);

	void updateAgentPage(PcAgentAddPo pcAgentAddPo);


	
}