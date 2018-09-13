package com.zxin.jdk.test.lang;

import java.io.UnsupportedEncodingException;

import org.junit.Test;

public class StringTest {
	public static void hashCodeTest(){
		String str = "gdejicbegh";
		String str2 = "hgebcijedg";
		System.out.println(str.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str==str2);
	}
	
	public static void equalsTest(){
		String a = "abc";
		String b = "abc";
		System.out.println(a.equals(b));
		System.out.println(a==b);
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());
		
		String c = new String("abc");
		System.out.println(a.equals(c));	//equals相等,hashcode必相等;反之不一定！！！
		System.out.println(c.hashCode());
		System.out.println(a==c);
	}
	public static void main(String[] args) {
//		hashCodeTest();
		equalsTest();
	}
	
	@Test
	public void strTest(){
		System.out.println(("cpu利用率").equals("cpu利用率"));
	}
	
	
	@Test
	public void testCode(){
		
		try {
			String s = new String("aa撒旦法".getBytes(),"UTF-8");
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
}
