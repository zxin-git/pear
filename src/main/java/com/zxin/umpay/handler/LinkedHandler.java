package com.zxin.umpay.handler;

import java.util.Arrays;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedHandler implements IHandler{

	private static final Logger logger = LoggerFactory.getLogger(LinkedHandler.class);

	private LinkedList<IHandler> handlers = null;
	
	public LinkedHandler(IHandler... handlerArray) {
		this.handlers = new LinkedList<>(Arrays.asList(handlerArray));
	}

	@Override
	public void handler(String data) throws Exception {
		for (int i = 0; i < handlers.size(); i++) {
			handlers.get(i).handler(data);
		}
	}
	
	
}

