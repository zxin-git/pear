package com.zxin.base.cat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.base.util.DBUtil;

public class HandleServiceImpl implements HandleService {

	private static Logger logger = LoggerFactory.getLogger(HandleServiceImpl.class);

	@Override
	public void handle() {
		DBPools.dao().query(UserInfo.class, null);
	}
}

