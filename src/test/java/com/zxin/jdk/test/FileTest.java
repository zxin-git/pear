package com.zxin.jdk.test;

import java.io.File;

import org.junit.Test;

public class FileTest {
	@Test
	public void test(){
		File file = new File("a");
		System.out.println(file);
	}
}
