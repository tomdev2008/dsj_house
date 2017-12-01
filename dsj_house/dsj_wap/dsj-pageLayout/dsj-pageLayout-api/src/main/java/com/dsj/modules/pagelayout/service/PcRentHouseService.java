package com.dsj.modules.pagelayout.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcRentHousePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcRentHouseService extends BaseService<PcRentHousePo>{
    
	List<PcRentHousePo> getRentHouse();

	PcRentHousePo getRentHouseOne(Long id);

	void updateRentHouse(PcRentHousePo pcRentHousePo);

	void updateRentHousePage(PcRentHousePo pcRentHousePo);


	
}