package com.zxin.mvc.core;

public abstract class AbstractSetupListener implements SetupListener {

	protected String name;
	
	protected int weight = 1;
	
	protected String module;
	
	protected String before;
	
	protected String after;
	
	protected Boolean isRunning = false;
	
	public String getName() {
		return name;
	}
	
	final public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	final public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getModule() {
		return module;
	}

	final public void setModule(String module) {
		this.module = module;
	}

	public String getBefore() {
		return before;
	}

	final public void setBefore(String before) {
		this.before = before;
	}

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public void setIsRunning(Boolean isRunning) {
		this.isRunning = isRunning;
	}
	
}
