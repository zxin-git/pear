package com.zxin.jdk.test.jdk8.function;

public interface MineFun{
	
}

@FunctionalInterface
interface FunFactory<In, Out> {
	Out run(In arg);
}

@FunctionalInterface
interface FunFactory2<In>{
	@SuppressWarnings("unchecked")
	void run(In... a);
}

@FunctionalInterface
interface Constructor<In>{
	@SuppressWarnings("unchecked")
	In run();
}
