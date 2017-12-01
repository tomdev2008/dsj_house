package com.dsj.modules.other.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.AreaPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 15:25:36.
 * @版本: 1.0 .
 */
public interface AreaDao extends BaseDao<AreaPo> {
	String findNameByAreaCode(String areaCode);

	AreaPo getMaxIDArea(HashMap<String, Object> map);

	List<AreaPo> listParent(HashMap<String, Object> map);

	List<AreaPo> getAreaList();

	List<AreaPo> getRrareaList();

	void updateTrea(Map<String, Object> map);

	List<AreaPo> getAreaList(Map<String, Object> map);
}