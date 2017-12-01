package com.dsj.modules.pagelayout.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcOldHousePo;
import com.dsj.modules.pagelayout.po.PcRentHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.pagelayout.vo.WarrantOriginVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcOldHouseService extends BaseService<PcOldHousePo>{

	List<NewHouseLabelVo> getLableOldHouseList();

	PcOldHousePo getOldHouse(Long id);

	void updateOldHouse(PcOldHousePo pcOldHousePo);

	void updateOldHousePage(PcOldHousePo pcNewhouse);

	List<NewHouseLabelVo> getLableOldHouseListPage();

	NewHouseLabelVo getBanner();

	List<WarrantOriginVo> getWarrantList();

	WarrantOriginVo getWarrant(Long id);

	void updateWarrant(WarrantOriginVo warrantOriginVo);

	void updateWarrantPage(WarrantOriginVo warrantOriginVo);

	WarrantOriginVo getWarrantVo(Long id);

	void updateBanner(WarrantOriginVo warrantOriginVo);

	void updateBannerPage(NewHouseLabelVo banner);

	List<WarrantOriginVo> getWarrantListPage();




	
}