package com.dsj.modules.oldhouse.dao;

import java.util.HashMap;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:13:06.
 * @版本: 1.0 .
 */
public interface OldHouseEntrustDao extends BaseDao<OldHouseEntrustPo> {

	void updateOldHouseEntrust(HashMap<String, Object> map);
	
}