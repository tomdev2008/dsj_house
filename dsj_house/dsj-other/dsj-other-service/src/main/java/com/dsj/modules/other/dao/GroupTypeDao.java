package com.dsj.modules.other.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.GroupTypePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-29 13:46:52.
 * @版本: 1.0 .
 */
public interface GroupTypeDao extends BaseDao<GroupTypePo> {

	List<GroupTypePo> getDictrait(Map<String, Object> map);

	List<GroupTypePo> getGroupTypeBackMapByPid(Map<String, Object> map);
	
}