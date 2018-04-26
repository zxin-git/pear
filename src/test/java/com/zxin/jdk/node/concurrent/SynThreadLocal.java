package com.zxin.jdk.node.concurrent;

// a.ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题。 
//b.前者采用以"空间换时间"的方法，后者采用以"时间换空间"的方式

//ThreadLocal() : 创建一个线程本地变量 
//get() : 返回此线程局部变量的当前线程副本中的值 
//initialValue() : 返回此线程局部变量的当前线程的"初始值" 
//set(T value) : 将此线程局部变量的当前线程副本中的值设置为value
public class SynThreadLocal{
    //使用ThreadLocal类管理共享变量account
    private static ThreadLocal<Integer> account = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 100;
        }
    };
    public void save(int money){
        account.set(account.get()+money);
    }
    public int getAccount(){
        return account.get();
    }
}