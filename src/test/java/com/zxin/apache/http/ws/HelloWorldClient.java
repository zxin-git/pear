package com.zxin.apache.http.ws;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;  
  
public class HelloWorldClient  
{  
  
    public static void main(String[] args)  
    {  
    	//Proxy
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();  
        factory.setAddress("http://localhost:8080/HelloWorld");  
        factory.setServiceClass(HelloWorld.class);  
        HelloWorld helloWorld = (HelloWorld) factory.create();  
        System.out.println(helloWorld.sayHello("zhuwei"));  
  
    }  
  
} 