package com.dsj.modules.oldhouse.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldhouse.dao.OldHouseEntrustDao;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:13:06.
 * @版本: 1.0 .
 */
@Repository
public class OldHouseEntrustDaoImpl extends BaseDaoImpl<OldHouseEntrustPo> implements OldHouseEntrustDao{

	@Override
	public void updateOldHouseEntrust(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateOldHouseEntrust"),map);
	}
	
}