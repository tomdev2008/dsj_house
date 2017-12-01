package com.dsj.modules.newhouse.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseDirectorySortPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseDirectorySortDao extends BaseDao<NewHouseDirectorySortPo> {

	List<NewHouseDirectorySortPo> selectList();

	NewHouseDirectorySortPo selectOne(Map<String, Object> paramMap);

	int updateNewHouseSort(Map<String, Object> paramMap);

	int deleteSort(Map<String, Object> paramMap);
	
}