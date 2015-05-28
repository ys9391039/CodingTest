package com.sample.common.util;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReqParamLoggingFilter implements Filter {

	private static final Log logger = LogFactory.getLog(ReqParamLoggingFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) 
				throws IOException, ServletException {
	
		boolean existParameters = request.getParameterNames().hasMoreElements();
		Enumeration<String> params = request.getParameterNames();
		String paramKey, paramValue;
		
		if (existParameters) {
			logger.debug("-------------------------parameters-------------------------");
			while (params.hasMoreElements()) {
				paramKey = (String)params.nextElement();
				paramValue = request.getParameter(paramKey);
			    logger.debug(paramKey + ": " + paramValue);
			}
			logger.debug("-------------------------parameters-------------------------");
		}
		
		chain.doFilter (request, response);
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

}