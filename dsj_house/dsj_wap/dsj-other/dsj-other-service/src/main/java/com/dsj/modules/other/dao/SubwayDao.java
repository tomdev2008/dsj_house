package com.dsj.modules.other.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.SubwayPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-13 10:33:23.
 * @版本: 1.0 .
 */
public interface SubwayDao extends BaseDao<SubwayPo> {

	List<SubwayPo> getSubWayLine(HashMap<String, Object> map);

	List<SubwayPo> getNewSubWayLine(HashMap<String, Object> map);
	
}