package com.dsj.modules.pagelayout.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.pagelayout.po.PcOldHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.pagelayout.vo.WarrantOriginVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcOldHouseDao extends BaseDao<PcOldHousePo> {

	List<NewHouseLabelVo> getLableOldHouseList();

	void updateOldHousePage(PcOldHousePo pcNewhouse);

	List<NewHouseLabelVo> getLableOldHouseListPage();

	NewHouseLabelVo getBanner();

	List<WarrantOriginVo> getWarrantList();

	WarrantOriginVo getWarrant(Map<String, Object> paramMap);

	void updateWarrant(WarrantOriginVo warrantOriginVo);

	void updateWarrantPage(WarrantOriginVo warrantOriginVo);

	WarrantOriginVo getWarrantVo(Map<String, Object> paramMap);

	void updateBanner(WarrantOriginVo warrantOriginVo);

	void updateBannerPage(NewHouseLabelVo banner);

	List<WarrantOriginVo> getWarrantListPage();

	
}