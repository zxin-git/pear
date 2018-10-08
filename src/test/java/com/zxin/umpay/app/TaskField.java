package com.zxin.umpay.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskField {

	private static final Logger logger = LoggerFactory.getLogger(TaskField.class);

	private int tps;
	
	private int threadNum;

	public int getTps() {
		return tps;
	}

	public void setTps(int tps) {
		this.tps = tps;
	}

	public int getThreadNum() {
		return threadNum;
	}

	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}
	
	
	
}

