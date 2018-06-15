package com.zxin.base.cat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTest {
	
	private static Logger logger = LoggerFactory.getLogger(MainTest.class);
	
	public static void main(String[] args) {
//		DBPools.dao().create(UserInfo.class, true);
		for(int i=0;i<1000;i++){
//			HandleUtil.exec.submit(new HandleTask());
			ThreadPools.innerPool().submit(new HandleTask());
			System.out.println("----线程数---"+ThreadPools.innerPool().getPoolSize());
			System.out.println("----队列数---"+ThreadPools.innerPool().getQueue().size());
			System.out.println("-------------------------已经开启"+i+"个线程-------------------");
		}
	}
	
}
