package com.zxin.jdk.node.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.mvc.core.util.SerializeUtil;

public class XmlTest {

	private static Logger logger = LoggerFactory.getLogger(XmlTest.class);
	
	public static void main(String[] args) {
		try {
			System.out.println(SerializeUtil.getInstance().fromXml(new FileInputStream(new File("d:/z.txt"))));
		} catch (FileNotFoundException e) {
			logger.debug("",e);
		}
	}
}

