package com.dsj.data.shiro;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ResourceCtrlInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI=request.getRequestURI();
		@SuppressWarnings("unchecked")
		List<String> patterns=(List<String>)SecurityUtils.getSubject().getSession().getAttribute("menusPatterns");
		if(null!=patterns){
			for(String pattern:patterns){
				if(requestURI.startsWith(pattern.replace("/frame", ""))){
					return true;
				}
			}
		}else{
			response.setStatus(HttpStatus.SC_FORBIDDEN);
			response.getWriter().write("您没有任何权限,请联系管理员!");
			return false;
		}
		response.setStatus(HttpStatus.SC_FORBIDDEN);
		response.getWriter().write("您无权访问此页面!");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
