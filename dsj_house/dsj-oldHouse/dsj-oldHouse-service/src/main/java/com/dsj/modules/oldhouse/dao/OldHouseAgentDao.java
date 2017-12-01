package com.dsj.modules.oldhouse.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.vo.OldHouseAgentVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:24:08.
 * @版本: 1.0 .
 */
public interface OldHouseAgentDao extends BaseDao<OldHouseAgentPo> {

	OldHouseAgentVo getByMasterIdAndNullUserId(Long masterId);

	List<OldHouseAgentPo> getJoinMasterLefJoinAgent(Map<String, Object> agentMap);

	List<OldHouseAgentPo> getJoinMasterLefJoinAgentUserId(Map<String, Object> agentMap);

	void updateIsRecomend(Long oldMasterId, Integer isRecomend, Long userId);

	int updateDelOldAgentByUserNotNull(Long oldId, Integer deleteFlag);

	OldHouseAgentPo getByUserNotNull(Map<String, Object> paramMap);
	
}