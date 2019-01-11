package com.zxin.umpay.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.handler.CacheHandler;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.Status;
import com.zxin.umpay.handler.StatusHandler;

public class ConcurrentTask extends Task{

	private static final Logger logger = LoggerFactory.getLogger(ConcurrentTask.class);
	
	protected static final ExecutorService executorService = Executors.newFixedThreadPool(50);
	
	protected final int threadNum;
	
	protected final AtomicInteger nowThreadNum = new AtomicInteger(0);

	public ConcurrentTask(CacheHandler cacheHandler, int threadNum, StatusHandler handler) {
		super(cacheHandler, handler);
		this.threadNum = threadNum;
	}
	
//	@Override
//	public void run() {
//		
//	}
	
	@Override
	public void process(){
		logger.info("启动并发查询任务, 线程数：[{}]", threadNum);
		for (int i = 0; i < threadNum; i++) {
			executorService.submit( ()->{ 
				super.process(); 
				if (nowThreadNum.decrementAndGet() <= 0) {
					cacheHandler.setStatus(Status.STOP);
				}
			});
			nowThreadNum.incrementAndGet();
		}
	}
	
	
	
}

