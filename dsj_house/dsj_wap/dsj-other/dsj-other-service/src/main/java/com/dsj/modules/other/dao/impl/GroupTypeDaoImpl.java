package com.dsj.modules.other.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.GroupTypeDao;
import com.dsj.modules.other.po.GroupTypePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-29 13:46:52.
 * @版本: 1.0 .
 */
@Repository
public class GroupTypeDaoImpl extends BaseDaoImpl<GroupTypePo> implements GroupTypeDao{

	@Override
	public List<GroupTypePo> getDictrait(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getDictrait"),map);
	}

	@Override
	public java.util.List<GroupTypePo> getGroupTypeBackMapByPid(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getGroupTypeBackMapByPid"),map);
	}
	
}