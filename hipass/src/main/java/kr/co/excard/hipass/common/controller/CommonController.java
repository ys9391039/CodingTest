
package kr.co.excard.hipass.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import kr.co.excard.hipass.common.exception.CustomException;


@Controller
public class CommonController {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest req, Exception e) {
 
		//logger.error("handleException:" + e.getMessage());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 1000);
		modelAndView.addObject("resultMsg", e.getMessage());
 
		return modelAndView;
 	}

	@ExceptionHandler(CustomException.class)
	public ModelAndView handleDADException(HttpServletRequest req, CustomException e) {
 
		//logger.error("handleCustomException:" + e.getErrorCode());
		//logger.error("handleCustomException:" + e.getErrorMsg());
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", e.getErrorCode());
		modelAndView.addObject("resultMsg", e.getErrorMsg());
 
		return modelAndView;
 	}
	
}