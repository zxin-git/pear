package com.zxin.jdk.node.extend;

import org.nutz.json.Json;

public class ExtendTest {

	public static void main(String[] args) {
		Person person = new Person();
		person.setName("ss");
		System.out.println(Json.toJson(person));
	}
	
}
