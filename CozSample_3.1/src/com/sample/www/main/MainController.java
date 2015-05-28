
package com.sample.www.main;

import java.util.ArrayList;
import java.util.List;

import jxl.format.Alignment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sample.common.Constants;
import com.sample.common.controller.CommonController;

@Controller
public class MainController extends CommonController{
	
	protected Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	MainService mainService;
	
	@RequestMapping(value = "/main")
	public ModelAndView mainView() throws Exception {		
		
		ModelAndView modelAndView = new ModelAndView();
		
		logger.debug("mainView>>>>>");
		
		String fileUploadTempDir = Constants.configProp.getProperty("system.fileUploadTempDir");
		logger.debug("fileUploadTempDir>>>>>"+fileUploadTempDir);
		
		String userName = "";
		userName = mainService.getMainInfo();
//		userName = mainService.updateMainInfo();

		modelAndView.addObject("errorYn", "N");
		modelAndView.addObject("userName", userName);
		//modelAndView.addObject("userName", "1234");
		modelAndView.setViewName("main");		
		return modelAndView;		
	}
	
	@RequestMapping(value = "/excelDown")
	public ModelAndView excelDown() throws Exception {		
		
		ModelAndView modelAndView = new ModelAndView("excelView");
		
		logger.debug("excelDown>>>>>");
		
		List<String> listColumn = new ArrayList<String>();
		List<List<Object>> listData = new ArrayList<List<Object>>();
		List<List<Object>> alignData = new ArrayList<List<Object>>();
		
		listColumn.add("NO");
		listColumn.add("Col1");
		listColumn.add("Col2");
		
		List<Object> row = new ArrayList<Object>();
		row.add(1);
		row.add("1111");
		row.add("aaaa");
		listData.add(row);	
		
		for(int i = 0; i < 10; i++) {
			List<Object> alignColumn = new ArrayList<Object>();
			alignColumn.add(i);
			alignColumn.add(Alignment.LEFT);
			alignData.add(alignColumn);
		}
		
		modelAndView.addObject("fileName", "엑셀테스트");
		modelAndView.addObject("sheetName", "테스트시트");
		modelAndView.addObject("listData", listData);
		modelAndView.addObject("listColumn", listColumn);
		modelAndView.addObject("alignData", alignData);
		
		return modelAndView;		
	}
}