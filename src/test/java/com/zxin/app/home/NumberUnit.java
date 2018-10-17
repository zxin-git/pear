package com.zxin.app.home;

public enum NumberUnit{
	W(10000),K(1000);
	
	
	private final int value;

	private NumberUnit(int value) {
		this.value = value;
	}

	public int value() {
		return value;
	}
	
}