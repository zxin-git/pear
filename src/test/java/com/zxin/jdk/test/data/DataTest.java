package com.zxin.jdk.test.data;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTest {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void cloneTest(){
		Data d = new Data();
		d.setName("zzzz");
		try {
			Data d2 = d.clone();
			logger.debug(d2.getName());
		} catch (CloneNotSupportedException e) {
			logger.debug("",e);
		}
	}
}
