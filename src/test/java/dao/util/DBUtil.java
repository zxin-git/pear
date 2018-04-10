package dao.util;

import java.io.File;
import java.io.IOException;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.FileSqlManager;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;
import org.nutz.dao.util.DaoUp;
import org.nutz.lang.Files;
import org.nutz.lang.Xmls;
import org.w3c.dom.Document;

public class DBUtil {
	public static NutDao dao = new NutDao();
	public static SimpleDataSource dataSource = new SimpleDataSource();;
	
	static{
		// 创建一个数据源
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mvc?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
//		dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3308/tsoc");
//		dataSource.setUsername("root");
//		dataSource.setPassword("venustech.tsoc.db.ROOT");
		dao.setDataSource(dataSource);
	}
	
	public static Dao dao(){
		try {
			DaoUp.me().init(new File("E:/zx/developer/java/git/repo/mvc/mvc/src/main/resources/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return DaoUp.me().dao();
	}
	public static void addSqlFile(){
//		dao = new NutDao(dataSource,new FileSqlManager("demo/sqls/all.sqls"));
		dao.setSqlManager(new FileSqlManager("sqls/all.sqls"));
//		如果你给出的 path 是一个目录，那么该目录下所有后缀为.sqls 的文件都会被加载
	}
	
	public static void main(String[] args) {
		Document xmlDoc = Xmls.xml(Files.findFile("pom.xml"));
		System.out.println(xmlDoc);
	}
	
}
