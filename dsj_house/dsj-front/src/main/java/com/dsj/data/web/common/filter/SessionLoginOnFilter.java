package com.dsj.data.web.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;

/**
 * H5登录filter,
 * 
 * @create 2015-9-30
 */

public class SessionLoginOnFilter implements Filter {

	static Logger LOGGER = LoggerFactory.getLogger(SessionLoginOnFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getRequestURI();
		
			if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {

				HttpServletResponse response = (HttpServletResponse) res;
				if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
					LOGGER.info("XMLHttpRequest url:{}", request.getRequestURI());
					chain.doFilter(req, res);
					return;
				}
				// 未登录尝试自动登录
				HttpSession session = request.getSession();

				String path = request.getContextPath();
				// 参数
				String queryString = request.getQueryString();
				if (StringUtils.isNotBlank(queryString)) {
					url = url.concat("?").concat(queryString);
				}
				session.setAttribute(CommConst.COOKIE_PC_LOGIN_URL, url);
				LOGGER.info("session add url:{}, getQueryString:{}", url, queryString);
				
				chain.doFilter(req, res);
			}
		
		//chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
	
	
	/**
	 * ajax 返回数据
	 * @param response
	 */
	private void responseJson(HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		String jsonStr = "{\"code\":500,\"msg\":\"no seesion!\",\"redirect\":\"/front/login/toLogin\"}";
		PrintWriter out = null;
		try {
		    out = response.getWriter();
		    out.write(jsonStr);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (out != null) {
		        out.close();
		    }
		}
	}

}
