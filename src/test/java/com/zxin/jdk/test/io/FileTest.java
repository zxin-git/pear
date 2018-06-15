package com.zxin.jdk.test.io;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileTest {
	public static void main(String[] args) {
		fileTest();
		
	}
	
	public static void fileTest(){
		try {
			File file = new File("E:/zxin/developer/tmp/file");
			Collection<File> files = FileUtils.listFiles(file, null, null);
			System.out.println(file.getName());	//名称
			System.out.println(file.getParent());	//父全路径
			System.out.println(file.getPath());
			System.out.println(file.getParentFile());
			System.out.println(file.listFiles());	//数组
			System.out.println(file.getFreeSpace());
			System.out.println(file.getCanonicalPath());//抽象路径
			System.out.println(file.getAbsolutePath());//抽象路径
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
