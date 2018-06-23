package com.zxin.util;

import java.io.InputStreamReader;
import java.util.Properties;

public class PropertyUtil {
	public static Properties getProperties(String fileName){
		Properties prop = new Properties();     
		try{
			InputStreamReader in = new InputStreamReader(PropertyUtil.class.getClassLoader().getResourceAsStream(fileName), "UTF-8");
			prop.load(in);     ///加载属性列表
			in.close();
		}catch(Exception e){
			System.out.println(e);
		}
		return prop;
	}
	
	
	public static void main(String[] args) {
		System.out.println(PropertyUtil.getProperties("").getProperty("coreDataIp"));
	}
}