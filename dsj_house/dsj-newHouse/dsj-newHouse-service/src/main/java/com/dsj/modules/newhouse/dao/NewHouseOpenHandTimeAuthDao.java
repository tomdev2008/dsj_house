package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseOpenHandTimeAuthDao extends BaseDao<NewHouseOpenHandTimeAuthPo> {

	void deleteByNewHouseId(Long id);

	void updateNewHouseId(HashMap<String, Object> map);

	void saveList(HashMap<String, Object> map);

	NewHouseOpenHandTimeAuthPo getNewBy(HashMap<String, Object> map);

	List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map);

	NewHouseOpenHandTimeAuthPo getNewBy2(HashMap<String, Object> map);

}