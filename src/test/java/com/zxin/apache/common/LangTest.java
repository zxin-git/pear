package com.zxin.apache.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LangTest {

	private static Logger logger = LoggerFactory.getLogger(LangTest.class);
	
	public static void main(String[] args) {
		fastDateFormatTest();
		serializationUtilsTest();
		arrayUtilsTest();
		numberUtilsTest();
	}
	
	public static void fastDateFormatTest(){
		DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
		System.out.println(DateFormatUtils.ISO_DATETIME_FORMAT.format(new Date()));
		System.out.println(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
		System.out.println(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance()));
	}
	
	public static void serializationUtilsTest(){
//		ArrayList<String> list = new ArrayList<>();	//3
//		list.add("ssss");
//		List<String> copyList = SerializationUtils.clone(list);
		String str = "aaa";
		String str2 = SerializationUtils.clone(str);
		System.out.println(str==str2);
		System.out.println(ObjectUtils.identityToString(str)+ObjectUtils.identityToString(str));
		System.out.println(ObjectUtils.hashCode(str)+" "+ObjectUtils.hashCode(str));
	}
	
	public static void arrayUtilsTest(){
		String[] strs = {"1","2","#"};
		System.out.println(strs);
		System.out.println(ArrayUtils.clone(strs));
		Arrays.asList("Tom", "Jerry", "Mike");
	}
	
	public static void numberUtilsTest(){
		int i = NumberUtils.toInt("111111111");
		System.out.println(i);
	}
	
	public static void stringUtilsTest(){
		StringUtils.isNumeric("1");
	}
	
	public static void dateUtilsTest(){
		long l = DateUtils.MILLIS_PER_DAY;
	}
	
	
}

