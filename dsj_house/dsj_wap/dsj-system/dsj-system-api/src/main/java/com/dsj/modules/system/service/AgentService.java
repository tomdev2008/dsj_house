package com.dsj.modules.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;

/**
 *
 * @描述: Service接口.
 * @作者: fengqh.
 * @创建时间: 2017-06-27 19:45:51.
 * @版本: 1.0 .
 */
public interface AgentService extends BaseService<AgentPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	String getAgentCode();
	

	
	
	void deleteAgent(String ids);

	
	void updateResetPwdMany(String ids);
	

	
	void updateAuditMany(String ids,String msg,String status,Integer auditPerson);


	PageBean listAgentPage(PageParam pageParam, Map<String, Object> requestMap);

	AgentPo selectAgent(Long agentId);
	
	List<AgentPo> selectSort();
	
	AgentVo getByUserId(long userId);
	
	List<AgentPo> selectByName(Map<String,Object> map);

	long findAgentRecommend(int agentId);

	long insertAgentDirectory(AgentDirectoryVo agentDirectoryVo);

	AgentDirectoryVo selectAgentDirectory(Long newHouseId);

	List<AgentDirectoryVo> findListAgent(int agentId);

	long deleteAgentDircetory(Long newHouseId);

	long findShowShelves(int agentId);

	AgentDirectoryVo selectDateMin(int agentId);

	void deleteShelves(Long id);

	AgentDirectoryVo selectShowShelves(Long newHouseId, int type);

	long insertShowShelves(AgentDirectoryVo agentDirectoryVo);

	long deleteShowShelves(Long newHouseId, int type);

	List<AgentDirectoryVo> findListSevlves(int agentId);

	List<AgentPo> listNewBy(HashMap<String, Object> map);

	List<AgentPo> getAgent(HashMap<String, Object> map);
	
	List<AgentVo> getLikeAgent(HashMap<String, Object> map);

	List<AgentVo> getRentHouse();
	
	AgentVo getVoById(long id);
	
	void updateSort(Map<String,Object> map);

	List<AgentVo> getAgentBySelect(HashMap<String, Object> map);

	AgentVo getAgentByHouseId(Long id);
	
	/**
	 * 根据租房房源id获取经纪人信息详情
	 */
	List<AgentVo> getVoByRentHouseId(Map<String, Object> map);
	
	List<AgentVo> findFollow(Map<String, Object> map);
	
	long findFollowCount(Map<String, Object> map);
	
	List<AgentVo> lookHistory(Map<String, Object> map);
	
	long lookHistoryCount(Map<String, Object> map);

	/**
	 * 根据房源id查询绑定的经纪人
	 * @param id
	 * @return
	 */
	List<AgentVo> getNewHouseAgentDirectory(Long id);
	
	int findIsFollow(long obejctId,long userId,Integer type);
    /**
     * 查询前台首页的经纪人展示列表
     * @return
     */
	List<AgentVo> getRentHousePage(); 
	
	List<RecommendVo> getRecommend(Integer userId);
	
	 List<AgentVo> getNewHouseAgentDirectoryTwo(Long id);

	AgentVo getScoreVoById(Long id);

	List<AgentVo> listByNewHouseId(Long id);

	List<com.dsj.modules.newhouse.vo.AgentFrontVo> listByMap(HashMap<String, Object> map);
}