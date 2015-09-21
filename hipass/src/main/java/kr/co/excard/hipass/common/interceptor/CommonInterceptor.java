
package kr.co.excard.hipass.common.interceptor;

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
		
		String uri = request.getRequestURI();
		if (uri.indexOf("step1") > 0 || uri.indexOf("login") > 0) 
			return true;
			
		if (!"OK".equals(request.getSession().getAttribute("Login"))){
			//String returnUrl = request.getRequestURI()+(request.getQueryString()==null?"":URLEncoder.encode("?", "UTF-8")+request.getQueryString());
			response.sendRedirect("/");
			return true;
		}
		
		return true;
	}

}