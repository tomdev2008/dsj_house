package com.dsj.modules.system.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.RoleFunctionPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface RoleFunctionService extends BaseService<RoleFunctionPo>{

	List<RoleFunctionPo> findById(Long roleId);

	void updateRoleFunction(String[] funList, Integer roleId);

	void delRoleFunction(Long id);

}