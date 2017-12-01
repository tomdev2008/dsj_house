package com.dsj.modules.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.AgentDao;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙ｅ疄鐜扮被.
 * @浣滆��: gaocj
 * @鍒涘缓鏃堕棿: 2017-06-27 19:45:51.
 * @鐗堟湰: 1.0 .
 */
@Repository
public class AgentDaoImpl extends BaseDaoImpl<AgentPo> implements AgentDao{

	@Override
	public List<Integer> getAgentCode() {
		List<Integer> list = sessionTemplate.selectList(getStatement("getAgentCode"));
		return list;
	}


	@Override
	public void resetPwdMany(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateResetPwdMany"),map);		
	}

	@Override
	public void updateAuditMany(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateAuditMany"),map);	
		
	}

	@Override
	public List<Map> findPhone(List<Integer> list) {
		List<Map> numberList = sessionTemplate.selectList(getStatement("findPhone"),list);
		return numberList;
	}


	@Override
	public AgentPo agentId(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectOne(getStatement("agentId"), paramMap);
	}


	@Override
	public List<AgentPo> selectSort() {
		List<AgentPo> list = sessionTemplate.selectList(getStatement("selectSort"));

		return list;
	}




	@Override
	public List<AgentPo> selectByName(Map<String,Object> map) {
		return sessionTemplate.selectList(getStatement("selectByName"),map);
	}


	@Override
	public long selectAgentRecommend(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectOne(getStatement("selectAgentRecommend"), paramMap);
	}




	@Override
	public long insertAgentDirectory(AgentDirectoryVo agentDirectoryVo) {
		 return sessionTemplate.insert(getStatement("insertAgentDirectory"), agentDirectoryVo);
	}


	@Override
	public AgentDirectoryVo selectAgentDirectory(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("selectAgentDirectory"), paramMap);
	}


	@Override
	public List<AgentDirectoryVo> selectAgentDirectoryList(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("selectAgentDirectoryList"), paramMap);
	}


	@Override
	public long deleteAgentDircetory(Map<String, Object> paramMap) {
		return sessionTemplate.delete(getStatement("deleteAgentDircetory"), paramMap);
	}


	@Override
	public long findShowShelves(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("findShowShelves"), paramMap);
	}



	@Override
	public int deleteShelves(Map<String, Object> paramMap) {
		return sessionTemplate.delete(getStatement("deleteShelves"), paramMap);
	}


	@Override
	public AgentDirectoryVo selectShowShelves(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("selectShowShelves"), paramMap);
	}


	@Override
	public long insertShowShelves(AgentDirectoryVo agentDirectoryVo) {
		return sessionTemplate.insert(getStatement("insertShowShelves"), agentDirectoryVo);
	}
	@Override
	public AgentVo getByUserId(long userId) {
		return sessionTemplate.selectOne(getStatement("getByUserId"),userId);
	}

	@Override
	public long deleteShowShelves(Map<String, Object> paramMap) {
		
		return sessionTemplate.delete(getStatement("deleteShowShelves"), paramMap);
	}


	@Override
	public List<AgentDirectoryVo> selectListSevlves(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("selectListSevlves"), paramMap);
	}


	@Override
	public List<AgentPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}


	@Override
	public List<AgentPo> getAgent(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getAgent"), map);
	}

	@Override
	public List<AgentVo> getLikeAgent(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getLikeAgent"), map);
	}

	@Override
	public AgentVo getVoById(long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}


	@Override
	public void updateSort(Map<String, Object> map) {
		 sessionTemplate.update(getStatement("updateSort"), map);
		
	}

	@Override
	public List<AgentVo> getAgentBySelect(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getAgentBySelect"), map);
	}

	@Override
	public List<AgentVo> getAgentDirectory() {
		return sessionTemplate.selectList(getStatement("getAgentDirectory"));
	}


	@Override
	public AgentVo getAgentByHouseId(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getAgentByHouseId"), map);
	}


	@Override
	public AgentDirectoryVo selectDateMin(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("selectDateMin"),map);
	}


	@Override
	public List<AgentVo> getVoByRentHouseId(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getVoByRentHouseId"),map);
	}

	@Override
	public List<AgentVo> findFollow(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findFollow"),map);
	}


	@Override
	public long findFollowCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findFollowCount"), map);
	}


	@Override
	public List<AgentVo> lookHistory(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("lookHistory"),map);
	}


	@Override
	public long lookHistoryCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("lookHistoryCount"), map);
	}

	@Override
	public List<AgentVo> getNewHouseAgentDirectory(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("getNewHouseAgentDirectory"), paramMap);
	}


	@Override
	public int findIsFollow(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findIsFollow"), map);
	}


	@Override
	public List<AgentVo> getRentHousePage() {
		return sessionTemplate.selectList(getStatement("getAgentDirectoryPage"));
	}


	@Override
	public List<RecommendVo> getRecommend(Integer userId) {
		return sessionTemplate.selectList(getStatement("getRecommend"),userId);
	}


	@Override
	public AgentVo getScoreVoById(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return sessionTemplate.selectOne(getStatement("getScoreVoById"),map);
	}


	@Override
	public List<AgentVo> listByNewHouseId(Long id) {
		return sessionTemplate.selectList(getStatement("listByNewHouseId"),id);
	}


	@Override
	public List<com.dsj.modules.newhouse.vo.AgentFrontVo> listByMap(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listByMap"),map);
	}


	
	

	
}