package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseOpenHandTimeAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseOpenHandTimeAuthHistoryDaoImpl extends BaseDaoImpl<NewHouseOpenHandTimeAuthHistoryPo> implements NewHouseOpenHandTimeAuthHistoryDao{

	@Override
	public List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

}