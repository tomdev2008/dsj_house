package com.dsj.modules.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.FunctionDao;
import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.po.RoleFunctionPo;
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
public class FunctionDaoImpl extends BaseDaoImpl<FunctionPo> implements FunctionDao{

	@Override
	public List<String> getPatternsList(UserPo user) {
		Map<String, Object> map=new HashMap<String,Object>();
		if(user!=null){
		map.put("user_id",  user.getId());
		}
		return sessionTemplate.selectList("getPatternsList", map);
	}

	@Override
	public List<FunctionPo> getListMenus(List<RolePo> rolelist) {
		return sessionTemplate.selectList("getListMenus", rolelist);
	}

	@Override
	public List<FunctionPo> findAllFunction(int pid) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("pid", pid);
		return sessionTemplate.selectList("functionList",map);
	}

	@Override
	public List<FunctionPo> findAllFunctionTwo() {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("level", 2);
		paramMap.put("type", 1);
		return sessionTemplate.selectList("findAllFunctionTwo",paramMap);
	}

	@Override
	public List<FunctionPo> findAllFunctionButtons() {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("level", 3);
		paramMap.put("type", 2);
		return sessionTemplate.selectList("findAllFunctionTwo",paramMap);
	}

	@Override
	public List<FunctionPo> getFunction(Map<String, Object> map) {
		return sessionTemplate.selectList("getFunction", map);
	}

	
	
}