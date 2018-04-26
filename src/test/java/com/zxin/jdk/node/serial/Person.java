package com.zxin.jdk.node.serial;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -6686118583225411708L;

	private int id;
	
	private String name;
	
	private long phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	

	
}
