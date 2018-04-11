package com.zxin.jdk.test.lang;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringBuilderTest {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void appendTest(){
		StringBuilder sb = new StringBuilder("init");
		sb.append("secondzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
//		logger.debug();
	}
	
	@Test
	public void StringTest(){
		String str3="hello"+" word!";  
		String str4="hello word!";
		System.out.println(str3==str4);
//		logger.debug(str3==str4+"");
	}
	
}
