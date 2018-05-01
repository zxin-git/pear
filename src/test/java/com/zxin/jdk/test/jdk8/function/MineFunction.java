package com.zxin.jdk.test.jdk8.function;

public class MineFunction {
	
	public static String myMethod(String arg){
		return "aaaa";
	}
	
	public static void main(String[] args) {
		//static静态方法和构造函数的引用
		FunFactory<String, String> mainFun = MineFunction::myMethod;//见MineFun
		Constructor<MineFunction> constructor = MineFunction::new;
		MineFunction mineFunction = constructor.run();
		System.out.println(mainFun.run("Creating"));
		System.out.println(mineFunction);
		System.out.println(constructor.run());

	}
	
}
