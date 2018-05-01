package com.zxin.jdk.test.jdk8.function;

import java.util.function.Supplier;

public interface CreateFactory {  
    /** 
     * 通过Supplier函数接口创建对象 
     *  
     * @param <T> 
     * @param supplier 
     * @return 
     */  
    public static <T> T create(final Supplier<T> supplier) {  
        return supplier.get();  
  
    }  
}