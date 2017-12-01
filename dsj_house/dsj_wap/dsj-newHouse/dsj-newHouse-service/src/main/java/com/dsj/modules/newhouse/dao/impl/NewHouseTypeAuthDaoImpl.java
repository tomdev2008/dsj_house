package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseTypeAuthDao;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseTypeAuthDaoImpl extends BaseDaoImpl<NewHouseTypeAuthPo> implements NewHouseTypeAuthDao{

	@Override
	public List<NewHouseTypeAuthPo> selectHouseTypeList(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("selectHouseTypeList"), paramMap);
	}

	@Override
	public long selectHouseTypeCount(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("selectHouseTypeCount"), paramMap);
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		sessionTemplate.delete(getStatement("deleteByNewHouseId"), id);
	}

	@Override
	public void updateNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateNewHouseId"), map);
	}

	@Override
	public void updateDeleteFlagNewHouseTypes(HashMap<String, Object> hashMap) {
		sessionTemplate.update("updateDeleteFlagNewHouseTypes", hashMap);
	}

	@Override
	public List<NewHouseTypeAuthPo> getNewHouseType(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectList("getNewHouseType", paramMap);
	}

	@Override
	public String getNewHouseTypeByHouseId(Long id) {
		return sessionTemplate.selectOne(getStatement("getNewHouseTypeByHouseId"), id);
	}

	@Override
	public List<NewHouseTypeCountVo> getHouseTypeCount(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getHouseTypeCount"), map);
	}

	@Override
	public List<NewHouseTypeCountVo> getNewHouseTypeListAndCountById(Long id) {
		return null;
	}

	@Override
	public void updateHouseType(NewHouseTypeAuthPo po) {
		sessionTemplate.update("updateHouseType", po);
	}

	
}