package com.zxin.jdk.test.jdk8;

//允许给接口本身添加一个默认的实现。用“default”进行修饰
public interface DefaultImpl {

 /**
  * 定义加法运算并给他默认实现方法
  * @param i1 加数
  * @param i2 加数
  * @return 和
  */
 default int sum(int i1, int i2){
  return i1 + i2;
 }

 /**
  * 定义减法运算接口
  * @param i1 减数
  * @param i2 被减数
  * @return 差
  */
 int subtraction(int i1, int i2);
}