package com.zxin.jdk.test.jdk8.function;

import java.util.Arrays;
import java.util.List;

public class TestMethodReferences {  
  
    public static void main(String[] args) {  
          
        //第一种引用 构造方法引用，语法是：Class::new ，  
        //对于泛型来说语法是：Class<T >::new，请注意构造方法没有参数:  
        User user = CreateFactory.create(User::new);  
        user.setName("xiaowen");  
        user.setAge("11");  
        List<User> users = Arrays.asList(user);     
        System.out.println("大家好!我是"+users.get(0).getName()+" 今年"+users.get(0).getAge());  
          
          
        //第二种 静态方法引用   
        //语法是：Class::static_method请注意这个静态方法只支持一个类型为user的参数。  
        users.forEach(User::collide);  
          
        //第三种是类实例的方法引用，语法是：Class::method请注意方法没有参数  
        users.forEach(User::repair);  
          
        //第四种是引用特殊类的方法  
        //语法是：instance::method，请注意只接受user类型的一个参数  
        final User police = CreateFactory.create(User::new);  
        users.forEach( police::follow );  
  
    }  
}  