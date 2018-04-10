package com.zxin.mvc.core;

import org.nutz.mvc.NutConfig;

/**
 * 启动监听接口，需要Ioc支持
 * @author YG
 */
public interface SetupListener {

	/**
	 * 启动时执行
	 * @param nc
	 */
	public void init(NutConfig nc);
	
	/**
	 * 销毁时执行
	 * @param nc
	 */
	public void destroy(NutConfig nc);
}
