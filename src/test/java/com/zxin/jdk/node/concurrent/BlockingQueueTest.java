package com.zxin.jdk.node.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(1);
		blockingQueue.add("one");
		System.out.println(blockingQueue.size());

		
		blockingQueue.add("exception");
		System.out.println(blockingQueue.element());
		System.out.println(blockingQueue.remove());
		
		System.out.println(blockingQueue.offer("false"));
		System.out.println(blockingQueue.peek());	//看
		System.out.println(blockingQueue.poll());	//取
		
		try {
			blockingQueue.put("blocking");
			System.out.println(blockingQueue.take());
		} catch (InterruptedException e) {
			
		}
		
	}
}
