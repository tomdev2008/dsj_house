package com.dsj.modules.easemob.dao;

/**
*
* @描述: DAO接口.
* @作者: wangjl.
* @创建时间: 2017-07-27 11:17:27.
* @版本: 1.0 .
*/
public interface EasemobUserDao {

	/**
	 * 注册环信用户[单个]
	 * POST
	 * 
	 * @param payload
	 *            <code>{"username":"${用户名}","password":"${密码}", "nickname":"${昵称值}"}</code>
	 * @return
	 */
	Object createNewEasemobUserSingle(Object payload);

	/**
	 * 注册环信用户[批量]
	 * POST
	 * 
	 * @param payload
	 *            <code>[{"username":"${用户名1}","password":"${密码}"},…,{"username":"${用户名2}","password":"${密码}"}]</code>
	 * @return
	 */
	Object createNewEasemobUserBatch(Object payload);

	/**
	 * 获取环信用户[单个]
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getEasemobUserByUserName(String userName);

	/**
	 * 获取环信用户[批量]，参数为空时默认返回最早创建的10个用户
	 * GET
	 * 
	 * @param limit
	 *            单页获取数量
	 * @param cursor
	 *            游标，大于单页记录时会产生
	 * @return
	 */
	Object getEasemobUsersBatch(Long limit, String cursor);

	/**
	 * 删除环信用户[单个]
	 * DELETE
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object deleteEasemobUserByUserName(String userName);

	/**
	 * 删除环信用户[批量]，随机删除
	 * DELETE
	 * 
	 * @param limit
	 *            删除数量，建议100-500
	 * @return
	 */
	Object deleteEasemobUserBatch(Long limit, String cursor);

	/**
	 * 重置环信用户密码
	 * PUT
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param payload
	 *            <code>{"newpassword" : "${新密码指定的字符串}"}</code>
	 * @return
	 */
	Object modifyEasemobUserPasswordWithAdminToken(String userName, Object payload);

	/**
	 * 修改用户昵称
	 * PUT
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param payload
	 *            <code>{"nickname" : "${昵称值}"}</code>
	 * @return
	 */
	Object modifyEasemobUserNickNameWithAdminToken(String userName, Object payload);

	/**
	 * 给环信用户的添加好友
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param friendName
	 *            好友用戶名或用戶ID
	 * @return
	 */
	Object addFriendSingle(String userName, String friendName);

	/**
	 * 解除环信用户的好友关系
	 * DELETE
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param friendName
	 *            好友用戶名或用戶ID
	 * @return
	 */
	Object deleteFriendSingle(String userName, String friendName);

	/**
	 * 查看某个环信用户的好友信息
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getFriends(String userName);

	/**
	 * 获取环信用户的黑名单
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getBlackList(String userName);

	/**
	 * 往环信用户的黑名单中加人
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param payload
	 *            <code>{"usernames":["5cxhactgdj", "mh2kbjyop1"]}</code>
	 * @return
	 */
	Object addToBlackList(String userName, Object payload);

	/**
	 * 从环信用户的黑名单中减人
	 * DELETE
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param blackListName
	 *            黑名单用戶名或用戶ID
	 * @return
	 */
	Object removeFromBlackList(String userName, String blackListName);

	/**
	 * 查看用户在线状态
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getEasemobUserStatus(String userName);

	/**
	 * 查询离线消息数
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getOfflineMsgCount(String userName);

	/**
	 * 查询某条离线消息状态
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @param msgId
	 *            消息ID
	 * @return
	 */
	Object getSpecifiedOfflineMsgStatus(String userName, String msgId);

	/**
	 * 用户账号禁用
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object deactivateEasemobUser(String userName);

	/**
	 * 用户账号解禁
	 * POST
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object activateEasemobUser(String userName);

	/**
	 * 强制用户下线
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object disconnectEasemobUser(String userName);

	/**
	 * 获取用户参与的群组
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
	 *      60groupmgmt
	 */
	Object getEasemobUserAllChatGroups(String userName);

	/**
	 * 获取用户所有参与的聊天室
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
	 *      70chatroommgmt
	 */
	Object getEasemobUserAllChatRooms(String userName);
	
}
