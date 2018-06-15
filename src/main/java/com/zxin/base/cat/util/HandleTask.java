package com.zxin.base.cat.util;

import java.util.List;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.base.util.DBUtil;
import com.zxin.base.util.RandomUtil;

public class HandleTask implements Runnable{
	
	private static Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);
	
	@Override
	public void run() {
//		List<UserInfo> list = DBPools.dao().query(UserInfo.class, null);
		DBPools.dao().insert(RandomUtil.nextUser());
		logger.debug("执行线程为："+Thread.currentThread().getName());
		try {
			Thread.sleep(1*1000);
		} catch (InterruptedException e) {
			logger.debug("",e);
		}
	}

}
