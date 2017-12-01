package com.dsj.modules.newhouse.dao;

import java.util.HashMap;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-24 16:36:39.
 * @版本: 1.0 .
 */
public interface NewHouseSubwayAuthDao extends BaseDao<NewHouseSubwayAuthPo> {

	void saveList(HashMap<String, Object> map);

	void deleteByNewHouseId(Long id);

	void updateNewHouseId(HashMap<String, Object> map);
	
}