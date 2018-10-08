package com.zxin.javaee.jaxb;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Jaxb7Util {

	private static Logger logger = LoggerFactory.getLogger(Jaxb7Util.class);
	

	public static <T> T toBean(String xml, Class<T> type){
		return JAXB.unmarshal(new StringReader(xml), type);
	}
	
	public static <T> String toXml(T t){
		StringWriter xml = new StringWriter();
		JAXB.marshal(t, xml);
		return xml.toString();
	}
	
	public static void main(String[] args) {
		User user = new User();
    	user.setAge(1);
    	user.setRole("rolessss");
    	user.setBibi("bbbbbb");
    	String xml = Jaxb7Util.toXml(user);
    	System.out.println(xml);
		
		User user2 = Jaxb7Util.toBean(xml, User.class);
		System.out.println(user2.equals(user));
		
	}
	
}

