package com.zxin.serial;

import com.thoughtworks.xstream.XStream;
import com.zxin.mvc.core.util.SerializeUtil;

public class XStreamTest {
	public static void main(String[] args) {
	    User user = new User("lanweihong", "lwhhhp@gmail.com");
	    
//	    //创建解析XML对象
//	    XStream xStream = new XStream();
//	    //设置别名, 默认会输出全路径
////	    xStream.alias("User", User.class);
//	    //转为xml
//	    String xml = xStream.toXML(user);
//	    System.out.println(xml);
	    String xml = SerializeUtil.getInstance().toJson(user);
	    System.out.println(xml);
	}
}
