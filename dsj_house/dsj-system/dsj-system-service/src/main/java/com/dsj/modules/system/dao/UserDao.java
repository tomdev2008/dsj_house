package com.dsj.modules.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙�.
 * @浣滆��: gaocj.
 * @鍒涘缓鏃堕棿: 2017-06-15 17:49:26.
 * @鐗堟湰: 1.0 .
 */
public interface UserDao extends BaseDao<UserPo> {

	void updateEvelopersAudit(HashMap<String, Object> map);
	
	int updatePasswordByPhone(Map<String, Object> map);

	void updateEvelopersDelFlag(HashMap<String, Object> map);

	void saveUserRole(HashMap<String, Object> hashMap);

	UserPo getByLeftEveloper(HashMap<String, Object> paramMap);
	
	List<UserPo> listAgentUserBy(Map<String, Object> paramMap);

	List<NewHouseAgentFrontVo> getNewHouseAgent(HashMap<String, Object> map);
	
	void insertFollow(Map<String,Object> map);
	
	void deleteFollow(long id);
	
	void deleteFollowByMap(Map<String,Object> map);

	void deleteManyFollow(List<Integer> list);

	UserPo getByLeftPropert(HashMap<String, Object> map);

	int findByUsername(String phone);

	String getNewHouseIdsByMap(HashMap<String, Object> map);
	

	Integer findIsHistory(Map<String, Object> map);
	
	void updateLookHistory(Map<String, Object> map);
	
	void insertLookHistory(Map<String, Object> map);
	
	List<String> getAdminPhones();

	UserPo selectUser(Map<String, Object> map);
}