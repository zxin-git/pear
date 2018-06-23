package com.zxin.jdk.node.concurrent;

import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.nutz.castor.Castors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleThreadPoolTest {

	private static Logger logger = LoggerFactory.getLogger(ScheduleThreadPoolTest.class);
	
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor  scheduled = new ScheduledThreadPoolExecutor(5);
//	    scheduled.scheduleWithFixedDelay(new Runnable() {	结束时间间隔
    	scheduled.scheduleAtFixedRate(new Runnable() {	 //开始时间间隔,执行超过间隔,默认执行完继续执行
	        @Override
	        public void run() {
	            logger.debug("current time : {}", Castors.me().castTo(Calendar.getInstance(), String.class));
	            try {
					Thread.sleep(7000);
				} catch (InterruptedException e) {
					logger.debug("",e);
				}
	        }
	    }, 0, 5, TimeUnit.SECONDS);//0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位
	}
}

