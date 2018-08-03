package com.zxin.mine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDateFormatTest {
	
	private static final Logger log = LoggerFactory.getLogger(SimpleDateFormatTest.class);

	private static final ExecutorService EX_SERVICE = Executors.newFixedThreadPool(5);
	
//	private static final FastDateFormat sdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	private static ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>(){
//		protected SimpleDateFormat initialValue() {
//			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		};
//	};
	
	private static boolean running = true;
	
	public static void main(String[] args) {
		EX_SERVICE.submit(new Runnable() {
			@Override
			public void run() {
				while(running){
					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.HOUR, 5);
					calendar.set(Calendar.MINUTE, 55);
					calendar.set(Calendar.SECOND, 55);
//					sdf.get().format(calendar.getTime());
					sdf.format(calendar.getTime());
//					log.info("running");
				}
				
			}
		});
		for (int i = 0; i < 50; i++) {
			EX_SERVICE.submit(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					log.info(DateFormatUtils.ISO_DATETIME_FORMAT.format(System.currentTimeMillis())+" : "+sdf.format(System.currentTimeMillis()));
				}
			});
		}
		EX_SERVICE.submit(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(5*1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				running = false;
			}
		});
		
	}
}
