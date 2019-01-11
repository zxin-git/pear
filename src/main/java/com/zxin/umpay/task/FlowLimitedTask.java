package com.zxin.umpay.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.umpay.bean.LimitBean;
import com.zxin.umpay.handler.CacheHandler;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.Status;
import com.zxin.umpay.handler.StatusHandler;

public class FlowLimitedTask extends ConcurrentTask{

	private static final Logger logger = LoggerFactory.getLogger(FlowLimitedTask.class);
	
	private LimitBean limitBean;
	
	public FlowLimitedTask(CacheHandler cacheHandler, int threadNum, LimitBean limitBean, StatusHandler handler) {
		super(cacheHandler, threadNum, handler);
		this.limitBean = limitBean;
	}
	
	@Override
	public void run() {
		limitBean.start();
		logger.info("启动查询任务, tps限制：[{}], 线程数：[{}]", limitBean.getMaximum(), threadNum);
		super.run();
		while (Status.STOP.before(cacheHandler.getStatus()) || 
				cacheHandler.getCache().size() > 0) {
			try {
				Thread.sleep(limitBean.getTimeUnit().toMillis(limitBean.getPer()));
				logger.info("当前线程数:{}, 当前缓存池的数量:{}", nowThreadNum.get(), cacheHandler.getCache().size());
			} catch (InterruptedException e) {
				logger.warn("",e);
			}
		}
		limitBean.stop();
	}
	
	@Override
	public void exec(String line) throws Exception {
		limitBean.getLimit().acquire();
		limitBean.count().incrementAndGet();
		super.exec(line);
	}

}

