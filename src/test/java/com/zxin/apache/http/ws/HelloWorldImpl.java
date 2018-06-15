package com.zxin.apache.http.ws;

import javax.jws.WebService;

@WebService  
public class HelloWorldImpl implements HelloWorld  
{  
  
    @Override  
    public String sayHello(String name)  
    {  
          
        System.out.println("sayHello方法被调用");  
        return ("Hello"+name);  
          
    }  
  
}  