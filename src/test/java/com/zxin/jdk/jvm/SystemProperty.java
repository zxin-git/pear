package com.zxin.jdk.jvm;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemProperty {

	private static Logger logger = LoggerFactory.getLogger(SystemProperty.class);
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(Json.toJson(System.getProperties()));
	}
}

