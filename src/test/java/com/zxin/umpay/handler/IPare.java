package com.zxin.umpay.handler;

import com.zxin.javaee.jaxb.Jaxb7Util;
import com.zxin.umpay.http.ResponseBean;

public interface IPare {

	default String pare(String response) throws Exception{
		ResponseBean responseBean = Jaxb7Util.toBean(response, ResponseBean.class);
		return responseBean.toString();
	}
	
}

