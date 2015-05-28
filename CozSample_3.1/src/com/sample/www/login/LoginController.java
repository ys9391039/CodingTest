
package com.sample.www.login;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value = "/login/login")
	public ModelAndView loginView() throws Exception {				

		logger.debug("loginView>>>>>");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("errorYn", "N");

		return modelAndView;		
	}

}