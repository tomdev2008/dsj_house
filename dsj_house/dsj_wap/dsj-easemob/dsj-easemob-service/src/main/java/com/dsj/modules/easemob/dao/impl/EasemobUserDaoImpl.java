package com.dsj.modules.easemob.dao.impl;

import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.UserNames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dsj.modules.easemob.dao.EasemobUserDao;
import com.dsj.modules.easemob.util.EasemobAPI;
import com.dsj.modules.easemob.util.OrgInfo;
import com.dsj.modules.easemob.util.ResponseHandler;
import com.dsj.modules.easemob.util.TokenUtil;

/**
*
* @描述: Service接口实现类.
* @作者: wangjl.
* @创建时间: 2017-07-27 11:17:27.
* @版本: 1.0 .
*/
@Service
public class EasemobUserDaoImpl implements EasemobUserDao {
	
	private final Logger logger 
		= LoggerFactory.getLogger(EasemobUserDaoImpl.class);
	
	private UsersApi api = new UsersApi();
	
	private ResponseHandler responseHandler = new ResponseHandler();
	
	@Override
	public Object createNewEasemobUserSingle(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						(RegisterUsers)payload, 
						TokenUtil.instance.getAccessToken());
			}
		});
	}

	@Override
	public Object createNewEasemobUserBatch(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						(RegisterUsers)payload, 
						TokenUtil.instance.getAccessToken());
			}
		});
	}

	@Override
	public Object getEasemobUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(),
						userName);
		}
		});
	}

	@Override
	public Object getEasemobUsersBatch(final Long limit, final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(),
						limit + "", 
						cursor);
			}
		});
	}

	@Override
	public Object deleteEasemobUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDelete(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object deleteEasemobUserBatch(final Long limit, 
			final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersDelete(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						limit + "", 
						cursor);
			}
		});
	}

	@Override
	public Object modifyEasemobUserPasswordWithAdminToken(final String userName, 
			final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePasswordPut(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						userName, 
						(NewPassword)payload, 
						TokenUtil.instance.getAccessToken());
			}
		});
	}

	@Override
	public Object modifyEasemobUserNickNameWithAdminToken(final String userName, 
			final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePut(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						userName, 
						(Nickname)payload, 
						TokenUtil.instance.getAccessToken());
			}
		});
	}

	@Override
	public Object addFriendSingle(final String userName, 
			final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api
				.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName, 
						friendName);
			}
		});
	}

	@Override
	public Object deleteFriendSingle(final String userName, 
			final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api
				.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName, 
						friendName);
			}
		});
	}

	@Override
	public Object getFriends(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(),
						userName);
			}
		});
	}

	@Override
	public Object getBlackList(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object addToBlackList(final String userName, final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersPost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName, 
						(UserNames)payload);
			}
		});
	}

	@Override
	public Object removeFromBlackList(final String userName, 
			final String blackListName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api
				.orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(),
						TokenUtil.instance.getAccessToken(), 
						userName, 
						blackListName);
			}
		});
	}

	@Override
	public Object getEasemobUserStatus(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameStatusGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object getOfflineMsgCount(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(),
						userName);
			}
		});
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(final String userName, 
			final String msgId) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName, 
						msgId);
			}
		});
	}

	@Override
	public Object deactivateEasemobUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDeactivatePost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object activateEasemobUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameActivatePost(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object disconnectEasemobUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDisconnectGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}

	@Override
	public Object getEasemobUserAllChatGroups(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatgroupsGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(),
						userName);
			}
		});
	}

	@Override
	public Object getEasemobUserAllChatRooms(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatroomsGet(
						OrgInfo.instance.getORG_NAME(), 
						OrgInfo.instance.getAPP_NAME(), 
						TokenUtil.instance.getAccessToken(), 
						userName);
			}
		});
	}
}
