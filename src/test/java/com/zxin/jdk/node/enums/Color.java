package com.zxin.jdk.node.enums;

public enum Color {
	RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);

	private final String name ;
    private final int index ;
     
    private Color( String name , int index ){
        this.name = name ;
        this.index = index ;
    }
     
    public final String getName() {
        return name;
    }
    public final int getIndex() {
        return index;
    }
}
