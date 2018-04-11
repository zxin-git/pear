package com.zxin.jdk.test.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.jdk.test.data.Data;
import com.zxin.jdk.test.io.InputStreamReaderTest;

public class ArraysTest {
	
	Logger logger = LoggerFactory.getLogger(ArraysTest.class);
	
	@Test
	public void asList(){
		List<Integer> list = Arrays.asList(1,2,3,4,5);
		logger.debug(list.getClass().getName());
		
	}
	
}
