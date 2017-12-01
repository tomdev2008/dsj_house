package com.dsj.modules.pagelayout.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.PcRentHouseDao;
import com.dsj.modules.pagelayout.po.PcRentHousePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Repository
public class PcRentHouseDaoImpl extends BaseDaoImpl<PcRentHousePo> implements PcRentHouseDao{

	@Override
	public List<PcRentHousePo> getRentHouse() {
		return sessionTemplate.selectList("getRentHouse");
	}

	@Override
	public void updateRentHousePage(PcRentHousePo pcRentHousePo) {
		sessionTemplate.update("updateRentHousePage", pcRentHousePo);
	}
	
}