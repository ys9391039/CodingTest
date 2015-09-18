
package kr.co.excard.hipass.common;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class Constants{
	
	public static Properties configProp;
	
	public static void setConfigProp(Properties configProp) {
		Constants.configProp = configProp;
	}
	
	public static String FILE_ROOT_DIR = "system.fileRootDir";
	public static String CAR_TYPE = "system.carType";
	public static String CARD_NAME = "system.cardName";
	public static String CARD_NUMBER = "system.cardNumber";
	public static String TX_SERIAL = "system.txSerial";
	
}