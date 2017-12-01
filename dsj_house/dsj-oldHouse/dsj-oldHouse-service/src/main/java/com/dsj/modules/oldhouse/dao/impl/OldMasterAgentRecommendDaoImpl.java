package com.dsj.modules.oldhouse.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.oldhouse.dao.OldMasterAgentRecommendDao;
import com.dsj.modules.oldhouse.po.OldMasterAgentRecommendPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-18 18:23:40.
 * @版本: 1.0 .
 */
@Repository
public class OldMasterAgentRecommendDaoImpl extends BaseDaoImpl<OldMasterAgentRecommendPo> implements OldMasterAgentRecommendDao{

	@Override
	public int updateDeleteFlagByUserIdOrMasterId(Long oldMasterId, Long userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("oldMasterId",oldMasterId);
		map.put("deleteFlag", DeleteStatusEnum.DEL.getValue());
		int result = sessionTemplate.update(getStatement("updateDeleteFlagByUserIdOrMasterId"),map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}

	@Override
	public int deletegByUserIdOrMasterId(Long oldMasterId, Long userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("oldMasterId", oldMasterId);
		int result = sessionTemplate.update(getStatement("deletegByUserIdOrMasterId"),map);
		return result;
	}
	
}