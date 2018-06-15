package com.zxin.base.util;

import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.base.cat.util.UserInfo;

public class RandomUtil {

	private static Logger logger = LoggerFactory.getLogger(RandomUtil.class);
	
	public static UserInfo nextUser(){
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE)+"");
		userInfo.setUserId(ThreadLocalRandom.current().nextLong(1000));
		return userInfo;
	}
}

