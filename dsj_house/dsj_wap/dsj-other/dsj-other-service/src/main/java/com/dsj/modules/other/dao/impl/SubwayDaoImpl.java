package com.dsj.modules.other.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.SubwayDao;
import com.dsj.modules.other.po.SubwayPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-13 10:33:23.
 * @版本: 1.0 .
 */
@Repository
public class SubwayDaoImpl extends BaseDaoImpl<SubwayPo> implements SubwayDao{

	@Override
	public List<SubwayPo> getSubWayLine(HashMap<String, Object> map) {
		return sessionTemplate.selectList("getSubWayLine", map);
	}

	@Override
	public List<SubwayPo> getNewSubWayLine(HashMap<String, Object> map) {
		return sessionTemplate.selectList("getNewSubWayLine", map);
	}
	
}