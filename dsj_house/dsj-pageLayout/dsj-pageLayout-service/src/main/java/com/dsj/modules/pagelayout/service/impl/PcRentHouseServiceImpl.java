package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.PcRentHouseService;
import com.dsj.modules.pagelayout.dao.PcRentHouseDao;
import com.dsj.modules.pagelayout.po.PcRentHousePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcRentHouseServiceImpl  extends BaseServiceImpl<PcRentHouseDao,PcRentHousePo> implements PcRentHouseService {

	@Override
	public List<PcRentHousePo> getRentHouse() {
		return dao.getRentHouse();
	}

	@Override
	public PcRentHousePo getRentHouseOne(Long id) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("id", id);
		return dao.getBy(paramMap);
	}

	@Override
	public void updateRentHouse(PcRentHousePo pcRentHousePo) {
		dao.updateDynamic(pcRentHousePo);
		
	}

	@Override
	public void updateRentHousePage(PcRentHousePo pcRentHousePo) {
		dao.updateRentHousePage(pcRentHousePo);
	}
	
}