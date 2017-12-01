package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseSubwayAuthDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-24 16:36:39.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseSubwayAuthDaoImpl extends BaseDaoImpl<NewHouseSubwayAuthPo> implements NewHouseSubwayAuthDao{

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		sessionTemplate.delete(getStatement("deleteByNewHouseId"), id);
	}

	@Override
	public void updateNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateNewHouseId"), map);
	}
	
}