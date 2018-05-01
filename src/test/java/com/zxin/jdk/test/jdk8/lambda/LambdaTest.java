package com.zxin.jdk.test.jdk8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {

//	(params) -> expression
//	(params) -> statement
//	(params) -> { statements }
	
	public static void main(String[] args) {
//		Student student = Student.print(()->);
	}
	
	public static void createThread() {
		// Java 8之前：
		new Thread(new Runnable() {
		    @Override
		    public void run() {
		    System.out.println("Before Java8, too much code for too little to do");
		    }
		}).start();

		//Java 8方式：
		new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
	}
	
	public static void oldSort() {
		List<String> list = Arrays.asList("asd", "dweas", "aw", "trs");
		// Create a comparator
		Comparator<String> mySort = new Comparator<String>() {

			@Override
			public int compare(String srt1, String str2) {
				// TODO Auto-generated method stub
				return srt1.compareTo(str2);
			}
		};
	
		// Sorting...
		Collections.sort(list, mySort);
	}

	
	public static void LambdaSort() {
		List<String> list = Arrays.asList("asd", "dweas", "aw", "trs");
		// Sorting...
		Collections.sort(list, (str1, str2) -> str1.compareTo(str2));
	}
}
