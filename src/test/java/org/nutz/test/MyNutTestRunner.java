package org.nutz.test;

import org.junit.runners.model.InitializationError;
import org.nutz.ioc.Ioc;
import org.nutz.mock.NutTestRunner;


public class MyNutTestRunner extends NutTestRunner {
	public MyNutTestRunner(Class<?> klass) throws InitializationError{
		super(klass);
	}
	
	public Class<?> getMainModule() {
		return MainModule.class;
	}
	
	/**
     * 直接返回Ioc参数,例如 return new String[]{"*js", "ioc/", "*anno", "net.wendal.nutzbook", "*tx", "*async"};
     */
//    protected String[] getIocArgs() {
//    	return new String[]{"*com.zxin.mvc.core.PathXmlIocLoader" , "WEB-INF/conf", "*async"};
//    }
	/**
	* 可覆盖createIoc,实现参数覆盖, bean替换,等定制.
	*/
	protected Ioc createIoc() {
		Ioc ioc = super.createIoc();
		
		return ioc;
	}
}
