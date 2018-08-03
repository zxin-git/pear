package com.zxin.javaee.jaxb;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MineDate {

	private Month month;
	
	private Week week;
	
	
	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	class Year{}
	
	interface None{}
	
	enum Month{JANUARY,FEBRUARY,MARCH,APRITL,MAY} 
	
	enum Week{MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY}
	
}

