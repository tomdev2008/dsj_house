package com.dsj.modules.oldhouse.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.vo.OldHouseAgentVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:24:08.
 * @版本: 1.0 .
 */
public interface OldHouseAgentService extends BaseService<OldHouseAgentPo>{

	OldHouseAgentVo getByMasterIdAndNullUserId(Long masterId);

	List<OldHouseAgentPo> getJoinMasterLefJoinAgent(Map<String, Object> agentMap);
	
	/*
	 * 查询二手房的经纪人，userId 不为空
	 */
	List<OldHouseAgentPo> getJoinMasterLefJoinAgentUserId(Map<String, Object> agentMap);

	void updateIsRecomend(Long oldMasterId, Integer isRecomend, Long userId);

	int updateDelOldAgentByUserNotNull(Long oldId, Integer value);

	OldHouseAgentPo getByUserNotNull(Map<String, Object> paramMap);


	
}