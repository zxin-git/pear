package com.zxin.jdk.test.data;

import java.io.Serializable;

public class Data implements Serializable,Cloneable {
	
	private static final long serialVersionUID = -8043103498508333398L;
	
	private int id;
	private String name;
	private short num;
	private long phone;
	private boolean sex;	//man 1  woman 0
	private float f;
	private double money;
	private byte b;
	private char c;
	
	public Data clone() throws CloneNotSupportedException{
		return (Data)super.clone();
	};
	
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
	public short getNum() {
		return num;
	}
	public void setNum(short num) {
		this.num = num;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public boolean isSex() {
		return sex;
	}
	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public float getF() {
		return f;
	}
	public void setF(float f) {
		this.f = f;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public byte getB() {
		return b;
	}
	public void setB(byte b) {
		this.b = b;
	}
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	
	
}
