package com.dsj.data.web.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dsj.common.utils.code.VerifyCodeUtils;
import com.dsj.common.web.BaseController;


/**
 * 登录
 */
@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {

	/**
	 * loginUrl
	 * @author gaocj
	 */
	@RequestMapping("to_login")
	public String toLogin(Model model) {
		
		return "login/login";
		
	}
	

	/**
	 * loginUrl
	 * @author gaocj
	 */
	@RequestMapping("to_logout")
	public String logout(Model model) {
		SecurityUtils.getSubject().getSession().removeAttribute("sessionDeveloperUser");
		return "redirect:logout";
	}
	
	
	/**
	 * 权限不足Url
	 * @author gaocj
	 */
	@RequestMapping("to_unauthorized")
	public String toUnauthorized(Model model) {
		
		return "login/unauthorized";
		
	}
	
	/**
	 * shiro登录验证通过的successUrl
	 * @author gaocj
	 */
	@RequestMapping("to_index")
	public String index(Model model) {
		return "redirect:/auth/home";
		
	}
	
	
	/**
	 * 验证码
	 * @author gaocj
	 */
	@RequestMapping("verifyCode")
	public void verifyCode(HttpServletRequest req,HttpServletResponse resp) {
		//生成四位数的验证码,存到session中
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		req.getSession().setAttribute("adminLoginCode", verifyCode);
		try {
			//输出验证码到页面
			VerifyCodeUtils.outputImage(110, 50, resp.getOutputStream(), verifyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("验证码输出异常");
			e.printStackTrace();
		}
	}
	
	
}
