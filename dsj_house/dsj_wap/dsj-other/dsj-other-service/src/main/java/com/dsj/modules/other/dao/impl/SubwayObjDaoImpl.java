package com.dsj.modules.other.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.SubwayObjDao;
import com.dsj.modules.other.po.SubwayObjPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-13 17:55:12.
 * @版本: 1.0 .
 */
@Repository
public class SubwayObjDaoImpl extends BaseDaoImpl<SubwayObjPo> implements SubwayObjDao{

	@Override
	public void saveList(Map<String, Object> map) {
		sessionTemplate.update(getStatement("saveList"),map);
	}

	@Override
	public void deleteByObjTypeAndObjId(Map<String, Object> map) {
		sessionTemplate.delete(getStatement("deleteByObjTypeAndObjId"), map);
	}

	@Override
	public String getLineBy(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getLineBy"), map);
	}

	@Override
	public String getStationBy(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getStationBy"), map);
	}

	@Override
	public String getLineByNewHouse(Map<String, Object> map) {
		return sessionTemplate.selectOne("getLineByNewHouse", map);
	}

	@Override
	public String getStationByNewHouse(Map<String, Object> map) {
		return sessionTemplate.selectOne("getStationByNewHouse", map);
	}
	
}