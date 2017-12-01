package com.dsj.modules.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.RoleFunctionDao;
import com.dsj.modules.system.po.RoleFunctionPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Repository
public class RoleFunctionDaoImpl extends BaseDaoImpl<RoleFunctionPo> implements RoleFunctionDao{

	@Override
	public List<RoleFunctionPo> findRolefuncionList(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectList("findRolefuncionList", paramMap);
	}

	@Override
	public int insertRoleFunction(List<RoleFunctionPo> list) {
		
     return sessionTemplate.insert("insertRoleFunction", list);
	}

	@Override
	public int deleteByRoleId(Map<String, Object> paramMap) {
		
		 return sessionTemplate.delete("deleteByRoleId", paramMap);
	}
	
}