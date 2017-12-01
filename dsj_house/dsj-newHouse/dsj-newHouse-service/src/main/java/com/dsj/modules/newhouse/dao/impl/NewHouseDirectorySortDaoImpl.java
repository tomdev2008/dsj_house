package com.dsj.modules.newhouse.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseDirectorySortDao;
import com.dsj.modules.newhouse.po.NewHouseDirectorySortPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseDirectorySortDaoImpl extends BaseDaoImpl<NewHouseDirectorySortPo> implements NewHouseDirectorySortDao{

	@Override
	public List<NewHouseDirectorySortPo> selectList() {
		return sessionTemplate.selectList("selectLouPanList");
	}

	@Override
	public NewHouseDirectorySortPo selectOne(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("selectOne", paramMap);
	}

	@Override
	public int updateNewHouseSort(Map<String, Object> paramMap) {
		return sessionTemplate.update("updateNewHouseSort", paramMap);
	}

	@Override
	public int deleteSort(Map<String, Object> paramMap) {
		return sessionTemplate.delete("deleteSort", paramMap);
	}

	
}