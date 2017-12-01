package com.dsj.modules.oldHouseParser.dao.impl;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.MtfcOldhouseFlagDao;
import com.dsj.modules.oldHouseParser.po.MtfcOldhouseFlagPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 15:27:00.
 * @版本: 1.0 .
 */
@Repository
public class MtfcOldhouseFlagDaoImpl extends BaseDaoImpl<MtfcOldhouseFlagPo> implements MtfcOldhouseFlagDao{

	@Override
	public void deleteAll() {
		sessionTemplate.delete(getStatement("deleteAll"));
		
	}
	
}