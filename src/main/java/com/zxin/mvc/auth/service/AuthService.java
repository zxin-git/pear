package com.zxin.mvc.auth.service;

import java.util.List;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.service.IdNameEntityService;

import com.zxin.mvc.auth.data.User;

@IocBean(name="authService", fields = { "dao" })
public class AuthService extends IdNameEntityService<User>{
	
	public void updates(List<User> users){
		for (User user : users) {
			dao().update(user);
		}
	}
	
}
