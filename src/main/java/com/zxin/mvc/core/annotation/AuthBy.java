package com.zxin.mvc.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.TYPE, ElementType.METHOD}) 
@Documented
public @interface AuthBy {
		
	String[] value() default {};
	
	boolean login() default true;
	
	boolean check() default true;	
	
}
