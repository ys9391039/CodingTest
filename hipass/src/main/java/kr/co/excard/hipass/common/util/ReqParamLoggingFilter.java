package kr.co.excard.hipass.common.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReqParamLoggingFilter implements Filter {

	private static final Log logger = LogFactory.getLog(ReqParamLoggingFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) 
				throws IOException, ServletException {
	
		boolean existParameters = request.getParameterNames().hasMoreElements();
		@SuppressWarnings("unchecked")
		Enumeration<String> params = (Enumeration<String>)request.getParameterNames();
		String paramKey, paramValue;
		
		HttpServletRequest req = (HttpServletRequest) request;
		if (!"".equals(req.getRequestURI())&&req.getRequestURI()!=null) {
			logger.debug("=======================URI=======================");
			logger.debug("RemoteAddr: "+req.getRemoteAddr());
			logger.debug("URI: "+req.getRequestURI());
		}
		
		if (existParameters) {
			logger.debug("--------------------parameter--------------------");
			while (params.hasMoreElements()) {
				paramKey = params.nextElement();
				paramValue = request.getParameter(paramKey);
			    logger.debug(paramKey + ": " + paramValue);
			}
		}
		logger.debug("=================================================");
		
		chain.doFilter (request, response);
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

}