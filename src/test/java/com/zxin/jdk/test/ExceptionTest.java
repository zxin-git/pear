package com.zxin.jdk.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionTest {

	private static Logger logger = LoggerFactory.getLogger(ExceptionTest.class);
	
	public static void main(String[] args) {
		
		try {
			exec();
		} catch (Exception e) {
			logger.debug("",e);
		}
	}
	
	
	public static void exec(){
		try {
			int i=0;
			int a = 100/i;
		} catch (Exception e) {
			System.out.println("catch");
			throw e;
		}finally {
			System.out.println("finally");
		}
	}
}

