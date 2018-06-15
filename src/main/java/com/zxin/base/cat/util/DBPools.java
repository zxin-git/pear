package com.zxin.base.cat.util;

import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

public class DBPools {

	private static Logger logger = LoggerFactory.getLogger(DBPools.class);
	
	private static DruidDataSource druidDataSource = null;
	private static NutDao dao = null;
	
	public static DruidDataSource getDruidDataSource(){
		if(druidDataSource == null){
			synchronized (DBPools.class) {
				if(druidDataSource == null){
					Ioc ioc = new NutIoc(new JsonLoader("/dao-test.js"));
//					ioc.get(NutDao.class, "dao");
					druidDataSource = ioc.get(DruidDataSource.class, "dataSource");
				}
			}
		}
		return druidDataSource;
	}
	
	
	public static NutDao dao(){
		if(dao == null){
			synchronized (DBPools.class) {
				if(dao == null){
					Ioc ioc = new NutIoc(new JsonLoader("/dao-test.js"));
					dao = ioc.get(NutDao.class, "dao");
				}
			}
		}
		return dao;
	}
}

