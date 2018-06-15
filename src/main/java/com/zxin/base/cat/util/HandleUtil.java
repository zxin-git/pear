package com.zxin.base.cat.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleUtil {

	private static Logger logger = LoggerFactory.getLogger(HandleUtil.class);
	
	public static ExecutorService exec = Executors.newFixedThreadPool(20);
	
//	ThreadPoolExecutor es = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
	
	
	
	
	
}

