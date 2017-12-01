package com.dsj.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface RoleDao extends BaseDao<RolePo> {

	List<String> getRoleNames(UserPo admin);

	List<RolePo> getRolesList(List<String> roles);

	Long listCount();
	
	List<RolePo> pageList(Map<String, Object> paramMap);

	RolePo getRoleCodeName(Map<String, Object> paramMap);

	int deleteRoleId(Map<String, Object> paramMap);



	
}