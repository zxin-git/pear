package com.zxin.umpay.app;

public enum AppEnum {

	FONT_UAT_53("http://10.102.5.53:9006/umpaydc/dataQuery/query","前置_UAT_53",null,null,""),
	FONT_TEST_53("http://10.102.5.53:9005/umpaydc/dataQuery/query","前置_真实测试_53",null,null,""),
	FONT_PRO_51("http://10.102.5.51:9005/umpaydc/dataQuery/query","前置_生产_51",null,null,"http://10.102.5.51:9006/"),
	FONT_PRO_52("http://10.102.5.52:9005/umpaydc/dataQuery/query","前置_生产_52",null,null,"http://10.102.5.52:9006/"),
	
	YLZH_PRO_("http://10.102.5.51:9017/","银联智慧数据源_生产",null,null,null),
	YLZH_TEST("http://10.102.5.53:9018/","银联智慧数据源_测试",null,null,null),

	BATCH_PRO("http://10.102.5.53:9019/fileBatch/fileTask","跑批_生产",null,null,"http://10.102.5.53:8443/prod_fileBatch/fileTask"),
	BATCH_UAT("http://10.102.5.53:9017/fileBatch/fileTask","跑批_测试",null,null,"http://10.102.5.53:8443/fileBatch/fileTask"),
	
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

