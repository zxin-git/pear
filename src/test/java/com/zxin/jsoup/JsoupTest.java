package com.zxin.jsoup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;

import com.zxin.mvc.core.DBUtil;
import com.zxin.mvc.region.data.Region;
import com.zxin.mvc.region.data.Town;

public class JsoupTest {
	
	public static Dao dao = DBUtil.dao;
	private static Connection connection = null;
	
	private static Document jDocument;
	
	public static void main(String[] args) {
//		dao.create(Town.class,true);
		
		String preUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/";
		Map<String,String> map = new HashMap<>();
		map.put("Accept", "*/*");
		map.put("Accept-Encoding", "gzip, deflate");
		map.put("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		map.put("Content-Type", "application/json;charset=UTF-8");
		map.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0");
		
		List<Region> regions = dao.query(Region.class, Cnd.where("level","=","district"));//.and("adcode",">","370831")
		for(Region region : regions){
			int code = region.getAdcode();
			String province = code/10000 >9?code/10000+"":"0"+code/10000;
			String city = code%10000/100 >9?code%10000/100+"":"0"+code%10000/100;
			
//			int district = code%10000;
			String url = preUrl+province+"/"+city+"/"+code+".html";
			List<Town> towns = new ArrayList<>();
			try {
				jDocument = Jsoup.connect(url).headers(map).timeout(10000).ignoreContentType(true).get();
				Elements trs = jDocument.select("table.towntable tr.towntr");
				for(Element tr:trs){
					String id = tr.select("td:eq(0)>a").text();
					String surUrl = tr.select("td:eq(0)>a").attr("href");
					String name = tr.select("td:eq(1)>a").text();
					Town town = new Town();
					town.setAdcode(Long.valueOf(id));
					town.setName(name);
					town.setLevel("town");
					town.setPadcode((long)code*1000000);
					towns.add(town);
					
//					String vUrl = preUrl+province+"/"+city+"/"+surUrl;
//					Document vdoc = Jsoup.connect(vUrl).headers(map).timeout(10000).ignoreContentType(true).get();
//					Elements vtrs = vdoc.select("table.villagetable  tr.villagetr");
//					for(Element vtr:vtrs){
//						Town village = new Town();
//						String vid = vtr.select("td:eq(0)").text();
//						String type = vtr.select("td:eq(1)").text();
//						String vname = vtr.select("td:eq(2)").text();
//						village.setAdcode(Long.valueOf(vid));
//						village.setName(vname);
//						village.setLevel("village");
//						village.setType(type);
//						village.setPadcode(Long.valueOf(id));
//						towns.add(village);
//					}
				}
			} catch (IOException e) {
				System.out.println(e);
				continue;
			}finally{
//				try {
//					Thread.sleep(30*1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				dao.insert(towns);
			}
//			if(towns.size()>=300){
//				dao.insert(towns);
//			}
		}
		
	}
}
