package com.zxin.javaee.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxbTest {

	private static Logger logger = LoggerFactory.getLogger(JaxbTest.class);
	
	public static void main(String[] args) {
		User user = new User();
    	user.setAge(1);
    	user.setRole("rolessss");
    	user.setBibi("bbbbbb");
    	StringWriter str = new StringWriter();
		JAXB.marshal(user,str);
		System.out.println(str.toString());
		
		StringReader sr = new StringReader(str.toString());
		User user2 = JAXB.unmarshal(sr, User.class);
		System.out.println(user2.equals(user));
	}
}

