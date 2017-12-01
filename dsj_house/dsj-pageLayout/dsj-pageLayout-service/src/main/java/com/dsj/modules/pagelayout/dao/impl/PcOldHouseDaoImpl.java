package com.dsj.modules.pagelayout.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.PcOldHouseDao;
import com.dsj.modules.pagelayout.po.PcOldHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.pagelayout.vo.WarrantOriginVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Repository
public class PcOldHouseDaoImpl extends BaseDaoImpl<PcOldHousePo> implements PcOldHouseDao{

	@Override
	public List<NewHouseLabelVo> getLableOldHouseList() {
		return sessionTemplate.selectList("getLableOldHouseList");
	}

	@Override
	public void updateOldHousePage(PcOldHousePo pcNewhouse) {
		sessionTemplate.update("updateOldHousePage", pcNewhouse);
	}

	@Override
	public List<NewHouseLabelVo> getLableOldHouseListPage() {
		return sessionTemplate.selectList("getLableOldHouseListPage");
	}

	@Override
	public NewHouseLabelVo getBanner() {
		return sessionTemplate.selectOne("getBanner");
	}

	@Override
	public List<WarrantOriginVo> getWarrantList() {
		return sessionTemplate.selectList("getWarrantList");
	}

	@Override
	public WarrantOriginVo getWarrant(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getWarrant", paramMap);
	}

	@Override
	public void updateWarrant(WarrantOriginVo warrantOriginVo) {
		sessionTemplate.update("updateWarrant", warrantOriginVo);		
	}

	@Override
	public void updateWarrantPage(WarrantOriginVo warrantOriginVo) {
        sessionTemplate.update("updateWarrantPage", warrantOriginVo);		
	}

	@Override
	public WarrantOriginVo getWarrantVo(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getWarrantVo", paramMap);
	}

	@Override
	public void updateBanner(WarrantOriginVo warrantOriginVo) {
		 sessionTemplate.update("updateBanner", warrantOriginVo);		
	}

	@Override
	public void updateBannerPage(NewHouseLabelVo banner) {
		sessionTemplate.update("updateBannerPage", banner);
	}

	@Override
	public List<WarrantOriginVo> getWarrantListPage() {
		return sessionTemplate.selectList("getWarrantListPage");
	}

	
}