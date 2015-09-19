package kr.co.excard.hipass.www.usepculr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

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
	private	final static String carType = Constants.configProp.getProperty(Constants.CAR_TYPE);
	private	final static String cardName = Constants.configProp.getProperty(Constants.CARD_NAME);
	private	final static String cardNumber = Constants.configProp.getProperty(Constants.CARD_NUMBER);
	private	final static String txSerials = Constants.configProp.getProperty(Constants.TX_SERIAL);
	
	@RequestMapping(value = "/UsePculrReceiptPrint.do")
	public ModelAndView UsePculrReceiptPrint() throws CustomException {				
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usepculr/UsePculrReceiptPrint");
		return modelAndView;		
	}
	
	@RequestMapping(value = "/UsePculrReceiptPrintAjax.json")
	public ModelAndView UsePculrReceiptPrintAjax() throws CustomException {				
		
		File file = new File(fileRootDir+"billData.txt");
//		System.out.println("file:::::"+file);
		logger.debug("file:::::"+file);
		FileInputStream fis = null;
		InputStreamReader isb = null;
		BufferedReader br = null;
		
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
				String txSerialArr[] = txSerials.split("\\|");
				
				int rnd = (int) (Math.random()*17);
				System.out.println("rnd:::::"+rnd);
				
				String billAmount = nf.format(Integer.valueOf(tmpArr[5]));
				
				if (tmpArr.length == 6){
					BillInfo billInfo = new BillInfo();
					billInfo.setRegionName(tmpArr[0]);
					billInfo.setTgTel(tmpArr[1]);
					billInfo.setTgAccount(tmpArr[2]);
					billInfo.setPayDate(tmpArr[3]);
					billInfo.setTgName(tmpArr[4]);
					billInfo.setCarType(Integer.valueOf(carType));
					billInfo.setBillAmount(billAmount);
					billInfo.setCardName(cardName);
					billInfo.setCardNumber(cardNumber);
					billInfo.setTxSerial(txSerialArr[rnd]);
					billInfoList.add(billInfo);
				}
			}
			
		}catch(Exception e){
			System.out.println("Error :"+e.getMessage());
			
		}finally{
			try{
				fis.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			try{
				isb.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			try{
				br.close();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("resultCode", 0);
		modelAndView.addObject("billInfoList", billInfoList);
		return modelAndView;		
	}
	
}