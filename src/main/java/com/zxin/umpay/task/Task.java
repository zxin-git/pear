package com.zxin.umpay.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.handler.CacheHandler;
import com.zxin.umpay.handler.Status;
import com.zxin.umpay.handler.StatusHandler;

public class Task implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(Task.class);

	private static final ExecutorService taskService = Executors.newFixedThreadPool(20);
	
//	private static final ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(40);
	
	protected CacheHandler cacheHandler;
	
	protected StatusHandler handler;
	
	public Task(CacheHandler cacheHandler, StatusHandler handler) {
		super();
		this.cacheHandler = cacheHandler;
		this.handler = handler;
	}
	
	public void start(){
		taskService.execute(this);
	}

	@Override
	public void run() {
		while(Status.SCANNING.before(cacheHandler.getStatus())){
			try {
				Thread.sleep(1*1000);
			} catch (InterruptedException e) {
				logger.debug("",e);
			}
		}
		handler.setFirstLine(cacheHandler.getFirstLine());
		process();
//		scheduledService.scheduleAtFixedRate( () -> {
//			logger.info("当前缓存池的数量:{}", cacheHandler.getCache().size());
//			if(StatusHandler.Status.stop.equals(cacheHandler.getStatus()) && 
//					cacheHandler.getCache().size() <= 0){
//				throw new RuntimeException("中断打印");
//			}
//		}, 0, 1, TimeUnit.SECONDS);
	}

	public void process(){
		try {
			while (Status.SCANNED.before(cacheHandler.getStatus()) || 
					cacheHandler.getCache().size() > 0) {
				String line = cacheHandler.getCache().poll(10, TimeUnit.SECONDS);
				try {
					exec(line);
//					handler.handler(line);
				} catch (Exception e) {
					logger.warn("",e);
				}
			}
		} catch (InterruptedException e) {
			logger.debug("",e);
		}
	}
	
	public void exec(String line) throws Exception{
		handler.handler(line);
	}


	
}

