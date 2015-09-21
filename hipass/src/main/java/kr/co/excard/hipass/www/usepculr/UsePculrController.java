package kr.co.excard.hipass.www.usepculr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.excard.hipass.common.Constants;
import kr.co.excard.hipass.common.controller.CommonController;
import kr.co.excard.hipass.common.exception.CustomException;
import kr.co.excard.hipass.common.models.BillInfo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/usepculr")
public class UsePculrController extends CommonController{
	
	protected Log logger = LogFactory.getLog(this.getClass());
	private	final static String fileRootDir = Constants.configProp.getProperty(Constants.FILE_ROOT_DIR);
	
	@RequestMapping(value = "/UsePculrReceiptPrint.do")
	public ModelAndView UsePculrReceiptPrint() throws CustomException {				
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usepculr/UsePculrReceiptPrint");
		return modelAndView;		
	}
	
	@RequestMapping(value = "/UsePculrReceiptPrintAjax.json")
	public ModelAndView UsePculrReceiptPrintAjax(HttpServletRequest req) throws CustomException {				
		
		// 카드번호는 세션에서 꺼내서 사용
		String cardNumber = (String)req.getSession().getAttribute("cardNumber");
		
		// 기본정보 읽어오기
		File file = new File(fileRootDir+"basicData_"+ cardNumber +".txt");
		logger.debug("file:::::"+file);
		FileInputStream fis = null;
		InputStreamReader isb = null;
		BufferedReader br = null;
		
		int carType = 0;
		String cardName = "";
		//String cardNumber = "";
		try{
			fis = new FileInputStream(file);
			isb = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isb);
			
			String tmp = br.readLine();
			logger.debug("tmp:::::"+tmp);
			String tmpArr[] = tmp.split("\\|");
			logger.debug("tmpArr.length:::::"+tmpArr.length);
						
			carType = Integer.valueOf(tmpArr[0]);
			cardName = tmpArr[1];
			//cardNumber = tmpArr[2];
			
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
			throw new CustomException(2001, e.getMessage());
		}finally{
			try{fis.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{isb.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{br.close();	}catch(Exception e){System.out.println(e.getMessage());}
		}
		
		// 영수증 정보 읽어오기
		file = new File(fileRootDir+"billData_"+ cardNumber +".txt");
//		System.out.println("file:::::"+file);
		logger.debug("file:::::"+file);
		fis = null;
		isb = null;
		br = null;
		
		List<BillInfo> billInfoList = new ArrayList<BillInfo>();
		
		try{
			fis = new FileInputStream(file);
			isb = new InputStreamReader(fis, "UTF-8");
			br = new BufferedReader(isb);
			String tmp;
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMaximumIntegerDigits(9);
			while((tmp = br.readLine()) != null){
//				System.out.println(tmp);
				logger.debug("tmp:::::"+tmp);
				String tmpArr[] = tmp.split("\\|");
//				System.out.println(tmpArr[1]);
				logger.debug("tmpArr.length:::::"+tmpArr.length);
				
				String billAmount = nf.format(Integer.valueOf(tmpArr[6]));
				String billAmount1 = nf.format(Integer.valueOf(tmpArr[8]));
				String totalAmount = nf.format(Integer.valueOf(tmpArr[9]));
				String taxAmount = nf.format(Integer.valueOf(tmpArr[10]));

				String billAmount2 = "";
				if (!"".equals(tmpArr[16])) billAmount2 = nf.format(Integer.valueOf(tmpArr[16]));
				String billAmount3 = "";
				if (!"".equals(tmpArr[17])) billAmount3 = nf.format(Integer.valueOf(tmpArr[17]));
				String billAmount4 = "";
				if (!"".equals(tmpArr[18])) billAmount4 = nf.format(Integer.valueOf(tmpArr[18]));
				String billAmount5 = "";
				if (!"".equals(tmpArr[19])) billAmount5 = nf.format(Integer.valueOf(tmpArr[19]));
				
			
				BillInfo billInfo = new BillInfo();
				billInfo.setServiceName(tmpArr[0]);
				billInfo.setRegionName(tmpArr[1]);
				billInfo.setTgTel(tmpArr[2]);
				billInfo.setTgAccount(tmpArr[3]);
				billInfo.setPayDate(tmpArr[4]);
				billInfo.setTgName(tmpArr[5]);
				billInfo.setBillAmount(billAmount);
				
				billInfo.setTgType1(tmpArr[7]);
				billInfo.setBillAmount1(billAmount1);
				
				billInfo.setTotalAmount(totalAmount);
				billInfo.setTaxAmount(taxAmount);
				billInfo.setTxSerial(tmpArr[11]);
				
				billInfo.setTgType2(tmpArr[12]);
				billInfo.setTgType3(tmpArr[13]);
				billInfo.setTgType4(tmpArr[14]);
				billInfo.setTgType5(tmpArr[15]);
				
				billInfo.setBillAmount2(billAmount2);
				billInfo.setBillAmount3(billAmount3);
				billInfo.setBillAmount4(billAmount4);
				billInfo.setBillAmount5(billAmount5);

				billInfo.setCarType(Integer.valueOf(carType));
				billInfo.setCardName(cardName);
				billInfo.setCardNumber(cardNumber);
				
				billInfoList.add(billInfo);
			}
			
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
			throw new CustomException(2002, e.getMessage());
		}finally{
			try{fis.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{isb.close();}catch(Exception e){System.out.println(e.getMessage());}
			try{br.close();	}catch(Exception e){System.out.println(e.getMessage());}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 0);
		modelAndView.addObject("billInfoList", billInfoList);
		return modelAndView;		
	}
	
}