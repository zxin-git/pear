package com.zxin.mvc.core.util.nutz.ioc;

import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.xml.XmlIocLoader;

public class XTest {
	public static void main(String[] args) {
		NutIoc ioc = new NutIoc(new XmlIocLoader("nioc.xml"));
		XmlIocTest s =ioc.get(XmlIocTest.class,"$mx_XmlIocTest");
		System.out.println(s);
	}
}
