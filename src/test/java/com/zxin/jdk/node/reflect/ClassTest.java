package com.zxin.jdk.node.reflect;

import java.util.List;

public final class ClassTest {
	public static void main(String[] args) {
		try {
		Class class1 = Class.forName(ClassTest.class.getName());
		class1.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static <T> void  getCls(List<T> list){

	}
	
}
