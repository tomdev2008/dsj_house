package com.dsj.modules.oldhouse.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldhouse.dao.OldHouseRequireDao;
import com.dsj.modules.oldhouse.po.OldHouseRequirePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:13:07.
 * @版本: 1.0 .
 */
@Repository
public class OldHouseRequireDaoImpl extends BaseDaoImpl<OldHouseRequirePo> implements OldHouseRequireDao{

	@Override
	public void updateOldHouseRequire(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateOldHouseRequire"),map);
	}
	
}