package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.PcAgentService;
import com.dsj.modules.pagelayout.dao.PcAgentDao;
import com.dsj.modules.pagelayout.po.PcAgentPo;
import com.dsj.modules.pagelayout.po.PcNewHousePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcAgentServiceImpl  extends BaseServiceImpl<PcAgentDao,PcAgentPo> implements PcAgentService {

	@Override
	public PcAgentPo getAgent(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		return dao.getAgent(paramMap);
	}

	@Override
	public void updateAgent(PcAgentPo pcAgentPo) {
		dao.updateDynamic(pcAgentPo);
	}

	@Override
	public PcAgentPo getAgentOldHouse(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		return dao.getAgentOldHouse(paramMap);
	}

	@Override
	public void updateAgentOldHouse(PcAgentPo pcAgentPo) {
		dao.updateAgentOldHouse(pcAgentPo);
	}


	
}