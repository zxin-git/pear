package com.zxin.apache.http.rs;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;

public class MyServer {
	public static void main(String[] args) throws Exception {
		JAXRSServerFactoryBean factoryBean = new JAXRSServerFactoryBean();
//		factoryBean.getInInterceptors().add(new LoggingInInterceptor());
//		factoryBean.getOutInterceptors().add(new LoggingOutInterceptor());
		factoryBean.setResourceClasses(CustomerServiceImpl.class);
		factoryBean.setAddress("http://localhost:9000/ws/jaxrs");
		factoryBean.create();
	}
}