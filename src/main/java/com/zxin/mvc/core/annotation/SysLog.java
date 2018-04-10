package com.zxin.mvc.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 必须带这个,不然读取不到的
@Target({ElementType.METHOD}) // aop,一般指方法
@Documented // 记录到javadoc
public @interface SysLog {
	String value() default "";
}