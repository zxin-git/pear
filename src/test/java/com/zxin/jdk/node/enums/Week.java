package com.zxin.jdk.node.enums;

public enum Week{
	
	
	SUNDAY;


	Week(){
		System.out.println("c");
	}

	public static void main(String[] args) {
		System.out.println(SUNDAY.name());
	}
	
}

