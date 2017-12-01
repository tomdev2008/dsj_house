package com.dsj.modules.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.EmployeeDao;
import com.dsj.modules.system.po.EmployeePo;
import com.dsj.modules.system.po.RolePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: fengqh
 * @创建时间: 2017-06-16 15:44:20.
 * @版本: 1.0 .
 */
@Repository
public class EmployeeDaoImpl extends BaseDaoImpl<EmployeePo> implements EmployeeDao{

	@Override
	public void deleteByIds(List<Integer> list, Integer delFlag) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("delFlag", delFlag);
		sessionTemplate.update(getStatement("deleteByIds"), map);
		
	}


	@Override
	public Long selectUser(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("selectUser"), paramMap);
	}


	@Override
	public void insertRole(List<Integer> list, long userId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list", list);
		map.put("userId", userId);
		sessionTemplate.update(getStatement("insertRole"), map);
		
	}


	@Override
	public List<Integer> getRoleIdList(long userId) {
		List<Integer> list = sessionTemplate.selectList(getStatement("getRoleIdList"),userId);
		return list;
	}


	@Override
	public List<RolePo> getRoleNameList() {
		List<RolePo> roleList = sessionTemplate.selectList(getStatement("getRoleNameList"));
		return roleList;
	}


	@Override
	public List<String> getRoleNameString(List<Integer> roleIdList) {
		List<String> list = sessionTemplate.selectList(getStatement("getRoleNameString"),roleIdList);
		return list;
	}


	@Override
	public void deleteRole(long userId) {
		sessionTemplate.delete(getStatement("deleteRole"),userId);
		
	}


	@Override
	public List<Integer> getEmpNums() {
		List<Integer> list = sessionTemplate.selectList(getStatement("getEmpNums"));
		return list;
	}

	
	
}