package com.dsj.modules.system.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface RoleService extends BaseService<RolePo>{

	List<String> getRoles(UserPo admin);

	List<RolePo> getRolesList(List<String> roles);

	RolePo getRoleByName(String name);

	Long findCountByCondLike();

	List<RolePo> findRoleByCond(int skip, int limit);

	RolePo getRoleByCodeName(String nameCode);

	PageBean listNewRolePage(PageParam pageParam, Map<String, Object> requestMap);

	int delRole(Long id);




	
}