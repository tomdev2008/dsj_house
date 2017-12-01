package com.dsj.modules.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙�.
 * @浣滆��: gaocj.
 * @鍒涘缓鏃堕棿: 2017-06-27 19:45:51.
 * @鐗堟湰: 1.0 .
 */
public interface AgentDao extends BaseDao<AgentPo> {
	List<Integer> getAgentCode();

	void resetPwdMany(Map<String,Object> map);
	void updateAuditMany(Map<String,Object> map);
	List<Map> findPhone(List<Integer> list);


	AgentPo agentId(Map<String, Object> paramMap);
	
	List<AgentPo> selectSort();
	
	AgentVo getByUserId(long userId);
	
	List<AgentPo> selectByName(Map<String,Object> map);

	List<AgentPo> listNewBy(HashMap<String, Object> map);

	long findShowShelves(Map<String, Object> paramMap);

	AgentDirectoryVo selectDateMin(Map<String, Object> map);

	int deleteShelves(Map<String, Object> paramMap);

	AgentDirectoryVo selectShowShelves(Map<String, Object> paramMap);

	long insertShowShelves(AgentDirectoryVo agentDirectoryVo);

	long deleteShowShelves(Map<String, Object> paramMap);

	List<AgentDirectoryVo> selectListSevlves(Map<String, Object> paramMap);

	long selectAgentRecommend(Map<String, Object> paramMap);

	long insertAgentDirectory(AgentDirectoryVo agentDirectoryVo);

	AgentDirectoryVo selectAgentDirectory(Map<String, Object> paramMap);

	List<AgentDirectoryVo> selectAgentDirectoryList(Map<String, Object> paramMap);

	long deleteAgentDircetory(Map<String, Object> paramMap);

	List<AgentPo> getAgent(HashMap<String, Object> map);
	
	List<AgentVo> getLikeAgent(HashMap<String, Object> map);

	List<AgentVo> getAgentDirectory();


	AgentVo getVoById(long id);
	
	void updateSort(Map<String,Object> map);

	List<AgentVo> getAgentBySelect(HashMap<String, Object> map);

	AgentVo getAgentByHouseId(Map<String, Object> map);

	List<AgentVo> getVoByRentHouseId(Map<String, Object> map);
	
	List<AgentVo> findFollow(Map<String, Object> map);
	
	long findFollowCount(Map<String, Object> map);
	
	List<AgentVo> lookHistory(Map<String, Object> map);
	
	long lookHistoryCount(Map<String, Object> map);

	List<AgentVo> getNewHouseAgentDirectory(Map<String, Object> paramMap);
	
	int findIsFollow(Map<String, Object> map);

	List<AgentVo> getRentHousePage();
	
	List<RecommendVo> getRecommend(Integer userId);

	AgentVo getScoreVoById(Long id);

	List<AgentVo> listByNewHouseId(Long id);

	List<com.dsj.modules.newhouse.vo.AgentFrontVo> listByMap(HashMap<String, Object> map);
}