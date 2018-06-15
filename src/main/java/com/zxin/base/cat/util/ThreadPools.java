package com.zxin.base.cat.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPools {

	private static ThreadPoolExecutor ditributePool = null;
	private static volatile ThreadPoolExecutor minePool = null;
	private static ThreadPoolExecutor innerPool = null;

	public synchronized static ThreadPoolExecutor ditributePool() {
		if (ditributePool == null) {
			ditributePool = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
					new ArrayBlockingQueue<Runnable>(2),
					new ThreadPoolExecutor.CallerRunsPolicy());
		}
		return ditributePool;
	}
	
	public static ThreadPoolExecutor minePool() {
		if (minePool == null) {
			synchronized(ThreadPools.class){
				if(minePool == null){
					minePool = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS,
							new ArrayBlockingQueue<Runnable>(2),
							new ThreadPoolExecutor.CallerRunsPolicy());
				}
			}
			
		}
		return minePool;
	}

	
	public static ThreadPoolExecutor innerPool() {
		if(innerPool==null){
			innerPool = InnerPool.instance;
		}
		return innerPool;
	}
	
	
	private static class InnerPool{
		private static final ThreadPoolExecutor instance = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(8),		//先填队列 ，队列满了，开线程，开到max，阻塞队列
//				new LinkedBlockingQueue<Runnable>(),		//max失效		nowThreadNum=core;
//				new SynchronousQueue<Runnable>(),			//core与max作用，多了抛异常！
				new ThreadPoolExecutor.CallerRunsPolicy());

	}
}
