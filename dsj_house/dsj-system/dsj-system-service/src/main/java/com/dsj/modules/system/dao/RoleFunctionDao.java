package com.dsj.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.RoleFunctionPo;
import com.dsj.modules.system.po.RolePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface RoleFunctionDao extends BaseDao<RoleFunctionPo> {

	List<RoleFunctionPo> findRolefuncionList(Map<String, Object> paramMap);
	
	int deleteByRoleId(Map<String, Object> paramMap);
	
	int insertRoleFunction(List<RoleFunctionPo> list);
	
}