package com.zxin.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	
	public static Properties getProperties(String fileName){
		Properties prop = new Properties();     
		try {
			URL url = ConfigurationUtils.locate(fileName);
			InputStream in = url.openStream();
			prop.load(in);     ///加载属性列表
			in.close();
		} catch (IOException e) {
			logger.error("Properties IO error", e);
		}
		return prop;
	}
	
	
	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getProperties("db.properties"));
	}
}