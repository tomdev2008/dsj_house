package com.dsj.modules.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙ｅ疄鐜扮被.
 * @浣滆��: gaocj
 * @鍒涘缓鏃堕棿: 2017-06-15 17:49:26.
 * @鐗堟湰: 1.0 .
 */
@Repository
public class UserDaoImpl extends BaseDaoImpl<UserPo> implements UserDao{

	@Override
	public void updateEvelopersAudit(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateEvelopersAudit"),map);
	}

	@Override
	public int updatePasswordByPhone(Map<String, Object> map) {
		return sessionTemplate.update(getStatement("updatePasswordByPhone"),map);
	}

	@Override
	public void updateEvelopersDelFlag(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateEvelopersDelFlag"),map);
	}

	@Override
	public void saveUserRole(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveUserRole"),map);
	}

	@Override
	public UserPo getByLeftEveloper(HashMap<String, Object> paramMap) {
		
		return sessionTemplate.selectOne(getStatement("getByLeftEveloper"),paramMap);
	}
	
	@Override
	public List<UserPo> listAgentUserBy(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(
				getStatement("listAgentUserBy"), paramMap);
	}

	@Override
	public List<NewHouseAgentFrontVo> getNewHouseAgent(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getNewHouseAgent"),map);
	}

	@Override
	public void insertFollow(Map<String, Object> map) {
		sessionTemplate.insert(getStatement("insertFollow"), map);
		
	}

	@Override
	public void deleteFollow(long id) {
		sessionTemplate.delete(getStatement("deleteFollow"), id);
		
	}

	@Override
	public void deleteFollowByMap(Map<String, Object> map) {
		sessionTemplate.delete(getStatement("deleteFollowByMap"), map);
		
	}

	@Override
	public void deleteManyFollow(List<Integer> list) {
		sessionTemplate.delete(getStatement("deleteManyFollow"), list);		
	}

	@Override
	public UserPo getByLeftPropert(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getByLeftPropert"),map);
	}

	@Override
	public int findByUsername(String phone) {
		return sessionTemplate.selectOne(getStatement("findByUsername"),phone);
	}

	@Override
	public String getNewHouseIdsByMap(HashMap<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getNewHouseIdsByMap"),map);
	}

	@Override
	public Integer findIsHistory(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findIsHistory"),map);
	}

	@Override
	public void updateLookHistory(Map<String, Object> map) {
		
		sessionTemplate.update(getStatement("updateLookHistory"),map);
	}

	@Override
	public void insertLookHistory(Map<String, Object> map) {
		sessionTemplate.insert(getStatement("insertLookHistory"),map);
		
	}

	@Override
	public List<String> getAdminPhones() {
		return sessionTemplate.selectList(getStatement("getAdminPhones"));
	}

	@Override
	public UserPo selectUser(Map<String, Object> map) {
		return sessionTemplate.selectOne("getUser", map);
	}

	
	
	
	
	
}