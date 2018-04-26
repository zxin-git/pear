package com.zxin.jdk.node.concurrent;

public class SynTest {
	//synchronized关键字修饰的方法 ,java的每个对象都有一个内置锁    锁对象,	 静态方法 锁类
	
	//	a.volatile关键字为域变量的访问提供了一种免锁机制， 
	//    b.使用volatile修饰域相当于告诉虚拟机该域可能会被其他线程更新， 
	//    c.因此每次使用该域就要重新计算，而不是使用寄存器中的值 
	//    d.volatile不会提供任何原子操作，它也不能用来修饰final类型的变量
	
	public synchronized void save(int money) {
        
    }
	
	public static synchronized void s(int money) {
        
    }
	
	public static void main(String[] args) {
		synchronized(args){
			
		}
	}
}
