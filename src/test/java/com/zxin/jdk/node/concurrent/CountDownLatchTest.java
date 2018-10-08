package com.zxin.jdk.node.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CountDownLatchTest {

	private static Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);
	
	public static void main(String[] args) {
		semaphoreTest();
	}
	
//	CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
//	而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
//	另外，CountDownLatch是不能够重用的，而CyclicBarrier是可以重用的。
//	Semaphore其实和锁有点类似，它一般用于控制对某组资源的访问权限。
	
	public static void semaphoreTest(){
		ExecutorService eService =  Executors.newFixedThreadPool(10);
		int N = 8;            //工人数
        final Semaphore semaphore = new Semaphore(5); //机器数目  许可数目
        for (int i = 0; i < N; i++) {
        	final int num = i;
			eService.submit(new Runnable() {
				@Override
				public void run() {
					try {
		                semaphore.acquire();	//acquire() 获取一个许可，如果没有就等待，
		                System.out.println("工人"+num+"占用一个机器在生产...");
		                Thread.sleep(2000);
		                System.out.println("工人"+num+"释放出机器");
		                semaphore.release();    //而 release() 释放一个许可。  
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
				}
			});
		}
	}
	
	
	public static void cyclicBarrierTest(){
		ExecutorService eService =  Executors.newFixedThreadPool(10);
		int N = 10;
        final CyclicBarrier barrier  = new CyclicBarrier(N);
//        final CyclicBarrier barrier  = new CyclicBarrier(N,new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("当前线程"+Thread.currentThread().getName());   
//            }
//        });
        
        for (int i = 0; i < 10; i++) {
			eService.submit(new Runnable() {
		 
		        @Override
		        public void run() {
		            System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
		            try {
		                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
		                System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");
		                barrier.await();
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }catch(BrokenBarrierException e){
		                e.printStackTrace();
		            }
		            System.out.println("所有线程写入完毕，继续处理其他任务...");
		        }
			});
        }
	}
	
	public static void latchTest(){ //主线程阻塞等待
		final CountDownLatch latch = new CountDownLatch(5);	//计数器
		ExecutorService eService =  Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			eService.submit(new Runnable() {
				@Override
				public void run() {
					latch.countDown();//减一
					logger.debug(latch.getCount()+"");
				}
			});
		}
		try {
			latch.await();	//减至0时,阻塞完毕。
			logger.info("game over!!!");
		} catch (InterruptedException e) {
			logger.debug("",e);
		}
	}
}

