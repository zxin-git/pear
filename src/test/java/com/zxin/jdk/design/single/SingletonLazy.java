package com.zxin.jdk.design.single;

public class SingletonLazy {

	private volatile static SingletonLazy instance;		//volatile防止指令优化
	
	private SingletonLazy(){
		
	}
	
	public static SingletonLazy getInstance() {
		if (instance==null) {
			synchronized (SingletonLazy.class) {	//静态属性与方法      锁类
				if (instance==null) {	//双重校验   防止有别的线程进入上层if块内，等待syn块
					instance = new SingletonLazy();
				}
			}
		}
		
		return instance;
	}
	
}
