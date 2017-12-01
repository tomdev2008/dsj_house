package com.dsj.modules.pagelayout.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.pagelayout.po.PcNewHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcNewHouseDao extends BaseDao<PcNewHousePo> {

	List<NewHouseLabelVo> getLableNewHouseList();

	void updateNewHousePage(PcNewHousePo pcNewhouse);

	List<NewHouseLabelVo> getLableNewHouseListPage();

	PcNewHousePo getNewHouse(Map<String, Object> paramMap);

	List<PcNewHousePo> getFindAll();
	
}