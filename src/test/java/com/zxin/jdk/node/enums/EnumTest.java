package com.zxin.jdk.node.enums;

import com.zxin.jdk.design.single.Singleton;
import java.lang.Enum;

import org.nutz.json.Json;


public class EnumTest {
	
	
	public static void main(String[] args) {
		
//		City city = City.valueOf(City.class,"BJ");
//		City city1 = City.valueOf("BJ");
//		Color color = Color.valueOf("GREEN");
//		Color[] colors = Color.values();
//		System.out.println(Json.toJson(city1));
//		System.out.println(Json.toJson(colors));
		System.out.println(Color.BLANK.ordinal());//序号,0开始
	}
}
