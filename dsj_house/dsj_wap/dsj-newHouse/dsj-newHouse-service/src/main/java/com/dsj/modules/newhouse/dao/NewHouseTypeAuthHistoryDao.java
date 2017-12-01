package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseTypeAuthHistoryDao extends BaseDao<NewHouseTypeAuthHistoryPo> {

	List<NewHouseTypeAuthPo> selectHouseTypeList(Map<String, Object> paramMap);

	long selectHouseTypeCount(Map<String, Object> paramMap);

	void saveList(HashMap<String, Object> map);
	
}