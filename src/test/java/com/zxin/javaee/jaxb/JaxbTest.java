package com.zxin.javaee.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JaxbTest {

	private static Logger logger = LoggerFactory.getLogger(JaxbTest.class);
	
	public static void main(String[] args) {
		annoTest();
	}
	
	public static void annoTest(){
		User user = new User();
    	user.setAge(1);
    	user.setRole("rolessss");
    	user.setBibi("bbbbbb");
    	Menu menu = new Menu();
    	menu.setId("s");
    	user.setMenu(menu);
    	String xml = Jaxb7Util.toXml(user);
    	System.out.println(xml);
	}
}

