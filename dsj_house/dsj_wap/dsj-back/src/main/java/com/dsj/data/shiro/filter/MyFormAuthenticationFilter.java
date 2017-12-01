package com.dsj.data.shiro.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {
	private String vcodeParam="verifyCode";
	
	@Autowired
	private UserService userService;
	//重写登录成功,添加最后登录时间跟ip
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		//获取用户名
		String username=(String)token.getPrincipal();
		//获取ip
		String ip = getIpAddr(request);
		UserPo adminPo = new UserPo();
		adminPo.setLastloginTime(new Date());
		adminPo.setIp(ip);
		adminPo.setUsername(username);
		
		//通过用户名修改用户ip,lastlogintime
	//	userService.updateLastLoginTime(adminPo);
		subject.hasRole("*");
		
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	//重写登录filter,验证登录的验证码
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		String vcode=request.getParameter(vcodeParam);
		Object code=org.apache.shiro.web.util.WebUtils.toHttp(request).getSession().getAttribute("adminLoginCode");
		try{
			//跟session中的code做比较,如果不正确,抛异常,(编写验证码异常.)
			/*if(vcode==null || code==null || !vcode.equalsIgnoreCase(code.toString())){
				throw new VerifyCodeException("verify code is incorrect");
			}*/
		} catch (AuthenticationException e) {
			//set异常,并让请求继续,最后返回登录页面
              setFailureAttribute(request, e);
              return true;
        }
		return super.executeLogin(request, response);
	}
	
	//重写登录filter,让登录过的用户登录到成功页面
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
//		boolean ret= super.isAccessAllowed(request, response, mappedValue);

		if(isLoginRequest(request, response)&& SecurityUtils.getSubject().isAuthenticated()){
			try {
				//如果是登录,并且请求登录
				issueSuccessRedirect(request, response);//重定向到登录成功页面
			} catch (Exception e) {
				e.printStackTrace();
			}
			return false;
		}
		
		return  super.isAccessAllowed(request, response, mappedValue);
	}
	//获取用户的ip
	private String getIpAddr(ServletRequest req) {   
		
		 HttpServletRequest request=(HttpServletRequest) req;
		
	     String ipAddress = null;   
	     ipAddress = request.getHeader("x-forwarded-for");   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	      ipAddress = request.getHeader("Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	         ipAddress = request.getHeader("WL-Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	      ipAddress = request.getRemoteAddr();   
	      if(ipAddress.equals("127.0.0.1")){   
	       //根据网卡取本机配置的IP   
	       InetAddress inet=null;   
	    try {   
	     inet = InetAddress.getLocalHost();   
	    } catch (UnknownHostException e) {   
	     e.printStackTrace();   
	    }   
	    ipAddress= inet.getHostAddress();   
	      }   
	            
	     }   
	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
	     if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15   
	         if(ipAddress.indexOf(",")>0){   
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
	         }   
	     }   
	     return ipAddress;    
	  } 
	
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		org.apache.shiro.web.util.WebUtils.issueRedirect(request, response, getSuccessUrl(), null, true);
	}

}
