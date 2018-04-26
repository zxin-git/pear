package com.zxin.jdk.node.enums;

public enum City {
	BJ("beijing"),SH("shanghai");
	
	private String chineseName;
	
	private City(String chineseName){
		this.chineseName = chineseName;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	

}
