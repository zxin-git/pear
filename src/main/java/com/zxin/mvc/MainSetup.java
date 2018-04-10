package com.zxin.mvc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.nutz.integration.quartz.NutQuartzCronJobFactory;
import org.nutz.ioc.Ioc;
import org.nutz.lang.Stopwatch;
import org.nutz.lang.Strings;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.mvc.core.AbstractSetupListener;



public class MainSetup implements Setup {

	private static final Logger logger = LoggerFactory.getLogger(MainSetup.class);

	private List<AbstractSetupListener> listeners = new ArrayList<AbstractSetupListener>();
	
	public void init(NutConfig nc) {
		if(logger.isDebugEnabled())
			logger.debug("{} {} init", nc.getAppName(), nc.getAppRoot());
		final ServletContext ctx = nc.getServletContext();
		System.setProperty("context.path", ctx.getContextPath());		//类似"/smp"
		String webdir = new File(nc.getAppRoot()).toPath().normalize().toString(); //未使用getCanonicalPath，避免需要捕获异常
		System.setProperty("web.dir", webdir);			//当前应用的根路径，类似"E:\apache-tomcat-7.0.14\webapps\smp"
		System.setProperty("webinf.dir", webdir + File.separator + "WEB-INF");
		System.setProperty("conf.dir", webdir + File.separator + "WEB-INF"+File.separator+"conf");
		System.setProperty("classes.dir", webdir + File.separator + "WEB-INF" + File.separator + "classes");
		Ioc ioc = nc.getIoc();
	
		//初始化启动服务
		initListener(nc);
		//初始化区域缓存
//		Dao dao = nc.getIoc().get(Dao.class);	
//		ioc.get(NutQuartzCronJobFactory.class);
	}

	public void destroy(NutConfig nc) {
		if(logger.isDebugEnabled()) 
			logger.debug("{} {} destroy", nc.getAppName(), nc.getAppRoot());
		// 销毁所有启动服务
		destoryListener(nc);
	}
	
	private void initListener(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		// 从ioc中获取所有的服务类
		for(String name : ioc.getNames()) {
			if (name != null && name.trim().startsWith("$setup_")) {
				AbstractSetupListener listener = ioc.get(AbstractSetupListener.class, name);
				listeners.add(listener);
			}
		}
		// 对服务类的权重进行排序   
		listeners.sort((o1, o2) -> Integer.toString(o2.getWeight()).compareTo(Integer.toString(o1.getWeight())));
		for (int i = 0; i < listeners.size(); i++) { 
			Stopwatch sw;
			AbstractSetupListener listener = listeners.get(i);
			if(!Strings.isBlank(listener.getBefore())) { 
				if(ioc.has(listener.getBefore())) {
					AbstractSetupListener beforeListener = ioc.get(AbstractSetupListener.class, listener.getBefore());
					if(!beforeListener.getIsRunning()) {
						sw = Stopwatch.begin();
						logger.info(String.format("SetupListener[%s] is initializing...", beforeListener.getName()));
						beforeListener.init(nc);
						beforeListener.setIsRunning(true);
						sw.stop();
						logger.info("SetupListener [{}] inited use {} ms", beforeListener.getName(), sw.getDuration());
					}
				} else {
					logger.warn("SetupListener [{}] is not found in Ioc", listener.getBefore());
				}
			}
			if(!listener.getIsRunning()) {
				sw = Stopwatch.begin();
				logger.info(String.format("SetupListener[%s] is initializing...", listener.getName()));
				listener.init(nc);
				listener.setIsRunning(true);
				sw.stop();
				logger.info("SetupListener [{}] inited use {} ms", listener.getName(), sw.getDuration());
			}
			if(!Strings.isBlank(listener.getAfter())) {
				if(ioc.has(listener.getAfter())) {
					AbstractSetupListener afterListener = ioc.get(AbstractSetupListener.class, listener.getAfter());
					if(!afterListener.getIsRunning()) {
						sw = Stopwatch.begin();
						logger.info(String.format("SetupListener[%s] is initializing...", listener.getName()));
						afterListener.init(nc);
						afterListener.setIsRunning(true);
						sw.stop();
						logger.info("SetupListener [{}] inited use {} ms", afterListener.getName(), sw.getDuration());
					}
				} else {
					logger.warn("SetupListener [{}] is not found in Ioc", listener.getAfter());
				}
			}
		}
	}
	
	private void destoryListener(NutConfig nc) {
		Ioc ioc = nc.getIoc();
		Stopwatch sw;
		AbstractSetupListener listener = listeners.get(0);
		if(!Strings.isBlank(listener.getBefore())) {
			if(ioc.has(listener.getBefore())) {
				AbstractSetupListener beforeListener = ioc.get(AbstractSetupListener.class, listener.getBefore());
				if(beforeListener.getIsRunning()) {
					sw = Stopwatch.begin();
					beforeListener.destroy(nc);
					beforeListener.setIsRunning(false);
					sw.stop();
					logger.info("SetupListener [{}] destoryed use {} ms", beforeListener.getName(), sw.getDuration());
				}
			} else {
				logger.warn("SetupListener [{}] is not found in Ioc", listener.getBefore());
			}
		}
		if(listener.getIsRunning()) {
			sw = Stopwatch.begin();
			listener.destroy(nc);
			listener.setIsRunning(false);
			sw.stop();
			logger.info("SetupListener [{}] inited destoryed {} ms", listener.getName(), sw.getDuration());
		}
		if(!Strings.isBlank(listener.getAfter())) {
			if(ioc.has(listener.getAfter())) {
				AbstractSetupListener afterListener = ioc.get(AbstractSetupListener.class, listener.getAfter());
				if(afterListener.getIsRunning()) {
					sw = Stopwatch.begin();
					afterListener.destroy(nc);
					afterListener.setIsRunning(false);
					sw.stop();
					logger.info("SetupListener [{}] destoryed use {} ms", afterListener.getName(), sw.getDuration());
				}
			} else {
				logger.warn("SetupListener [{}] is not found in Ioc", listener.getAfter());
			}
		}
	}	
	
}
