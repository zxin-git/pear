package com.zxin.umpay.task;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.bean.LimitBean;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.impl.StoreHandler;
import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.store.FileStore;

@Deprecated
public class LimitTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(LimitTask.class);

	private static final ExecutorService taskService = Executors.newCachedThreadPool();
	
	private static final ExecutorService executorService = Executors.newFixedThreadPool(50);
	
	private static final ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(20);
	
	private LimitBean limitBean;
	
	private final int threadNum;
	
	private final AtomicInteger nowThreadNum = new AtomicInteger(0);
	
	private final BlockingQueue<String> cache = new LinkedBlockingQueue<>();
	
	private final IHandler handler;
	
	private boolean writing;
	
	public LimitTask(IHandler handler, int maxTps, int threadNum) {
		super();
		this.handler = handler;
		this.threadNum = threadNum;
		this.limitBean = new LimitBean(maxTps, 1, TimeUnit.SECONDS);
		taskService.execute(this);
	}


	@Override
	public void run() {
		logger.info("启动查询任务, tps限制：[{}], 线程数：[{}]", limitBean.getMaximum(), threadNum);
		limitBean.start();
		for (int i = 0; i < threadNum; i++) {
			executorService.submit( ()->{
				nowThreadNum.incrementAndGet();
				while(writing || cache.size()>0){
					try {
						String line = cache.poll(10, TimeUnit.SECONDS);
						if(line==null)continue;
						limitBean.getLimit().acquire();
						limitBean.count().incrementAndGet();
						handler.handler(line);
					} catch (InterruptedException e) {
						logger.info("缓存池已经消费完毕,准备结束此线程...");
					} catch (Exception e) {
						logger.warn("",e);
					} finally{
						
					}
				}
				nowThreadNum.decrementAndGet();
			} );
		}
		
		int repeatCount = 0;
		while(repeatCount < 5){
			try {
				Thread.sleep(1000);
				logger.info("当前线程数:{}, 当前缓存池的数量:{}", nowThreadNum.get(), cache.size());
			} catch (InterruptedException e) {
				logger.warn("",e);
			}
			if( !writing && nowThreadNum.get() == 0){
				repeatCount++;
			}
		}
		
		limitBean.stop();
	}
	
	
	public BlockingQueue<String> getCache() {
		return cache;
	}
	
	public static BlockingQueue<String> newCache(AppEnum app, ReqBodyBuilder reqBodyBuilder, int maxTps, int threadNum){
		return new LimitTask(new StoreHandler(reqBodyBuilder, app, new FileStore(new File("E:/batch.txt"))), maxTps, threadNum).cache;
//		return new LimitTask(new HttpHandler(reqBodyBuilder, app), maxTps, threadNum).cache;
	}
	
	
}

