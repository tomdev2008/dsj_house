package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.PcAgentAddService;
import com.dsj.modules.pagelayout.dao.PcAgentAddDao;
import com.dsj.modules.pagelayout.po.PcAgentAddPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcAgentAddServiceImpl  extends BaseServiceImpl<PcAgentAddDao,PcAgentAddPo> implements PcAgentAddService {

	@Override
	public List<PcAgentAddPo> getAgent() {
		return dao.getAgent();
	}

	@Override
	public PcAgentAddPo getAgentOne(Long id) {
	Map<String, Object> paramMap=new HashMap<String, Object>();
	paramMap.put("id", id);
	return dao.getBy(paramMap);
	}

	@Override
	public void updateAgentAdd(PcAgentAddPo pcAgentAddPo) {
		dao.updateDynamic(pcAgentAddPo);
	}

	@Override
	public void updateAgentPage(PcAgentAddPo pcAgentAddPo) {
		dao.updateAgentPage(pcAgentAddPo);
		
	}
	
}