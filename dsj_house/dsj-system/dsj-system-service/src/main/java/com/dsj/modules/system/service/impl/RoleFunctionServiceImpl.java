package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.system.dao.RoleFunctionDao;
import com.dsj.modules.system.po.RoleFunctionPo;
import com.dsj.modules.system.service.RoleFunctionService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class RoleFunctionServiceImpl  extends BaseServiceImpl<RoleFunctionDao,RoleFunctionPo> implements RoleFunctionService {

	@Override
	public List<RoleFunctionPo> findById(Long roleId) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
	    paramMap.put("roleId", roleId);
		return dao.findRolefuncionList(paramMap);
	}


	@Override
	public void updateRoleFunction(String[] funList, Integer roleId) {
		List<RoleFunctionPo> list = new ArrayList<RoleFunctionPo>();
		if(funList!=null && funList.length>=1){
			RoleFunctionPo roleFun=null;
			for(int i=0;i<funList.length;i++){
				roleFun=new RoleFunctionPo();
				String funId=funList[i];
				roleFun.setRoleId(roleId);
				roleFun.setFuncId(Integer.parseInt(funId));
				list.add(roleFun);
			}
		}
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("roleId", roleId);
		dao.deleteByRoleId(paramMap);
		if(list!=null && list.size()>=1){
			dao.insertRoleFunction(list);
		}
	}


	@Override
	public void delRoleFunction(Long id) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("roleId", id);
		dao.deleteByRoleId(paramMap);
	}
}