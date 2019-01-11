package com.zxin.umpay.bean;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LimitBean{
	
	private static final Logger logger = LoggerFactory.getLogger(LimitBean.class);
	
	private final int maximum;
	
	private final int per;
	
	private final TimeUnit timeUnit;
	
	private Semaphore limit;
	
	private final AtomicInteger count = new AtomicInteger();
	
	private final TimerTask refreshLimitTask;

	private Timer timer;
	
	{
		refreshLimitTask = new TimerTask() {
			@Override
			public void run() {
//				logger.info("时间 ：{} ，当前tps：[{}]", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss SSS").format(System.currentTimeMillis()), count.get());
				logger.info("时间 ：{} ，当前流量为每[{}][{}]:[{}]", FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss SSS").format(System.currentTimeMillis()), per, timeUnit ,count.get());
				//定时刷新总许可
//				limit.release(maximum - limit.availablePermits());
				limit.release(count.getAndSet(0));
			}
		};
	}
	public LimitBean(int maximum, int per, TimeUnit timeUnit) {
		super();
		this.maximum = maximum;
		this.per = per;
		this.timeUnit = timeUnit;
		this.limit = new Semaphore(maximum);
	}
	
	
	
	public void start(){
		timer = new Timer();
		timer.scheduleAtFixedRate(refreshLimitTask, new Date(), timeUnit.toMillis(per));
	}
	
	public void stop(){
		refreshLimitTask.cancel();
		timer.cancel();
	}

	
	
	public int getMaximum() {
		return maximum;
	}

	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	public Semaphore getLimit() {
		return limit;
	}

	public void setLimit(Semaphore limit) {
		this.limit = limit;
	}

	public AtomicInteger count() {
		return count;
	}

	public int getPer() {
		return per;
	}

	public AtomicInteger getCount() {
		return count;
	}

	
	
}

