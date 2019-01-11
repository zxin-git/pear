package com.zxin.umpay.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ChainHandler implements IHandler{

	private static final Logger logger = LoggerFactory.getLogger(ChainHandler.class);

	protected ChainHandler next;
	
//	public LinkedHandler(IHandler... handlerArray) {
//		this.handlers = new LinkedList<>(Arrays.asList(handlerArray));
//	}
	
	public void chain(String data) throws Exception {
		this.handler(data);
		next.chain(data);
	}

}

