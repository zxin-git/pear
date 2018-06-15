package com.zxin.jdk.node.concurrent.instance;

public class UnSynchBankTest {

	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i ;
//		TransferRunnable t = new TransferRunnable(bank, 1, INITIAL_BALANCE);
//		Thread th = new Thread(t);
//		th.start();
		for (i = 0; i < NACCOUNTS; i++) {
			TotalRunnable r = new TotalRunnable(bank, i, INITIAL_BALANCE);
			Thread thread = new Thread(r);
			thread.start();
		}
	}
	
}
