package com.dsj.modules.other.service;

import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.GroupTypePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-29 13:46:52.
 * @版本: 1.0 .
 */
public interface GroupTypeService extends BaseService<GroupTypePo>{
	/**
	 * 根据字典id获取字典值和名称
	 * @param houseType
	 * @return
	 */
	Map<String, Object> getGroupTypeMapByPid(String houseType);

	Map<String, Object> getHouseGroupType();

	Map<String, Object> getFrontGroupType();

	/**
	 * 获取精简楼盘特点
	 * @return
	 */
	Map<String, Object> getDictrait();

	Map<String, Object> getGroupTypeBackMapByPid(String features);

	Map<String, Object> getGroupTypeByMysql();
	
	/**
	 * 根据父id和字典名称查询字典id
	 * @param pid 父id
	 * @param name 字典名称
	 * @return 字典id
	 */
	Long saveIdByPidAndOname(Integer pid , String name);
	
}