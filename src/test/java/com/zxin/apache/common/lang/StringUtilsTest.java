package com.zxin.apache.common.lang;

import static org.junit.Assert.*;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(StringUtilsTest.class);

	@Test
	public void testJoin(){
//		String[] arrayNull = {"a",null,"c","d"};
//		assertEquals("a,b,c,d", StringUtils.join(arrayNull,","));
		String[] arrayEmpty = {"a","","c","d"};
		assertEquals("a,b,c,d", StringUtils.join(arrayEmpty,","));
//		String[] array = {"a","b","c","d"};
//		assertEquals("a,b,c,d", StringUtils.join(array,","));
		
	}
}

