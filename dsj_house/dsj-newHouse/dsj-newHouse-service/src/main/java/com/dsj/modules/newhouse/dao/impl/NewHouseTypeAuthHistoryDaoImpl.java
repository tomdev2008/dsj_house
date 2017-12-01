package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseTypeAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseTypeAuthHistoryDaoImpl extends BaseDaoImpl<NewHouseTypeAuthHistoryPo> implements NewHouseTypeAuthHistoryDao{

	@Override
	public List<NewHouseTypeAuthPo> selectHouseTypeList(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("selectHouseTypeList"), paramMap);
	}

	@Override
	public long selectHouseTypeCount(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("selectHouseTypeCount"), paramMap);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}
	
}