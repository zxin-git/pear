package com.zxin.apache.common;

import org.apache.commons.lang.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateFormatUtil {

	private static final Logger logger = LoggerFactory.getLogger(DateFormatUtil.class);

	 public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	 
	 public static final FastDateFormat DATETIME = FastDateFormat.getInstance("yyyyMMddHHmmss");
}

