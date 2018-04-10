package com.zxin.base.junit;
import org.junit.runner.RunWith;  
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;  
@RunWith(Suite.class)  
@SuiteClasses({ParametTestUnit.class,CalculatorTest.class})  
public class SuiteUnit {}  