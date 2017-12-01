package com.dsj.modules.pagelayout.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcNewHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcNewHouseService extends BaseService<PcNewHousePo>{

	List<NewHouseLabelVo> getLableNewHouseList();

	PcNewHousePo getNewHouse(Long id);

	void updateNewHouse(PcNewHousePo pcNewHousePo);

	void updateNewHousePage(PcNewHousePo pcNewhouse);
    /**
     * 首页新房列表
     * @return
     */
	List<NewHouseLabelVo> getLableNewHouseListPage();

	List<PcNewHousePo> getFindAll();



	
}