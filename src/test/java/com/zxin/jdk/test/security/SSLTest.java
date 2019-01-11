package com.zxin.jdk.test.security;

import javax.net.ssl.SSLContext;

import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLTest {

	private static final Logger logger = LoggerFactory.getLogger(SSLTest.class);

	public static void main(String[] args) {
		SSLContext sslcontext = SSLContexts.createDefault();
	}
}

