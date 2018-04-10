package com.zxin.mvc.core.util.duplication;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.JDomDriver;

/**
 * XML流复制工具
 */
public class XStreamDuplicateUtil implements IDuplicateUtil
{
	private static final XStreamDuplicateUtil util = new XStreamDuplicateUtil();
	private static final XStream xstream = new XStream(new JDomDriver());
	
	private XStreamDuplicateUtil() {}
	
	public static final IDuplicateUtil getInstance()
	{
		return util;
	}

	@SuppressWarnings("unchecked")
	public <T> T duplicate(T o)
	{
		String str = xstream.toXML(o);
		T newObj = (T)xstream.fromXML(str);
		return newObj;
	}

}

