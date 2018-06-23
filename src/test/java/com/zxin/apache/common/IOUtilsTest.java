package com.zxin.apache.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtilsTest {

	private static Logger logger = LoggerFactory.getLogger(IOUtilsTest.class);
	
	public static void main(String[] args) {
		File file = new File("");
		try {
			FileUtils.touch(file);
		} catch (IOException e) {
			logger.debug("",e);
		}
	}
}

