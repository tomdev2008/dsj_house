package com.dsj.modules.other.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.other.service.SubwayObjService;
import com.dsj.modules.other.dao.SubwayObjDao;
import com.dsj.modules.other.po.SubwayObjPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-13 17:55:12.
 * @版本: 1.0 .
 */
@Service
public class SubwayObjServiceImpl  extends BaseServiceImpl<SubwayObjDao,SubwayObjPo> implements SubwayObjService {

	@Override
	public void saveList(Map<String, Object> map) {
		dao.saveList(map);
	}

	@Override
	public void deleteByObjTypeAndObjId(Map<String, Object> map) {
		dao.deleteByObjTypeAndObjId(map);
	}

	@Override
	public String getLineBy(Integer objType, Long houseId) {
		Map<String, Object> map = new HashMap<>();
		map.put("objType", objType);
		map.put("objId", houseId);
		return dao.getLineBy(map);
	}

	@Override
	public String getStationBy(Integer objType, Long houseId) {
		Map<String, Object> map = new HashMap<>();
		map.put("objType", objType);
		map.put("objId", houseId);
		return dao.getStationBy(map);
	}

	@Override
	public String getLineByNewHouse(long parseInt) {
		Map<String, Object> map = new HashMap<>();
		map.put("house_id", parseInt);
		return dao.getLineByNewHouse(map);
	}

	@Override
	public String getStationByNewHouse(long parseInt) {
		Map<String, Object> map = new HashMap<>();
		map.put("house_id", parseInt);
		return dao.getStationByNewHouse(map);
	}
	
}