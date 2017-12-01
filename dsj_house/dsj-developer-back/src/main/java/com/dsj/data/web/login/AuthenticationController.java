package com.dsj.data.web.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.UserPo;

/**
 * 登录
 */
@Controller
@RequestMapping(value = "auth/home")
public class AuthenticationController {
	@RequestMapping
	public String toLogin(Model model) {
		UserPo user=ShiroUtils.getSessionUser();
		if(user.getStatus()==UserStatusEnum.YES.getValue()){
			return "redirect:/app/responsible.html";
		}
		 return "redirect:/app/person-detail.html";
	}
}
