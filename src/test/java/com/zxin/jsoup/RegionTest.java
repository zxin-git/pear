package com.zxin.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Dao;
import org.nutz.json.Json;

import com.zxin.mvc.core.DBUtil;
import com.zxin.mvc.region.data.Region;

public class RegionTest {
	
	public static Dao dao = DBUtil.dao;
	private static Connection connection = null;
	
	private static Document jDocument;
	
	public static void main(String[] args) {
		dao.create(Region.class,true);
		connection = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201703/t20170310_1471429.html")
				.header("Accept", "*/*")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
				.timeout(10000).ignoreContentType(true);
		try {
			jDocument = connection.get();
			Elements trs = jDocument.select(".TRS_PreAppend > p.MsoNormal");
			List<Region> list = new ArrayList<>();
			char a = 12288;
			for (Element element : trs) {
				String preCode = element.select("span[lang='EN-US']").html();
				String code = preCode.substring(0, preCode.indexOf("<span>"));
				String name = element.select("span[style='font-family: 宋体']").html().replaceAll("\\s+", "").replaceAll(a+"", "");
				Region region = new Region();
				int adcode = Integer.valueOf(code);
				region.setAdcode(adcode);
				region.setName(name);
				if(adcode%10000!=0){
					if(adcode%100!=0){
						region.setPadcode( adcode-(adcode%100) );
						region.setLevel("district");
					}else{
						region.setPadcode( adcode-(adcode%10000) );
						region.setLevel("city");
					}
				}else{
					region.setLevel("province");
					region.setPadcode(100000);
				}
				list.add(region);
			}
			dao.insert(list);
//			System.out.println(Json.toJson(list));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
