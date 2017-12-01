package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseWyMsgAuthHistoryDao extends BaseDao<NewHouseWyMsgAuthHistoryPo> {

	List<NewHouseWyMsgAuthPo> listNewBy(HashMap<String, Object> map);

	void saveList(HashMap<String, Object> map);

}