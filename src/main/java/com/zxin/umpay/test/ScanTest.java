package com.zxin.umpay.test;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zxin.apache.http.httpclient.HttpClientUtil;
import com.zxin.umpay.app.AppEnum;
import com.zxin.umpay.batch.ScanUtil;
import com.zxin.umpay.bean.LimitBean;
import com.zxin.umpay.handler.CacheHandler;
import com.zxin.umpay.handler.IHandler;
import com.zxin.umpay.handler.impl.StoreHandler;
import com.zxin.umpay.http.ReqBodyBuilder;
import com.zxin.umpay.store.FileStore;
import com.zxin.umpay.task.FlowLimitedTask;
import com.zxin.umpay.task.LimitTask;

public class ScanTest {

	private static final Logger logger = LoggerFactory.getLogger(ScanTest.class);

	public static void main(String[] args) {
//		ReqBodyBuilder reqBodyBuilder = new ReqBodyBuilder().funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK"); 
		ReqBodyBuilder reqBodyBuilder = new ReqBodyBuilder().funcode("Gup1006474").merid("03806002").license("xmm8wbp1phauxiucuc7u"); 
//		ReqBodyBuilder reqBodyBuilder = new ReqBodyBuilder().funcode("Gup1006357").name("%E6%BB%A1%E9%B8%BF%E5%BF%97").identityNo("150104198211200513").license("4vnvm648tz1inki2qxa2").childmerid("YLZHMYTK"); 
//		ScanUtil.scan(new File("D:\\file\\mobile\\mobile.txt"),new CacheHandler(new LimitTask(AppEnum.FONT_UAT_53, reqBodyBuilder, 7, 3).getCache()));
//		ScanUtil.scan(new File("D:\\file\\mobile\\mobile10000.txt"),new CacheHandler(LimitTask.newCache(AppEnum.MODEL_GATEWAY_QUERY, reqBodyBuilder, 10, 3)));
		CacheHandler cacheHandler = new CacheHandler();
		StoreHandler storeHandler = new StoreHandler(reqBodyBuilder, AppEnum.MODEL_GATEWAY_QUERY, new FileStore("E:/bat.txt"));
		LimitBean limitBean = new LimitBean(5, 1, TimeUnit.SECONDS);
		FlowLimitedTask flowLimitedTask = new FlowLimitedTask(cacheHandler, 1, limitBean, storeHandler);
		flowLimitedTask.start();
		ScanUtil.scan(new File("D:\\file\\model\\wdph_228.txt"), cacheHandler);
//		ScanUtil.scan(new File("D:\\file\\mobile\\mobile10000.txt"),new CacheHandler(LimitTask.newCache(AppEnum.MODEL_GATEWAY_QUERY, reqBodyBuilder, 10, 3)));

		
	}
	
	public static void  test(){
		ScanUtil.scan(new File("D:\\file\\mobile\\mobile20.txt"), new IHandler() {
			
			@Override
			public void handler(String line) throws Exception {
				String reqXml = new ReqBodyBuilder().mobileid(line)
						.funcode("Gcs6000002").license("aqisbfuv18pn1mjc6yv2").datetime("20180430234239").childmerid("YLZHMYTK").set("labelType", "T0001;T0002;T0003;T0004").sign().buildXml();
//						.funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK").sign().buildXml();
//						.funcode("Mkt2000009").license("19y3sym331nanwyzupp2").childmerid("YLZHMYTK").size("200").file().sign( (reqMap) -> {return SignUtils.batchSign(reqMap);} ).buildXml();
				String response = HttpClientUtil.sendByPost(AppEnum.FONT_UAT_53.url(), reqXml);
//				new IPare(){}.pare(response);
//				System.err.println(new LabelBean().pare(response));
				System.out.println(response);
//				System.out.println(reqXml);
			}
			
		});
	}
	
}

