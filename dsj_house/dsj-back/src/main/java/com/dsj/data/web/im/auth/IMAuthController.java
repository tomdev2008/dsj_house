package com.dsj.data.web.im.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;

/**
 * 楼盘IM权限管理
 */
@Controller
@RequestMapping(value = "back/**/im/auth")
public class IMAuthController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMAuthController.class);

	@Autowired
	private EasemobUserService easemobUserService;

	@Autowired
	private UserService userService;
	
	/**
	 * 注册激活单个用户IM账号
	 * 
	 * @return
	 */
	@RequestMapping("registerUser")
	public AjaxResultVo registerEasemobUser(String username, String realname) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			if (StringUtils.isNotEmpty(username)) {
				EasemobUserPo easemobUser = new EasemobUserPo();
				easemobUser.setUserName(username);
				easemobUser.setPassWord("@b357q32");
				easemobUser.setNickName(realname);
				Object result = easemobUserService
						.createNewEasemobUserSingle(easemobUser);
				if (StringUtils.isNotEmpty(realname) 
						&& StringUtils.isNotEmpty(result.toString()) 
						&& result.toString().indexOf(username) > -1) {
					result = easemobUserService
						.modifyEasemobUserNickNameWithAdminToken(easemobUser);
				}
				ajax.setStatusCode(StatusCode.SUCCESS);
			} else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("注册激活用户账号错误");
			}
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("注册激活用户IM账号异常", e);
		}
		return ajax;
	}
	
	/**
	 * 注册激活经纪人用户IM账号
	 * 
	 * @return
	 */
	@RequestMapping("registerAgents")
	public AjaxResultVo registerEasemobAgents(Long userId) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			paramMap.put("delFlag", 2);
			paramMap.put("markFlag", 0);
			if (userId != null) 
				paramMap.put("id", userId);
			List<UserPo> list = userService.listAgentUserBy(paramMap);
			if (!CollectionUtils.isEmpty(list)) {
				for (UserPo po :  list) {
					EasemobUserPo easemobUser = new EasemobUserPo();
					easemobUser.setUserName(po.getUsername());
					easemobUser.setPassWord(po.getImPassword());
					easemobUser.setNickName(po.getRealname());
					Object result = easemobUserService
							.createNewEasemobUserSingle(easemobUser);
					if (StringUtils.isNotEmpty(po.getRealname()) 
							&& StringUtils.isNotEmpty(result.toString()) 
							&& result.toString().indexOf(po.getUsername()) > -1) {
						easemobUserService
							.modifyEasemobUserNickNameWithAdminToken(
									easemobUser);
					}
					po.setMarkFlag(1);
					userService.updateDynamic(po);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("注册激活经纪人用户IM账号异常", e);
		}
		return ajax;
	}
	
	/**
	 * 注册激活经纪人用户IM账号
	 * 
	 * @return
	 */
	@RequestMapping("registerUsers")
	public AjaxResultVo registerEasemobUsers(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			paramMap.put("delFlag", 2);
			paramMap.put("markFlag", 0);
			if (id != null) 
				paramMap.put("id", id);
			List<UserPo> list = userService.listBy(paramMap);
			if (!CollectionUtils.isEmpty(list)) {
				for (UserPo po :  list) {
					EasemobUserPo easemobUser = new EasemobUserPo();
					easemobUser.setUserName(po.getUsername());
					easemobUser.setPassWord(po.getImPassword());
					easemobUser.setNickName(po.getRealname());
					Object result = easemobUserService
							.createNewEasemobUserSingle(easemobUser);
					if (StringUtils.isNotEmpty(po.getRealname()) 
							&& StringUtils.isNotEmpty(result.toString()) 
							&& result.toString().indexOf(po.getUsername()) > -1) {
						easemobUserService
							.modifyEasemobUserNickNameWithAdminToken(
									easemobUser);
					}
					po.setMarkFlag(1);
					userService.updateDynamic(po);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("注册激活用户IM账号异常", e);
		}
		return ajax;
	}
	
	/**
	 * 修改IM账号昵称
	 * 
	 * @return
	 */
	@RequestMapping("updateNickname")
	public AjaxResultVo updateNickname(AgentPo agent) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			paramMap.put("delFlag", 1);
			paramMap.put("markFlag", 1);
			if (agent != null && agent.getUserId() != null) 
				paramMap.put("id", agent.getUserId());
			List<UserPo> list = userService.listBy(paramMap);
			if (!CollectionUtils.isEmpty(list)) {
				for (UserPo po :  list) {
					EasemobUserPo easemobUser = new EasemobUserPo();
					easemobUser.setUserName(po.getUsername());
					easemobUser.setNickName(po.getRealname());
					easemobUserService.modifyEasemobUserNickNameWithAdminToken(
							easemobUser);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("IM账号昵称修改异常", e);
		}
		return ajax;
	}
	
}
