package com.zxin.jdk.node.bean;

public class Parent implements Cloneable{

	private int id;
	
	private String name;
	
	private Job job;

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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}
	
	public Parent clone() throws CloneNotSupportedException{
		Parent copy = (Parent) super.clone();
		copy.setJob( this.job.clone() );
		return copy;
	}

}

