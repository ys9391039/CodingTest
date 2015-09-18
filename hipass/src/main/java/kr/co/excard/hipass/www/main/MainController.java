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
	
	@RequestMapping(value = "/main.do")
	public ModelAndView main() throws CustomException {				
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("main/main");
		return modelAndView;		
	}

	@RequestMapping(value = "/mainAjax.json")
	public ModelAndView mainAjax(HttpServletRequest req) throws CustomException {				
		
		String billInfoList = StringUtil.reqNullCheck(req, "billInfo");
		logger.debug("billInfo:::::"+billInfoList);
		
		String billInfoArr[] = billInfoList.split("@");
		logger.debug("billInfoArr.length:::::"+billInfoArr.length);
		
		File file = new File(fileRootDir+"billData.txt");
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
			
			for(String billInfo : billInfoArr){
				logger.debug("billInfo:::::"+billInfo);
				bw.write(billInfo);
				bw.newLine();
				bw.flush();
			}
		
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
		}finally{
			try{
				fos.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			try{
				out.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			try{
				bw.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 0);
		return modelAndView;		
	}
}