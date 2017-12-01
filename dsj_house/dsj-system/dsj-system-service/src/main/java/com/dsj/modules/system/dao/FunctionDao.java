package com.dsj.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface FunctionDao extends BaseDao<FunctionPo> {

	List<String> getPatternsList(UserPo user);

	List<FunctionPo> getListMenus(List<RolePo> rolelist);

	List<FunctionPo> findAllFunction(int pid);

	List<FunctionPo> findAllFunctionTwo();

	List<FunctionPo> findAllFunctionButtons();
     
	List<FunctionPo> getFunction(Map<String, Object> map);

	
}