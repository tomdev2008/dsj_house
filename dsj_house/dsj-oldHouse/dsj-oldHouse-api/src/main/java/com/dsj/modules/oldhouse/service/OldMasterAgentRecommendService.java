package com.dsj.modules.oldhouse.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldMasterAgentRecommendPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:23:40.
 * @版本: 1.0 .
 */
public interface OldMasterAgentRecommendService extends BaseService<OldMasterAgentRecommendPo>{

	void saveOrDel(Long id, Integer type,Long userId);

	void updateDeleteFlagByUserIdOrMasterId(Long oldMasterId, Long userId);


	
}