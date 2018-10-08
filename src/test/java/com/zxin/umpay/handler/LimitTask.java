package com.zxin.umpay.handler;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.http.ReqBodyBuilder;

public class LimitTask implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(LimitTask.class);

	private static final ExecutorService taskService = Executors.newCachedThreadPool();
	
	private static final ExecutorService executorService = Executors.newFixedThreadPool(20);
	
	private final ReqBodyBuilder reqBodyBuilder;
	
	private final AppEnum app;
	
	private final int maxTps;
	
	private LimitBean limitBean;
	
	private final TimeUnit timeUnit = TimeUnit.SECONDS;
	
	private final int threadNum;
	
	private final AtomicInteger nowThreadNum = new AtomicInteger(0);
	
//	private final Semaphore tpsLimit;
	
	private final BlockingQueue<String> cache = new LinkedBlockingQueue<>();
	
	private boolean writing;
	
	private IPare pare;
	
	public LimitTask(AppEnum app, ReqBodyBuilder reqBodyBuilder, int maxTps, int threadNum) {
		super();
		this.app = app;
		this.reqBodyBuilder = reqBodyBuilder;
		this.maxTps = maxTps;
		this.threadNum = threadNum;
//		this.tpsLimit = new Semaphore(maxTps);
		this.limitBean = new LimitBean(maxTps, 1, TimeUnit.SECONDS);
		taskService.execute(this);
	}


	@Override
	public void run() {
		logger.info("启动查询任务,路径：[{}], tps限制：[{}], 线程数：[{}]", app.url(), maxTps, threadNum);
		limitBean.start();
		for (int i = 0; i < threadNum; i++) {
			executorService.submit( ()->{
				nowThreadNum.incrementAndGet();
				while(writing || cache.size()>0){
					try {
						String data = cache.poll(30, TimeUnit.SECONDS);
						String reqXml = new ReqBodyBuilder(reqBodyBuilder).mobileid(data).sign().buildXml();
						limitBean.getLimit().acquire();
						limitBean.count().incrementAndGet();
//						logger.info("当前启动的线程数：[{}], 当前tps：[{}]", nowThreadNum.get(), limitBean.count().incrementAndGet());
						Thread.sleep(10*ThreadLocalRandom.current().nextInt(5));
//						String response =  HttpClientUtil.sendByPost(app.url(), reqXml);
//						System.err.println(pare.pare(response));
//						logger.info(response);
					} catch (InterruptedException e) {
						logger.info("缓存池已经消费完毕,准备结束此线程...");
					} catch (Exception e) {
						logger.warn("",e);
					} finally{
//						limitBean.getLimit().release();
					}
				}
				nowThreadNum.decrementAndGet();
			} );
		}
		
		while(writing || nowThreadNum.get() > 0){
			try {
				logger.info("当前缓存池的数量{}",cache.size());
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				logger.warn("",e);
			}
		}
	}
	
	
	public BlockingQueue<String> getCache() {
		return cache;
	}
	
	public static BlockingQueue<String> getCache(AppEnum app, ReqBodyBuilder reqBodyBuilder, int maxTps, int threadNum){
		return new LimitTask(app, reqBodyBuilder, maxTps, threadNum).cache;
	}
	
}

