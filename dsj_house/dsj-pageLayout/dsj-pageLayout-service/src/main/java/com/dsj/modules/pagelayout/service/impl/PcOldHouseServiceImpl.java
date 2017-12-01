package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.PcOldHouseService;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.pagelayout.vo.WarrantOriginVo;
import com.dsj.modules.pagelayout.dao.PcOldHouseDao;
import com.dsj.modules.pagelayout.po.PcOldHousePo;
import com.dsj.modules.pagelayout.po.PcRentHousePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcOldHouseServiceImpl  extends BaseServiceImpl<PcOldHouseDao,PcOldHousePo> implements PcOldHouseService {

	@Override
	public List<NewHouseLabelVo> getLableOldHouseList() {
		return dao.getLableOldHouseList();
	}

	@Override
	public PcOldHousePo getOldHouse(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id",id);
		return dao.getBy(paramMap);
	}

	@Override
	public void updateOldHouse(PcOldHousePo pcOldHousePo) {
		dao.updateDynamic(pcOldHousePo);
	}

	@Override
	public void updateOldHousePage(PcOldHousePo pcNewhouse) {
		dao.updateOldHousePage(pcNewhouse);
	}

	@Override
	public List<NewHouseLabelVo> getLableOldHouseListPage() {
		return dao.getLableOldHouseListPage();
	}

	@Override
	public NewHouseLabelVo getBanner() {
		return dao.getBanner();
	}

	@Override
	public List<WarrantOriginVo> getWarrantList() {
		return dao.getWarrantList();
	}

	@Override
	public WarrantOriginVo getWarrant(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id",id);
		return dao.getWarrant(paramMap);
	}

	@Override
	public void updateWarrant(WarrantOriginVo warrantOriginVo) {
		dao.updateWarrant(warrantOriginVo);
	}

	@Override
	public void updateWarrantPage(WarrantOriginVo warrantOriginVo) {
		 dao.updateWarrantPage(warrantOriginVo);
	}

	@Override
	public WarrantOriginVo getWarrantVo(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id",id);
		return dao.getWarrantVo(paramMap);
	}

	@Override
	public void updateBanner(WarrantOriginVo warrantOriginVo) {
		dao.updateBanner(warrantOriginVo);
	}

	@Override
	public void updateBannerPage(NewHouseLabelVo banner) {
		dao.updateBannerPage(banner);
		
	}

	@Override
	public List<WarrantOriginVo> getWarrantListPage() {
		
		return dao.getWarrantListPage();
	}


	
}