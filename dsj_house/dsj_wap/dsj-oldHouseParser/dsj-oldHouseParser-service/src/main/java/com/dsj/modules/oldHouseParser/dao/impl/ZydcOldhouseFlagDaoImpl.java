package com.dsj.modules.oldHouseParser.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.ZydcOldhouseFlagDao;
import com.dsj.modules.oldHouseParser.po.ZydcOldhouseFlagPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class ZydcOldhouseFlagDaoImpl extends BaseDaoImpl<ZydcOldhouseFlagPo> implements ZydcOldhouseFlagDao{

	@Override
	public void deleteAll() {
		sessionTemplate.delete(getStatement("deleteAll"));
	}

	@Override
	public ZydcOldhouseFlagPo getLastPo() {
		return sessionTemplate.selectOne(getStatement("getLastPo"));
	}

	@Override
	public List<ZydcOldhouseFlagPo> getCount(Map<String, Object> map) {
		return sessionTemplate.selectList("listPageCountWoai",map);
	}

	@Override
	public void insertZydc(ZydcOldhouseFlagPo zydc) {
		sessionTemplate.insert("insertZydc", zydc);
	}

	@Override
	public void deleteWawjAll() {
		sessionTemplate.delete(getStatement("deleteWawjAll"));
		
	}
	
}