package com.zxin.jdk.node.enums;


public enum Ball {

	basketball(Color.RED,"s");
	
	
	Color color;
	
	String s;
	
	

	Ball(Color color, String s) {
		this.color = color;
		this.s = s;
	}

}

