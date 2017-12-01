package com.dsj.data.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.BaseController;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.UserVo;


/**
 * 登录
 */
@Controller
@RequestMapping(value = "back/user")
public class UserController extends BaseController {
	@Autowired
	AgentService agentService;
	
	
	
	@RequestMapping("getUser")
	@ResponseBody
	public UserVo getUser(Model model) {
		UserVo vo=new UserVo();
		vo.setUrl(ConfigUtils.instance.getAgentProductName()+"/login/logout");
		BeanUtils.copyProperties( ShiroUtils.getSessionUser(),vo);
		AgentVo agent=agentService.getByUserId(ShiroUtils.getSessionUser().getId());
		if(agent!=null){
			vo.setAvatar(agent.getAvatarUrl());
		}
		vo.setAuditName(agent.getAuditName());
		vo.setAgentProductName( ConfigUtils.instance.getAgentProductName());
		return vo;
	}
	
	
}
