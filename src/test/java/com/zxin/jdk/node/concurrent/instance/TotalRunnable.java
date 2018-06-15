package com.zxin.jdk.node.concurrent.instance;

public class TotalRunnable implements Runnable{
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	public TotalRunnable(Bank bank,int from,double max) {
		this.bank = bank;
		fromAccount = from;
		maxAmount = max; 
	}
	
	@Override
	public void run() {
		try {
			while(true){
				bank.getTotalBanlance();
				Thread.sleep((int)(DELAY*Math.random()));
			}
		} catch (InterruptedException e) {
			
		}
	}
	
}
