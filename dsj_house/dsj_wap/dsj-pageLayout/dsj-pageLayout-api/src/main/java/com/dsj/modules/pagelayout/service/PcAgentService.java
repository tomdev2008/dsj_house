package com.dsj.modules.pagelayout.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcAgentPo;
import com.dsj.modules.pagelayout.po.PcNewHousePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcAgentService extends BaseService<PcAgentPo>{

	PcAgentPo getAgent(Long id);

	void updateAgent(PcAgentPo pcAgentPo);

	PcAgentPo getAgentOldHouse(Long id);

	void updateAgentOldHouse(PcAgentPo pcAgentPo);



	


	
}