package com.zxin.app.home;

import java.time.DayOfWeek;
import java.time.Month;

public class DateUtils {

	public static final int MONTH_PER_YEAR = 12;
	
	public static final int DAYS_PER_WEEK = 7;
	
	/**
	 * 闰年
	 */
	public static final int DAYS_PER_YEAR_LEAP = 366;
	
	/**
	 * 平年
	 */
	public static final int DAYS_PER_YEAR_COMMON = 365;
	
	/**
	 * 大月,阳月
	 */
	public static final int DAYS_PER_MONTH_SOLAR = 31;
	
	/**
	 * 小月,阴月,朔望月
	 */
	public static final int DAYS_PER_MONTH_LUNAR = 30;
	
	public static final int WEEKS_PER_YEAR = 52;
	
	public static void main(String[] args) {
		System.out.println(Month.of(5).length(false));
		System.out.println(Month.FEBRUARY.length(false));
		System.out.println(DayOfWeek.SUNDAY.getValue());
	}
	
	
}

