package com.dsj.modules.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.EmployeePo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.vo.RoleVo;

/**
 *
 * @描述: Service接口.
 * @作者: fengqh.
 * @创建时间: 2017-06-16 15:44:20.
 * @版本: 1.0 .
 */
public interface EmployeeService extends BaseService<EmployeePo>{
	void modifyPassword(String newPwd,long id,Integer updatePerson);

	boolean findPasswordById(long id,String oldPwd);

	
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	EmployeePo getUser(long id);
	
	void insertEmp(EmployeePo emp);
	void deleteUser(String ids);

	public long addUser(UserPo user);
	Long selectUser(Long id);
	void insertRole(String role, long userId);
	List<Integer> getRoleIdList(long userId);
	
	List<RoleVo> getRoleName(List<Integer> roleIdList);
	List<RoleVo> getAllRole();
	String getRoleNameString(List<Integer> roleIdList);
	void deleteRole(long userId);
	String getNewEmpNum();
	
}