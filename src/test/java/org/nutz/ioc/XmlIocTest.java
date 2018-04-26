package org.nutz.ioc;

import java.util.HashMap;
import java.util.Map;

import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.xml.XmlIocLoader;
import org.nutz.json.Json;

public class XmlIocTest {
	public static void main(String[] args) {
		Ioc ioc = new NutIoc(new XmlIocLoader("/ioc.xml"));
		Map<String, Object> map = ioc.get(HashMap.class,"fillDict");
		System.out.println(Json.toJson(map));
		
	}
}
