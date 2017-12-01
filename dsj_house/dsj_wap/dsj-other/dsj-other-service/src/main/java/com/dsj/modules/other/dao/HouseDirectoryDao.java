package com.dsj.modules.other.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.HouseDirectoryPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
public interface HouseDirectoryDao extends BaseDao<HouseDirectoryPo> {
	
	void updateDeleteFlag(HashMap<String, Object> map);

	List<HouseDirectoryPo> getByNamePreMatchding(Map<String, Object> map);

	List<HouseDirectoryPo> getByNameOldHouse(Map<String, Object> map);

	List<HouseDirectoryPo> listByLimit(Map<String, Object> map);

	
}