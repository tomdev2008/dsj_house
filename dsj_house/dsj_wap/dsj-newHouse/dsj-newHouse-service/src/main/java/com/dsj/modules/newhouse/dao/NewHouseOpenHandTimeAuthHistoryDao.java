package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseOpenHandTimeAuthHistoryDao extends BaseDao<NewHouseOpenHandTimeAuthHistoryPo> {

	List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map);

	void saveList(HashMap<String, Object> map);
	
}