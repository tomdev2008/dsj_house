package com.dsj.modules.oldhouse.dao;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldhouse.po.OldMasterAgentRecommendPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:23:40.
 * @版本: 1.0 .
 */
public interface OldMasterAgentRecommendDao extends BaseDao<OldMasterAgentRecommendPo> {

	int updateDeleteFlagByUserIdOrMasterId(Long oldMasterId, Long userId);

	int deletegByUserIdOrMasterId(Long oldMasterId, Long userId);
	
}