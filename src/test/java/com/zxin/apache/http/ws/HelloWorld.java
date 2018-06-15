package com.zxin.apache.http.ws;
import javax.jws.WebService;  
  
@WebService  
public interface HelloWorld  {
	
//	@WebService:定义服务，用在类上
//	@WebMethod:定义方法，用于方法上
//	@WebResult:定义返回值，用在方法上
//	@WebParam:定义参数，用在方法上
	
    public String sayHello(String name);  
} 