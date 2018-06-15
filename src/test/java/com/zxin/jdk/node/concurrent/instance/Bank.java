package com.zxin.jdk.node.concurrent.instance;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double[] accounts;
	private Lock bankLock = new ReentrantLock();
	private Lock bankLock2 = new ReentrantLock();
	private Object lock = new Object();
	
	public Bank(int n,double initialBalance) {
		accounts= new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
	}
	
	
	public void transfer(int from,int to,double amount) {
		bankLock.lock();
		try {
			if (accounts[from]>amount){
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf("%10.2f from %d to %d",amount,from,to);
			accounts[to] += amount;
			System.out.printf("Total Balance: %10.2f%n",getTotalBanlance());
			}
		} finally {
			bankLock.unlock();
		}
		
	}
	
	public double getTotalBanlance(){
//		bankLock2.lock();
		synchronized (lock) {
			
			try {
				double sum=0;
				for (double d : accounts) {
					sum+=d;
				}
				return sum;
			} finally {
//			bankLock2.unlock();
			}
		}
	}
	
	public int size() {
		return accounts.length;
	}
	
}
