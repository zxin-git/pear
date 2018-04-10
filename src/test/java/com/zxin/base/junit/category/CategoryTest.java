package com.zxin.base.junit.category;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

//对应的测试执行代码如下：
@RunWith(Categories.class)
@SuiteClasses(PersonTest.class)
@IncludeCategory(String.class)
public class CategoryTest{
 //注意，如果不加@IncludeCategory注解，那么就和使用Suit具有一样的效果了。
}