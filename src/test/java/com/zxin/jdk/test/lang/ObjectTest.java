package com.zxin.jdk.test.lang;

public class ObjectTest {
	public static void main(String[] args) {
		bitAirth();
	}
	
	public static void strHashCodeTest(){
		Object obj = "1";
		System.out.println(obj.hashCode());
		String str = "1";
		String str2 = "1";
		System.out.println(str.hashCode());
		System.out.println(str2.hashCode());
		System.out.println(str==str2);
	}
	
	public static void bitAirth(){
		long t1 = System.currentTimeMillis();
		for(int i=0,sum=0;i<10000*100000;i++){
			sum=sum+31*i;
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		for(int i=0,sum=0;i<10000*100000;i++){
			sum=sum+(i<<5)-i;
		}
		long t3 = System.currentTimeMillis();
		System.out.println(t3-t2);
	}
	
	public static void bitAirth2(){
		long t1 = System.currentTimeMillis();
		for(int i=0,sum=0;i<10000*100000;i++){
			if((i & ~((1<< 16)-1)) >0)sum+=i;
			
		}
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
		for(int i=0,sum=0;i<10000*100000;i++){
			if((i >= 0) && (i <= 65535))sum+=i;
		}
		long t3 = System.currentTimeMillis();
		System.out.println(t3-t2);
	}
}
