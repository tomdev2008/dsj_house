package com.dsj.modules.other.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.other.dao.HouseDirectoryDao;
import com.dsj.modules.other.po.HouseDirectoryPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
@Repository
public class HouseDirectoryDaoImpl extends BaseDaoImpl<HouseDirectoryPo> implements HouseDirectoryDao{
	
	@Override
	public void updateDeleteFlag(HashMap<String, Object> map) {
		int result = sessionTemplate.update(getStatement("updateDeleteFlag"), map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0
				.newInstance("数据库操作, updateDeleteFlag返回0.{%s}", 
						getStatement("updateDeleteFlag"));
		}
	}

	@Override
	public List<HouseDirectoryPo> getByNamePreMatchding(Map<String, Object> map) {
		return sessionTemplate.selectList("getByNamePreMatchding",map);
	}

	@Override
	public List<HouseDirectoryPo> getByNameOldHouse(Map<String, Object> map) {
		return sessionTemplate.selectList("getByNameOldHouse",map);
	}

	@Override
	public List<HouseDirectoryPo> listByLimit(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listPage"),map);
	}

	
}