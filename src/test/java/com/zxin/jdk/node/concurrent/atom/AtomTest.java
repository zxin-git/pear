package com.zxin.jdk.node.concurrent.atom;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtomTest {

	private static Logger logger = LoggerFactory.getLogger(AtomTest.class);
	
	private static int i = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
//		ExecutorService exService = Executors.newFixedThreadPool(10);
//		
//
//        ProcessingRunnable pt = new ProcessingRunnable();
//		exService.submit(pt);
//		exService.submit(pt);
//		try {
//			while (!exService.awaitTermination(2, TimeUnit.SECONDS)){
//				
//			}
//			System.out.println("Processing count=" + pt.getCount());
//		} catch (InterruptedException e) {
//			logger.debug("",e);
//		}
		
		
		ProcessingRunnable pt = new ProcessingRunnable();
	        Thread t1 = new Thread(pt, "t1");
	        t1.start();
	        Thread t2 = new Thread(pt, "t2");
	        t2.start();
	        t1.join();
	        t2.join();
	        System.out.println("Processing count=" + pt.getCount());

		
//		exService.submit(new Runnable() {
//			@Override
//			public void run() {
//				while(true){
//					System.out.println(i);
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						logger.debug("",e);
//					}
//				}
//			}
//		});
		
	}
	
	 

}
class ProcessingRunnable implements Runnable {
//    private AtomicInteger count = new AtomicInteger();
    private int count;
 
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            processSomething(1);
            count++;
//            count.incrementAndGet();
        }
    }
 
 
    public int getCount() {
        return this.count;
//        return this.count.get();
    }
 
 
    private void processSomething(int i) {
        // processing some job
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

