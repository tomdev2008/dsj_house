package com.dsj.modules.pagelayout.dao;

import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.pagelayout.po.PcRentHousePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcRentHouseDao extends BaseDao<PcRentHousePo> {

	List<PcRentHousePo> getRentHouse();

	void updateRentHousePage(PcRentHousePo pcRentHousePo);
	
}