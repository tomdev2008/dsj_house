package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseDirectoryAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseDirectoryAuthHistoryDaoImpl extends BaseDaoImpl<NewHouseDirectoryAuthHistoryPo> implements NewHouseDirectoryAuthHistoryDao{
	
	@Override
	public NewHouseDirectoryAuthVo getVoById(Long id) {
		
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}
}