package com.dsj.modules.easemob.service;

import java.util.List;

import com.dsj.modules.easemob.po.EasemobUserPo;

/**
*
* @描述: Service接口.
* @作者: wangjl.
* @创建时间: 2017-07-27 11:17:27.
* @版本: 1.0 .
*/
public interface EasemobUserService {

	/**
	 * 注册环信用户[单个]
	 * POST
	 * 
	 * @param po
	 * @return
	 */
	Object createNewEasemobUserSingle(EasemobUserPo po);

	/**
	 * 注册环信用户[批量]
	 * POST
	 * 
	 * @param list
	 * @return
	 */
	Object createNewEasemobUserBatch(List<EasemobUserPo> list);

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
	 * @param po
	 * @return
	 */
	Object modifyEasemobUserPasswordWithAdminToken(EasemobUserPo po);

	/**
	 * 修改用户昵称
	 * PUT
	 * 
	 * @param po
	 * @return
	 */
	Object modifyEasemobUserNickNameWithAdminToken(EasemobUserPo po);

	/**
	 * 给环信用户的添加好友
	 * POST
	 * 
	 * @param po
	 * @return
	 */
	Object addFriendSingle(EasemobUserPo po);

	/**
	 * 解除环信用户的好友关系
	 * DELETE
	 * 
	 * @param po
	 * @return
	 */
	Object deleteFriendSingle(EasemobUserPo po);

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
	 * @param po
	 * @return
	 */
	Object addToBlackList(EasemobUserPo po);

	/**
	 * 从环信用户的黑名单中减人
	 * DELETE
	 * 
	 * @param po
	 * @return
	 */
	Object removeFromBlackList(EasemobUserPo po);

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
