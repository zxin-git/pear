package com.zxin.jdk.node.concurrent;
//只给出要修改的代码，其余代码与上同

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynReentrantLock {
    
    private int account = 100;
    //需要声明这个锁
    private Lock lock = new ReentrantLock();
    public int getAccount() {
        return account;
    }
    //这里不再需要synchronized 
    public void save(int money) {
        lock.lock();
        try{
            account += money;
        }finally{
            lock.unlock();
        }
        
    }
}