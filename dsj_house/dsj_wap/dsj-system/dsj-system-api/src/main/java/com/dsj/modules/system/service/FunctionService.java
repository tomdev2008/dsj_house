package com.dsj.modules.system.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface FunctionService extends BaseService<FunctionPo>{

	List<String> getPatternsList(UserPo admin);

	List<FunctionPo> listMenus(List<RolePo> rolelist);

	List<FunctionPo> findAllFunction(Long parseInt);

	List<String> getPatternsAllList();






	
}