package com.dsj.modules.system.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.system.po.UserPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 17:49:26.
 * @版本: 1.0 .
 */
public interface UserService extends BaseService<UserPo>{

	UserPo getUserByName(String username);

	void updateEvelopersReset(Long id);

	void updateEvelopersAudit(String ids, int value);
	/**
	 * 根据手机号修改密码
	 * @param phone
	 * @param password1
	 */
	int updatePasswordByPhone(String phone, String password1,Integer userType);

	void updateEvelopersDeleteFlag(String ids, Integer value);

	UserPo getUserByName(String username, int value);

	UserPo getUserByPhone(String phone, int value);
	
	/**
	 * 关联开发商
	 * @param map
	 * @return
	 */
	UserPo getByLeftEveloper(HashMap<String, Object> map);

	/**
	 * 获得经纪人相关用户账号
	 * 
	 * @param map
	 * @return
	 */
	List<UserPo> listAgentUserBy(Map<String, Object> paramMap);

	List<NewHouseAgentFrontVo> getNewHouseAgent(HashMap<String, Object> map);
	
	void deleteFollow(long id);
	
	void insertFollow(Map<String,Object> map);
	
	void deleteFollowByMap(Map<String,Object> map);
	
	void deleteManyFollow(List<Integer> ids);

	UserPo getByLeftPropert(HashMap<String, Object> map);
	
	int findByUsername(String phone);

	String getNewHouseIdsByMap(HashMap<String, Object> map);

	UserPo getUserByPhone(String phone);
	
	Integer findIsHistory(Map<String, Object> map);

	void updateLookHistory(Map<String, Object> map);
	
	void insertLookHistory(Map<String, Object> map);

	/**
	 * 参数3个
	 * 1. type:枚举类型
	 * 2. objectId: 经纪人/新房/二手房/租房 id
	 * 3. userId:登录用户的Id
	 */
	void updateHandleLookHistory(int type,long objectId,long userId);
	
	List<String> getAdminPhones();

	UserPo selectUser(String openId, int i);
}