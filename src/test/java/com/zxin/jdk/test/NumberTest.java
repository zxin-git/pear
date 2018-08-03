package com.zxin.jdk.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberTest {

	private static Logger logger = LoggerFactory.getLogger(NumberTest.class);
	
	public static void main(String[] args) {
		int i = -15;
		int a = i%12;
		System.out.println(a);
	}
}

