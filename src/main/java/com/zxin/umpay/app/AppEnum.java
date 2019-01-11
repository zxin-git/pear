package com.zxin.umpay.app;

public enum AppEnum {

	//生产环境
	FONT_UAT_53("http://10.102.5.53:9006/umpaydc/dataQuery/query","前置_UAT_53",null,null,""),
	FONT_TEST_53("http://10.102.5.53:9005/umpaydc/dataQuery/query/","前置_真实测试_53",null,null,""),
	FONT_PRO_51("http://10.102.5.51:9005/umpaydc/dataQuery/query","前置_生产_51",null,null,"http://10.102.5.51:9006/"),
	FONT_PRO_52("http://10.102.5.52:9005/umpaydc/dataQuery/query","前置_生产_52",null,null,"http://10.102.5.52:9006/"),
	FONT_MERID_TEST("https://103.235.230.237:8444//umpaydc/dataQuery/","",null,null,"http://10.102.5.52:9006/"),
	
	YLZH_PRO("http://10.102.5.51:9017/","银联智慧数据源_生产",null,null,null),
	YLZH_TEST("http://10.102.5.53:9018/","银联智慧数据源_测试",null,null,null),

	BATCH_PRO("http://10.102.5.53:9019/fileBatch/fileTask","跑批_生产",null,null,"http://10.102.5.53:8443/prod_fileBatch/fileTask"),
	BATCH_UAT("http://10.102.5.53:9017/fileBatch/fileTask","跑批_测试",null,null,"http://10.102.5.53:8443/fileBatch/fileTask"),
	

	//待生产环境
	MODEL_TEST("http://192.168.110.128:9016/query","模型测试环境直连",null,null,null),
	GATEWAY_PlUM("http://192.168.110.128:8888/plum/query","模型网关环境",null,null,null),
	GATEWAY_TEST("http://192.168.110.128:8888/umf-test","模型网关环境",null,null,null),
	
	//模型
	MODEL_GATEWAY_QUERY("http://10.102.1.75:8888/wdph/query","随机",null,null,null),
	MODEL_GATEWAY_UMF("http://10.102.1.75:8888/souche/umf-test","随机",null,null,null),
	
	UMF_TEST("http://192.168.110.129:9016/umf-test","随机",null,null,null),
	
	//测试环境
	TEST1("https://10.102.5.53:8443/umpaydc/dataQuery","随机",null,null,null),
	TEST2("http://192.168.110.129:9016/umf-test","随机",null,null,null),
	TEST("http://10.102.5.151:9000/ylzhCid?queryType=5","随机",null,null,null),
	
	LAST("http://10.102.5.53:9018/","银联智慧数据源_测试",null,null,null);
	
	private final String url;	

	private String desc;

	private final String path;
	
	private final String logPath;
	
	private final String nginxUrl;
	
//	private final AppEnum next;
	

	private AppEnum(String url, String desc, String path, String logPath, String nginxUrl) {
		this.url = url;
		this.path = path;
		this.logPath = logPath;
		this.desc = desc;
		this.nginxUrl = nginxUrl;
	}

	public String desc() {
		return desc;
	}

	public String url() {
		return url;
	}

	public String path() {
		return path;
	}

	public String logPath() {
		return logPath;
	}

	public String nginxUrl() {
		return nginxUrl;
	}
	
}

