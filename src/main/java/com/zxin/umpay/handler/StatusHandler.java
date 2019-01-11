package com.zxin.umpay.handler;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StatusHandler implements IHandler {

	private static final Logger logger = LoggerFactory.getLogger(StatusHandler.class);
	
	protected Status status = Status.START;
	
	protected final AtomicInteger count = new AtomicInteger(0);
	
	protected String firstLine;
	
	@Override
	public void handler(String line) throws Exception{
		count.incrementAndGet();
		process(line);
	}

	public abstract void process(String line) throws Exception;
		
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public AtomicInteger getCount() {
		return count;
	}

	public String getFirstLine() {
		return firstLine;
	}

	public void setFirstLine(String firstLine) {
		this.firstLine = firstLine;
	}
	
}

