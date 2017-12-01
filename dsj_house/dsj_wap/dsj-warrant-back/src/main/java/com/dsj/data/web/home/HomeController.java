package com.dsj.data.web.home;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.utils.code.VerifyCodeUtils;
import com.dsj.common.utils.redis.JedisProxy;
import com.dsj.common.web.BaseController;
import com.dsj.data.shiro.realm.MyRealm;
import com.dsj.data.web.utils.ShiroUtils;


/**
 * 登录
 */
@Controller
@RequestMapping(value = "back/frame/home")
public class HomeController extends BaseController {

	
	
	/**
	 * shiro登录验证通过的successUrl
	 * @author gaocj
	 */
	@RequestMapping("to_index")
	public String index(Model model) {
		return "home/welcome";
	}
	
	
}
