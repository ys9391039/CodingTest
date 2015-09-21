package kr.co.excard.hipass.www.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import kr.co.excard.hipass.common.Constants;
import kr.co.excard.hipass.common.controller.CommonController;
import kr.co.excard.hipass.common.exception.CustomException;
import kr.co.excard.hipass.common.util.StringUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/main")
public class MainController extends CommonController{
	
	protected Log logger = LogFactory.getLog(this.getClass());
	private	final static String fileRootDir = Constants.configProp.getProperty(Constants.FILE_ROOT_DIR);
	
	@RequestMapping(value = "/step1.do")
	public ModelAndView step1() throws CustomException {				
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main/step1");
		return modelAndView;		
	}
	
	@RequestMapping(value = "/step1Ajax.json")
	public ModelAndView step1Ajax(HttpServletRequest req) throws CustomException {				
		
		String carType = StringUtil.reqNullCheck(req, "carType");
		String cardName = StringUtil.reqNullCheck(req, "cardName");
		String cardNumber = StringUtil.reqNullCheck(req, "cardNumber");

		// 파일명에 카드번호가 포함되도록 처리
		File file = new File(fileRootDir+"basicData_"+ cardNumber +".txt");
		logger.debug("file:::::"+file);
		
		FileOutputStream fos = null;
		Writer out = null;
		//FileWriter fw = null;
		BufferedWriter bw = null;

		try{
			boolean ret = file.createNewFile();
			if (!ret)
				file.delete();
			
			fos = new FileOutputStream(file);
			out = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(out);
			//fw = new FileWriter(file, true);
			
			String basicInfo = carType + "|" + cardName + "|" + cardNumber;
			bw.write(basicInfo);
			bw.flush();
		
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
			throw new CustomException(1001, e.getMessage());
		}finally{
			try{fos.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{out.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{bw.close();}catch(Exception e){System.out.println(e.getMessage());}
		}

		// 세션저장
		req.getSession().setAttribute("Login", "OK");
		req.getSession().setAttribute("cardNumber", cardNumber);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 0);
		return modelAndView;		
	}

	@RequestMapping(value = "/step2.do")
	public ModelAndView step2(HttpServletRequest req) throws CustomException {				
		String receipts = StringUtil.reqNullCheck(req, "receipts");
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("receipts", receipts);
		modelAndView.setViewName("main/step2");
		return modelAndView;		
	}
	
	@RequestMapping(value = "/step2Ajax.json")
	public ModelAndView step2Ajax(HttpServletRequest req) throws CustomException {				
		
		String billInfoList = StringUtil.reqNullCheck(req, "billInfo");
		logger.debug("billInfo:::::"+billInfoList);
		
		String billInfoArr[] = billInfoList.split("@");
		logger.debug("billInfoArr.length:::::"+billInfoArr.length);
		
		// 파일명에 카드번호가 포함되도록 처리
		String cardNumber = (String)req.getSession().getAttribute("cardNumber");
		
		File file = new File(fileRootDir+"billData_"+ cardNumber +".txt");
		logger.debug("file:::::"+file);
		
		FileOutputStream fos = null;
		Writer out = null;
		BufferedWriter bw = null;
		
		try{
			boolean ret = file.createNewFile();
			if (!ret)
				file.delete();
			
			fos = new FileOutputStream(file);
			out = new OutputStreamWriter(fos, "UTF-8");
			bw = new BufferedWriter(out);
			
			for(String billInfo : billInfoArr){
				logger.debug("billInfo:::::"+billInfo);
				bw.write(billInfo);
				bw.newLine();
				bw.flush();
			}
		
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
			throw new CustomException(1002, e.getMessage());
		}finally{
			try{fos.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{out.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{bw.close();}catch(Exception e){System.out.println(e.getMessage());}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 0);
		return modelAndView;		
	}
}