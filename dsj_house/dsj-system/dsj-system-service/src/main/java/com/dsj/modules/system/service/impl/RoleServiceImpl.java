package com.dsj.modules.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.dao.RoleDao;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.RoleService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class RoleServiceImpl  extends BaseServiceImpl<RoleDao,RolePo> implements RoleService {
	@Autowired
    private RoleDao roleDao;
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public List<String> getRoles(UserPo admin) {
		return dao.getRoleNames(admin);
	}

	@Override
	public List<RolePo> getRolesList(List<String> roles) {
		return dao.getRolesList(roles);
	}

	@Override
	public RolePo getRoleByName(String name) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("name", name);
		return dao.getBy(paramMap);
	}

	@Override
	public Long findCountByCondLike() {
		return dao.listCount();
	}

	@Override
	public List<RolePo> findRoleByCond(int skip, int limit) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pageFirst", skip);
		paramMap.put("pageSize", limit);
		return roleDao.pageList(paramMap);
	}

	@Override
	public RolePo getRoleByCodeName(String nameCode) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("nameCode", nameCode);
		return roleDao.getRoleCodeName(paramMap);
	}

	@Override
	public PageBean listNewRolePage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listNewPageRoleCount", "listNewPageRoleList");
	}

	@Override
	public int delRole(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		return dao.deleteRoleId(paramMap);
	}

	
}