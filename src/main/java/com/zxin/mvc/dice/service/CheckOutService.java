package com.zxin.mvc.dice.service;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.nutz.dao.Dao;
import org.nutz.json.Json;

import com.zxin.mvc.core.DBUtil;
import com.zxin.mvc.dice.data.ChartBall;


public class CheckOutService {
	public static Dao dao = DBUtil.dao;
	private static Connection connection = null;
	
	private static org.jsoup.nodes.Document jDocument;
	public static void main(String[] args) {
		connection = Jsoup.connect("https://datachart.500.com/ssq/zoushi/newinc/jbzs_redblue.php?expect=500").userAgent("Mozilla");
		List<ChartBall> list = new ArrayList<ChartBall>();
		try {
			jDocument = connection.get();
			Elements trs = jDocument.select("#tdata > tr");
			for (Element tr : trs) {
				if(tr.className().equals("tdbck")) continue;
				ChartBall chartBall = new ChartBall();
				List<Integer> reds = new ArrayList<Integer>();
				for(Element td:tr.getAllElements()){					
					if(td.classNames().contains("chartBall01")){
						reds.add(Integer.parseInt(td.text()));						
					}else if(td.classNames().contains("chartBall02")){
						chartBall.setBlueBall(Integer.parseInt(td.text()));
					}else if(td.attr("align").equals("center")){
						chartBall.setTerm(Integer.parseInt(td.text()));
					}
					chartBall.setRedBall(reds);
				}
				if(chartBall.getRedBall().size()!=6 || chartBall.getBlueBall()==0 ||chartBall.getTerm()==0)
					throw new Exception(Json.toJson(chartBall));	
				list.add(chartBall);
			}
			System.out.println(list.size()+"  \n"+Json.toJson(list));
			dao.clear(ChartBall.class);
			dao.insert(list);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
