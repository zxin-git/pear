package com.zxin.jdk.design.single;

public class SingletonInner {

	private SingletonInner(){
		
	}
	
	public static SingletonInner getInstance(){
		return SingletonHolder.singletonInner;
	}
	
	//静态内部类	推荐
	private static class SingletonHolder{
		public static final SingletonInner singletonInner = new SingletonInner();
	}
	
}
