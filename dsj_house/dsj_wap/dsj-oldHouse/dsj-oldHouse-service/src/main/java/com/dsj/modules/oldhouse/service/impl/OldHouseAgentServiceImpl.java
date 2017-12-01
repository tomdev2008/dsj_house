package com.dsj.modules.oldhouse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldhouse.dao.OldHouseAgentDao;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.vo.OldHouseAgentVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-18 18:24:08.
 * @版本: 1.0 .
 */
@Service
public class OldHouseAgentServiceImpl  extends BaseServiceImpl<OldHouseAgentDao,OldHouseAgentPo> implements OldHouseAgentService {

	@Override
	public OldHouseAgentVo getByMasterIdAndNullUserId(Long masterId) {
		return dao.getByMasterIdAndNullUserId(masterId);
	}

	@Override
	public List<OldHouseAgentPo> getJoinMasterLefJoinAgent(Map<String, Object> agentMap) {
		return dao.getJoinMasterLefJoinAgent(agentMap);
	}

	@Override
	public List<OldHouseAgentPo> getJoinMasterLefJoinAgentUserId(Map<String, Object> agentMap) {
		return dao.getJoinMasterLefJoinAgentUserId(agentMap);
	}

	@Override
	public void updateIsRecomend(Long oldMasterId, Integer isRecomend, Long userId) {
		 dao.updateIsRecomend(oldMasterId,isRecomend,userId);
	}

	@Override
	public int updateDelOldAgentByUserNotNull(Long oldId, Integer deleteFlag) {
		
		return dao.updateDelOldAgentByUserNotNull(oldId,deleteFlag);
	}

	@Override
	public OldHouseAgentPo getByUserNotNull(Map<String, Object> paramMap) {
		return dao.getByUserNotNull(paramMap);
	}
	
}