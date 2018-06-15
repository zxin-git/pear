package com.zxin.jdk.node.concurrent.instance;

public class TicketRunnable implements Runnable {
	
	@Override
	public void run() {
		new Ticket().read();
	}	
	

}
