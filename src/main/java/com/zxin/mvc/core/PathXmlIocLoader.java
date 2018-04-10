package com.zxin.mvc.core;

import org.nutz.ioc.loader.xml.XmlIocLoader;

public class PathXmlIocLoader extends XmlIocLoader {
	

	public PathXmlIocLoader(String... fileNames) {
		super(fileNames);
	}

	@Override
	protected String getScanPatten() {
		return ".+[.]ioc[.]xml$";
	}
	
}
