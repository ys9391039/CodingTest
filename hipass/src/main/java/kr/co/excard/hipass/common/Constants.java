
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
	public static String ID = "user.id";
	public static String PASSWORD = "user.password";
	public static String APPVERSION = "system.appVersion";
	
}