package com.dsj.modules.oldHouseParser.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldHouseParser.po.MtfcOldhouseFlagPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 15:27:00.
 * @版本: 1.0 .
 */
public interface MtfcOldhouseFlagDao extends BaseDao<MtfcOldhouseFlagPo> {

	void deleteAll();
	
}