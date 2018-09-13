package com.zxin.jdk.test.security;

import java.security.Provider;
import java.security.Security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityTest {

	private static Logger logger = LoggerFactory.getLogger(SecurityTest.class);
	
	public static void main(String[] args) {
		for(Provider p : Security.getProviders()){
			System.out.println(p+"\t\t\t"+p.getInfo());
		}
	}
}

