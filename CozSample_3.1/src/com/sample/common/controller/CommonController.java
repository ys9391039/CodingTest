
package com.sample.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception e) {
 
		logger.debug(">>>>>" + e);
		
		ModelAndView model = new ModelAndView("error/common_error");
		model.addObject("errMsg", "this is Exception.class");
 
		return model;
 	}

}