package com.zxin.jdk.design.single;

import java.util.Random;

public enum SingletonEnum {		//Enum
    INSTANCE;	//实例对象
	
    private SingletonEnum() {	//默认私有
    	
	}
    
    public int i = new Random().nextInt();
	
}
