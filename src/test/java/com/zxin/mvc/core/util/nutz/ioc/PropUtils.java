package com.zxin.mvc.core.util.nutz.ioc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class PropUtils {
	
	private final static String propPath = System.getProperty("classes.dir")+"ztc_kb_event.properties";
	
	public static Properties props = getProp();
	
//	public static List<Map<>> getPagerColumns(String pid){
//		for (String key : props.stringPropertyNames()) {  
//			if(key.startsWith(pid) && key.endsWith("show"))
//	        System.out.println(key + "=" + props.getProperty(key));  
//	    } 
//		return null;
//	}
	
	public static Map<String,String> getColumns(String pid){
//		List<Map<String,String>> list = new ArrayList<>();
		props = getProp();
		Map<String,String> map = new HashMap<>();
		for (String key : props.stringPropertyNames()) {  
			if(key.startsWith(pid)){
				map.put(cut(key), props.getProperty(key));
//				list.add(map);
			}
	         
	    } 
		return map;
	}
	
	public static String cut(String key){
		String str = key.substring(key.indexOf("_")+1); 		
		return str;
	}
	
	public String getValue(String key){
		return props.getProperty("key");
	}
	
	/**
	 * 读取properties文件
	 */
	public static Properties getProp() {

		Properties p = new Properties();
		FileInputStream f = null;
		try {
			f = new FileInputStream(propPath);
			p.load(f);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return p;
	}
}
