package com.zxin.jdk.test.jdk7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FunTest {

	public static void main(String[] args) {
		
		// 二进制字面量
        int x = 0b100101;
        System.out.println(x);
 
        // 数字字面量可以出现下划线
        int y = 100_1000;
        System.out.println(y);
 
        // switch语句可以用字符串
 
        // 泛型简化
        ArrayList<String> array = new ArrayList<>();
 
        // 异常的多个catch合并
 
        // try..with...resource语句
        method1();// 旧版
        method2();// 改进版
	}
	
	private static void method1() {// 旧版
        try {
            FileReader fr = new FileReader("E:\\zikao\\file\\cs.txt");
            FileWriter fw = new FileWriter("E:\\zikao\\file\\cs1.txt");
            int ch = 0;
            while ((fr.read()) != -1) {
                fw.write(ch);
            }
            fw.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private static void method2() {// 改进版
        try (FileReader fr = new FileReader("E:\\zikao\\file\\cs.txt");
                FileWriter fw = new FileWriter("E:\\zikao\\file\\cs1.txt");) {
            int ch = 0;
            while ((fr.read()) != -1) {
                fw.write(ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void mine() {
    	int a = 123_456;	//1.下划线分割 无意义,编译自动忽略
		System.out.println(a);
		
		int binary = 0b11;	//2.只增加二进制
		System.out.println(binary);
		int hex = 0xea;
		int octal = 074;
		
		String str = "s";	
		switch (str) {	//3.switch支持String
		case "a":break;
		case "s":break;
		default:break;
		}
	}
	
}
