package com.dsj.modules.pagelayout.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.PcNewHouseDao;
import com.dsj.modules.pagelayout.po.PcNewHousePo;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Repository
public class PcNewHouseDaoImpl extends BaseDaoImpl<PcNewHousePo> implements PcNewHouseDao{

	@Override
	public List<NewHouseLabelVo> getLableNewHouseList() {
		return sessionTemplate.selectList("getLableNewHouseList");
	}

	@Override
	public void updateNewHousePage(PcNewHousePo pcNewhouse) {
		sessionTemplate.update("updateNewHousePage", pcNewhouse);
	}

	@Override
	public List<NewHouseLabelVo> getLableNewHouseListPage() {
		return sessionTemplate.selectList("getLableNewHouseListPage");
	}

	@Override
	public PcNewHousePo getNewHouse(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getNewHouse", paramMap);
	}

	@Override
	public List<PcNewHousePo> getFindAll() {
		return sessionTemplate.selectList("getFindAll");
	}
	
}