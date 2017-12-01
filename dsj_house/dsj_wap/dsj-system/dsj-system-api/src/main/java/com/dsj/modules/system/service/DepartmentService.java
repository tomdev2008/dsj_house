package com.dsj.modules.system.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.DepartmentPo;
import com.dsj.modules.system.vo.EasyuiSelectTreeVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface DepartmentService extends BaseService<DepartmentPo>{
	/**
	 * 部门获取easyui的树结构
	 * @return
	 */
	List<EasyuiSelectTreeVo> getEasyuiSelectTrees(Long id);
	
	/**
	 * 分页查询树
	 * @param pageParam
	 * @param paramMap
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	PageBean listPageTree(PageParam pageParam, Map<String, Object> paramMap) throws IllegalAccessException, InvocationTargetException;


	
}