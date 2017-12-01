package com.dsj.modules.other.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.SubwayObjPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-13 17:55:12.
 * @版本: 1.0 .
 */
public interface SubwayObjService extends BaseService<SubwayObjPo>{

	void saveList(Map<String, Object> map);

	void deleteByObjTypeAndObjId(Map<String, Object> mapDel);
	
	String getLineBy(Integer objType, Long houseId);
	
	String getStationBy(Integer objType, Long houseId);

	String getLineByNewHouse(long parseInt);

	String getStationByNewHouse(long parseInt);

}