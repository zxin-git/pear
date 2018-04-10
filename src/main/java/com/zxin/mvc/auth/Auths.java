package com.zxin.mvc.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.zxin.mvc.auth.data.User;

public class Auths {
	
	private static Log log = Logs.get();
	
	public static final String USER_SESSION_KEY = "current_user";
	
	public static final String DEFAULT_LOGIN_PATH = "/login.jsp";
	public static final String DEFAULT_LOGIN_ENTRY = "/signin";
	
	public static final User getUser(HttpServletRequest request) {
		if (request != null) {
			HttpSession session = request.getSession(false);
			return getUser(session);
		}
		
		return null;
	}
	
	public static final User getUser(HttpSession session) {
		User user = null;
		if (session != null)
			user = (User) session.getAttribute(USER_SESSION_KEY);
		return user;
	}
	
}
