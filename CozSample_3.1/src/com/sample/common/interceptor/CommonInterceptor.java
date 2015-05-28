
package com.sample.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


@Controller
public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		logger.debug("preHandle>>>>>");
		//response.sendRedirect("/login/login.do");
		
		return true;
	}

}