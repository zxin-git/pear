package com.zxin.jdk.node.serial;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class User extends Person implements Serializable {

	private static final long serialVersionUID = -798016301527440006L;
	
	private long password;

	private transient HashMap<String, Integer> map = new HashMap<>();
	
	private ArrayList<String> list = new ArrayList<>();
	
	private Student student = null;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getPassword() {
		return password;
	}

	public void setPassword(long password) {
		this.password = password;
	}

	public HashMap<String, Integer> getMap() {
		return map;
	}

	public void setMap(HashMap<String, Integer> map) {
		this.map = map;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}
	
	
}
