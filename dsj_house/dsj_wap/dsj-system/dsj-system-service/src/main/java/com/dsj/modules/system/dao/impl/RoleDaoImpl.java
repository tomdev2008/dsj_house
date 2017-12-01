package com.dsj.modules.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.RoleDao;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<RolePo> implements RoleDao{

	@Override
	public List<String> getRoleNames(UserPo admin) {
		return sessionTemplate.selectList("getRoleNames", admin.getId());
	}

	@Override
	public List<RolePo> getRolesList(List<String> roles) {
		return sessionTemplate.selectList("getRoles", roles);
	}

	@Override
	public Long listCount() {
		
		return sessionTemplate.selectOne(getStatement(SQL_LIST_PAGE_COUNT));
	}

	@Override
	public List<RolePo> pageList(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("selectPageList"), paramMap);
	}

	@Override
	public RolePo getRoleCodeName(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectOne("getRoleCodeName", paramMap);
	}

	@Override
	public int deleteRoleId(Map<String, Object> paramMap) {
		return sessionTemplate.delete("deleteRoleId", paramMap);
	}





}