package com.zxin.jdk.test.other;


public class ParamTest {
	
	
	
	public static void main(String[] args) {
		a();
	}
	
	public static void a(){
		String a = "a";
//		StringBuffer a = new StringBuffer("a");
		int[] is = {1,2,3,4,5};
		System.out.println(a+"  "+is[0]);
		b(a,is);
		System.out.println(a+"  "+is[0]);
	}
	
	public static void b(StringBuffer a,int[] is){
		a.append("n");
		is[0]=5;
	}
	
	public static void b(String a,int[] is){
		a = a+"b";
		is[0]=5;
	}
	
	public static void bs(StringBuffer a,int[] is){
		a = new StringBuffer("a");
		is[0]=5;
	}
	
}
