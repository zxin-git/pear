package com.zxin.jdk.node.concurrent;

import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue<>();
		blockingQueue.add("sss");
		blockingQueue.add("bbb");
		blockingQueue.offer("bbb");
		System.out.println(blockingQueue.element());
		System.out.println(blockingQueue.peek());
		System.out.println(blockingQueue.remove());
		System.out.println(blockingQueue.size());
		System.out.println(blockingQueue.poll());
		
	}
}
