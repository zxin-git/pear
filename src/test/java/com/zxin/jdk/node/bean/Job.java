package com.zxin.jdk.node.bean;

public class Job implements Cloneable{

	private int id;
	
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public Job clone() throws CloneNotSupportedException{
		return (Job) super.clone();
	}
	
}

