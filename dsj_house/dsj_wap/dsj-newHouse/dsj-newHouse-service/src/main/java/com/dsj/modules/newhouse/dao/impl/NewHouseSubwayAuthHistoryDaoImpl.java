package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseSubwayAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-24 16:36:40.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseSubwayAuthHistoryDaoImpl extends BaseDaoImpl<NewHouseSubwayAuthHistoryPo> implements NewHouseSubwayAuthHistoryDao{

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

	@Override
	public List<NewHouseSubwayAuthPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}
	
}