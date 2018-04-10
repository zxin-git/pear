package com.zxin.mvc.auth.listener;

import org.apache.log4j.Logger;
import org.nutz.mvc.NutConfig;

import com.zxin.mvc.core.AbstractSetupListener;

public class AuthListener extends AbstractSetupListener {

	private static final Logger logger = Logger.getLogger(AbstractSetupListener.class);
	
	@Override
	public void init(NutConfig nc) {
		logger.info("AuthListener "+this.name);
	}

	@Override
	public void destroy(NutConfig nc) {
		
	}
	
}
