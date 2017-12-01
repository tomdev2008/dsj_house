package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseOpenHandTimeAuthDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseOpenHandTimeAuthDaoImpl extends BaseDaoImpl<NewHouseOpenHandTimeAuthPo> implements NewHouseOpenHandTimeAuthDao{

	@Override
	public void deleteByNewHouseId(Long id) {
		sessionTemplate.delete(getStatement("deleteByNewHouseId"), id);
	}

	@Override
	public void updateNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateNewHouseId"), map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

	@Override
	public NewHouseOpenHandTimeAuthPo getNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getNewBy"), map);
	}

	@Override
	public List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}

	@Override
	public NewHouseOpenHandTimeAuthPo getNewBy2(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getNewBy2"),map);
	}

}