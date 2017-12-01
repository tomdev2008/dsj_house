package com.dsj.modules.comment.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.comment.dao.HouseNewsDao;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.vo.HouseNewsVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 13:36:52.
 * @版本: 1.0 .
 */
@Repository
public class HouseNewsDaoImpl extends BaseDaoImpl<HouseNewsPo> implements HouseNewsDao{

	@Override
	public void downByIds(Map<String, Object> map) {
		sessionTemplate.update(getStatement("downByIds"), map);
		
	}

	@Override
	public void updateAuditMany(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateAuditMany"), map);
		
	}

	@Override
	public HouseNewsVo getVoById(long id) {
		
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}

	@Override
	public void updateRemoveStick(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateRemoveStick"), map);
	}

	@Override
	public void updateAddStick(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateAddStick"), map);
	}

	@Override
	public void updateDeleteFlag(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateDeleteFlag"), map);
	}

	@Override
	public HouseNewsVo getOneBy(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getOneBy"), map);
	}

	@Override
	public void updateLineFlag(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateLineFlag"), map);
	}

	@Override
	public void updateDeleteByCreateUserIds(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateDeleteByCreateUserIds"), map);
	}

	@Override
	public Long getHouseNewsCountBy(HashMap<String, Object> map1) {
		return sessionTemplate.selectOne(getStatement("getHouseNewsCountBy"), map1);
	}

	@Override
	public List<HouseNewsVo> getRelatedNews(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getRelatedNews"), map);
	}
	
}