package com.zxin.umpay.batch;

public interface ICache {

	void put(String data) throws Exception;
	
	String take(String data);
}

