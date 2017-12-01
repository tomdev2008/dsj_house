package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-24 16:36:40.
 * @版本: 1.0 .
 */
public interface NewHouseSubwayAuthHistoryDao extends BaseDao<NewHouseSubwayAuthHistoryPo> {

	void saveList(HashMap<String, Object> map);

	List<NewHouseSubwayAuthPo> listNewBy(HashMap<String, Object> map);
	
}