package com.dsj.modules.oldhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldhouse.dao.OldHouseAgentDao;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.vo.OldHouseAgentVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-18 18:24:08.
 * @版本: 1.0 .
 */
@Repository
public class OldHouseAgentDaoImpl extends BaseDaoImpl<OldHouseAgentPo> implements OldHouseAgentDao{

	@Override
	public OldHouseAgentVo getByMasterIdAndNullUserId(Long masterId) {
		return sessionTemplate.selectOne(getStatement("getByMasterIdAndNullUserId"), masterId);
	}

	@Override
	public List<OldHouseAgentPo> getJoinMasterLefJoinAgent(Map<String, Object> agentMap) {
		return sessionTemplate.selectList(getStatement("getJoinMasterLefJoinAgent"), agentMap);
	}

	@Override
	public List<OldHouseAgentPo> getJoinMasterLefJoinAgentUserId(Map<String, Object> agentMap) {
		return sessionTemplate.selectList(getStatement("getJoinMasterLefJoinAgentUserId"), agentMap);
	}

	@Override
	public void updateIsRecomend(Long oldMasterId, Integer isRecomend, Long userId) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("oldMasterId", oldMasterId);
		map.put("isRecomend", isRecomend);
		map.put("userId", userId);
		sessionTemplate.update(getStatement("updateIsRecomend"), map);
	}

	@Override
	public int updateDelOldAgentByUserNotNull(Long oldId, Integer deleteFlag) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("oldMasterId", oldId);
		map.put("deleteFlag", 	deleteFlag);
		return 	sessionTemplate.update(getStatement("updateDelOldAgentByUserNotNull"), map);
	}

	@Override
	public OldHouseAgentPo getByUserNotNull(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("getByUserNotNull"),paramMap);
	}
	
}



