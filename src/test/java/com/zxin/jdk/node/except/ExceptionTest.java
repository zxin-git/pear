package com.zxin.jdk.node.except;

public class ExceptionTest {
	static int i = 0;
	public static void main1(String[] args) {
		name();
		System.out.println(i);
	}
	
	public static void name() {
		try {
			i=1 ;
			return ;	//finally 一定会执行，先执行finally 后return；
		} catch (Exception e) {
			
		}finally {
			i=2;
		}
	}
	
	public static void main(String[] args) {
		try{

			int a = 100/0;
		}catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
			

		}
	}
}
