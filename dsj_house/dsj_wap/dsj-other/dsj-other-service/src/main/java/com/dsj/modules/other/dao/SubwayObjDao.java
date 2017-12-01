package com.dsj.modules.other.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.SubwayObjPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-13 17:55:12.
 * @版本: 1.0 .
 */
public interface SubwayObjDao extends BaseDao<SubwayObjPo> {

	void saveList(Map<String, Object> map);

	void deleteByObjTypeAndObjId(Map<String, Object> map);

	String getLineBy(Map<String, Object> map);

	String getStationBy(Map<String, Object> map);

	String getLineByNewHouse(Map<String, Object> map);

	String getStationByNewHouse(Map<String, Object> map);
	
}