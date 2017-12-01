package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseWyMsgAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseWyMsgAuthHistoryDaoImpl extends BaseDaoImpl<NewHouseWyMsgAuthHistoryPo> implements NewHouseWyMsgAuthHistoryDao{

	@Override
	public List<NewHouseWyMsgAuthPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

}