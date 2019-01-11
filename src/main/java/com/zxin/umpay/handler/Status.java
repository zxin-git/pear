package com.zxin.umpay.handler;
public enum Status{
	START, SCANNING, SCANNED, STOP;
	
	public boolean before(Status status){
		return status.ordinal() < this.ordinal();
	}
	
	public boolean after(Status status){
		return status.ordinal() > this.ordinal();
	}
	
};