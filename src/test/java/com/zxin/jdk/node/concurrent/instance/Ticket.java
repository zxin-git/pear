package com.zxin.jdk.node.concurrent.instance;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Ticket{
	
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReadLock readLock = readWriteLock.readLock();
	private WriteLock writeLock = readWriteLock.writeLock(); 

	
	public void sell(){
		writeLock.lock();
		try {
			System.out.println("sell");
		} finally {
			writeLock.unlock();
		}
	}
	
	public void buy(){
		synchronized (this) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void read(){
		readLock.lock();
		try {
			System.out.println("read");
		} finally {
			readLock.unlock();
		}
		
	}
	
	public static void main(String[] args) {
		Ticket ticket = new Ticket();
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						ticket.sell();
						try {
							Thread.sleep(1000*5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						ticket.read();
						try {
							Thread.sleep(1000*5);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}	
					}
				}
			}).start();
			
		}
				
	}
}
