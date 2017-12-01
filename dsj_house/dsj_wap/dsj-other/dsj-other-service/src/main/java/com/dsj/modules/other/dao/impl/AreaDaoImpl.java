package com.dsj.modules.other.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.AreaDao;
import com.dsj.modules.other.po.AreaPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 15:25:36.
 * @版本: 1.0 .
 */
@Repository
public class AreaDaoImpl extends BaseDaoImpl<AreaPo> implements AreaDao{

	@Override
	public String findNameByAreaCode(String areaCode) {
		String name = sessionTemplate.selectOne(getStatement("findNameByAreaCode"), areaCode);
		return name;
	}

	@Override
	public AreaPo getMaxIDArea(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getMaxIDArea"),map);
	}

	@Override
	public List<AreaPo> listParent(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listParent"),map);
	}

	@Override
	public List<AreaPo> getAreaList() {
		return sessionTemplate.selectList("getAreaList");
	}

	@Override
	public List<AreaPo> getRrareaList() {
		return sessionTemplate.selectList("getRrareaList");
	}

	@Override
	public void updateTrea(Map<String, Object> map) {
	sessionTemplate.update("updateTrea", map);
		
	}

	@Override
	public List<AreaPo> getAreaList(Map<String, Object> map) {
		return sessionTemplate.selectList("getAreaListName", map);
	}
	
}