
package com.sample.common;

import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class Constants{
	
	public static String Test1 = "1111";
	
	public static Properties configProp;
	
	public static void setConfigProp(Properties configProp) {
		Constants.configProp = configProp;
	}

}