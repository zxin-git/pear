package com.zxin.jdk.design.single;

public enum Singleton { //Enum
	INSTANCE;	//在序列化的时候Java仅仅是将枚举对象的name属性(INSTANCE)输出到结果中，
				//反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法来根据名字查找枚举对象。
	
	private int id;
	
	private String username;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
}
