package com.dsj.modules.easemob.service.impl;

import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;
import io.swagger.client.model.UserName;
import io.swagger.client.model.UserNames;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dsj.modules.easemob.dao.EasemobUserDao;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;

/**
*
* @描述: Service接口实现类.
* @作者: wangjl.
* @创建时间: 2017-07-27 11:17:27.
* @版本: 1.0 .
*/
@Service
public class EasemobUserServiceImpl implements EasemobUserService {
	
	private final Logger logger 
		= LoggerFactory.getLogger(EasemobUserServiceImpl.class);
	
	@Autowired
	private EasemobUserDao easemobUserDao;
	
	@Override
	public Object createNewEasemobUserSingle(EasemobUserPo po) {
		RegisterUsers registerUsers = new RegisterUsers();
		User user = new User().username(po.getUserName())
				.password(po.getPassWord());
		registerUsers.add(user);
		Object object = easemobUserDao
				.createNewEasemobUserSingle(registerUsers);
		return object;
	}

	@Override
	public Object createNewEasemobUserBatch(List<EasemobUserPo> list) {
		if (!CollectionUtils.isEmpty(list)) {
			return easemobUserDao.createNewEasemobUserBatch(list);
		}
		return null;
	}

	@Override
	public Object getEasemobUserByUserName(String userName) {
		return easemobUserDao.getEasemobUserByUserName(userName);
	}

	@Override
	public Object getEasemobUsersBatch(Long limit, String cursor) {
		return easemobUserDao.getEasemobUsersBatch(limit, cursor);
	}

	@Override
	public Object deleteEasemobUserByUserName(String userName) {
		return easemobUserDao.deleteEasemobUserByUserName(userName);
	}

	@Override
	public Object deleteEasemobUserBatch(Long limit, String cursor) {
		return easemobUserDao.deleteEasemobUserBatch(limit, cursor);
	}

	@Override
	public Object modifyEasemobUserPasswordWithAdminToken(EasemobUserPo po) {
		if (po.getUserName() != null && po.getNewpassword() != null) {
			return easemobUserDao.modifyEasemobUserPasswordWithAdminToken(
					po.getUserName(), po.getNewpassword());
		}
		return null;
	}

	@Override
	public Object modifyEasemobUserNickNameWithAdminToken(EasemobUserPo po) {
		Object object = null;
		if (po.getUserName() != null && po.getNickName() != null) {
			Nickname nickname = new Nickname().nickname(po.getNickName());  
			object = easemobUserDao.modifyEasemobUserNickNameWithAdminToken(
					po.getUserName(), nickname);
		}
		return object;
	}

	@Override
	public Object addFriendSingle(EasemobUserPo po) {
		if (po.getUserName() != null 
				&& !CollectionUtils.isEmpty(po.getFriendNames())) {
			return easemobUserDao.deleteFriendSingle(
					po.getUserName(), po.getFriendNames().get(0));
		}
		return null;
	}

	@Override
	public Object deleteFriendSingle(EasemobUserPo po) {
		if (po.getUserName() != null 
				&& !CollectionUtils.isEmpty(po.getFriendNames())) {
			return easemobUserDao.deleteFriendSingle(
					po.getUserName(), po.getFriendNames().get(0));
		}
		return null;
	}

	@Override
	public Object getFriends(String userName) {
		return easemobUserDao.getFriends(userName);
	}

	@Override
	public Object getBlackList(String userName) {
		return easemobUserDao.getBlackList(userName);
	}

	@Override
	public Object addToBlackList(EasemobUserPo po) {
		if (po.getUserName() != null 
				&& !CollectionUtils.isEmpty(po.getFriendNames())) {
			UserName username = (UserName)po.getFriendNames();
			UserNames usernames = new UserNames().usernames(username);
			return easemobUserDao.addToBlackList(po.getUserName(), usernames);
		}
		return null;
	}

	@Override
	public Object removeFromBlackList(EasemobUserPo po) {
		if (po.getUserName() != null 
				&& !CollectionUtils.isEmpty(po.getFriendNames())) {
			for (String friendName : po.getFriendNames()) {
				easemobUserDao.removeFromBlackList(po.getUserName(), 
						friendName);
			}
		}
		return null;
	}

	@Override
	public Object getEasemobUserStatus(String userName) {
		return easemobUserDao.getEasemobUserStatus(userName);
	}

	@Override
	public Object getOfflineMsgCount(String userName) {
		return easemobUserDao.getOfflineMsgCount(userName);
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(String userName, String msgId) {
		return easemobUserDao.getSpecifiedOfflineMsgStatus(userName, msgId);
	}

	@Override
	public Object deactivateEasemobUser(String userName) {
		return easemobUserDao.deactivateEasemobUser(userName);
	}

	@Override
	public Object activateEasemobUser(String userName) {
		return easemobUserDao.activateEasemobUser(userName);
	}

	@Override
	public Object disconnectEasemobUser(String userName) {
		return easemobUserDao.disconnectEasemobUser(userName);
	}

	@Override
	public Object getEasemobUserAllChatGroups(String userName) {
		return easemobUserDao.getEasemobUserAllChatGroups(userName);
	}

	@Override
	public Object getEasemobUserAllChatRooms(String userName) {
		return easemobUserDao.getEasemobUserAllChatRooms(userName);
	}
	
}
