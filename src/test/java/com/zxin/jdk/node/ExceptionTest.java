package com.zxin.jdk.node;

public class ExceptionTest {

	public static void main(String[] args) {
		System.out.println("1");
		zz();
		System.out.println("2");
		System.out.println("1");
	}
	
	public static void zz(){
		try{
			throw new Exception("zzzzz");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
