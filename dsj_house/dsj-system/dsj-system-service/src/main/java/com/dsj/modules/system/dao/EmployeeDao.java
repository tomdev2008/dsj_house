package com.dsj.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.EmployeePo;
import com.dsj.modules.system.po.RolePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: fengqh.
 * @创建时间: 2017-06-16 15:44:20.
 * @版本: 1.0 .
 */
public interface EmployeeDao extends BaseDao<EmployeePo> {


	void deleteByIds(List<Integer> list, Integer delFlag);

	Long selectUser(Map<String, Object> paramMap);
	
	void insertRole(List<Integer> list, long userId);
	List<Integer> getRoleIdList(long userId);
	List<RolePo> getRoleNameList();
	List<String> getRoleNameString(List<Integer> roleIdList);
	void deleteRole(long userId);
	List<Integer> getEmpNums();
}