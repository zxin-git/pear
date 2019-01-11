package com.zxin.umpay.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.time.FastDateFormat;

import com.zxin.umpay.handler.ISign;
import com.zxin.umpay.util.SignUtils;
import com.zxin.umpay.util.XmlUtils;

public class ReqBodyBuilder {

	private final Map<String, String> map = new HashMap<>();
	
	/**
	 * 默认值
	 */
	{
		map.put(HttpMap.MERID, "10001001");
		map.put(HttpMap.TRANSID, "zhangxin");
		map.put(HttpMap.DATETIME, FastDateFormat.getInstance("yyyyMMddHHmmss").format(System.currentTimeMillis()));
	}
	
	public static ReqBodyBuilder create(){
		return new ReqBodyBuilder();
	}
	
	public ReqBodyBuilder(){
		
	}
	
	public ReqBodyBuilder(ReqBodyBuilder reqBodyBuilder){
		this.map.putAll(reqBodyBuilder.map);	//能直接获取?
	}
	
	
	public ReqBodyBuilder merid(String merid){
		map.put(HttpMap.MERID, merid);
		return this;
	}
	
	public ReqBodyBuilder transid(String transid){
		map.put(HttpMap.TRANSID, transid);
		return this;
	}
	
	public ReqBodyBuilder datetime(String datetime){
		map.put(HttpMap.DATETIME, datetime);
		return this;
	}
	
	
	public ReqBodyBuilder funcode(String funcode){
		map.put(HttpMap.FUNCODE, funcode);
		return this;
	}
	
	public ReqBodyBuilder license(String license){
		map.put(HttpMap.LICENSE, license);
		return this;
	}
	
	public ReqBodyBuilder mobileid(String mobileid){
		map.put(HttpMap.MOBILEID, mobileid);
		return this;
	}
	
	
	public ReqBodyBuilder childmerid(String childmerid){
		map.put(HttpMap.CHILDMERID, childmerid);
		return this;
	}
	
	public ReqBodyBuilder identityNo(String identityNo){
		map.put(HttpMap.IDENTITYNO, identityNo);
		return this;
	}
	
	public ReqBodyBuilder name(String name){
		map.put(HttpMap.NAME, name);
		return this;
	}
	
	public ReqBodyBuilder queryDate(String queryDate){
		map.put(HttpMap.QUERYDATE, queryDate);
		return this;
	}
	
	public ReqBodyBuilder querymonth(String querymonth){
		map.put(HttpMap.QUERY_MONTH, querymonth);
		return this;
	}
	
	
	
	public ReqBodyBuilder size(String size){
		map.put(HttpMap.SIZE, size);
		return this;
	}
	
	public ReqBodyBuilder file(String file){
		map.put(HttpMap.FILE, file);
		return this;
	}
	
	public ReqBodyBuilder file(){
		String file =  map.get("funcode") + "_" + map.get("merid") + "_" + map.get("datetime") + ".txt";
		map.put(HttpMap.FILE, file);
		return this;
	}
	
	
	public ReqBodyBuilder sign(String sign){
		map.put(HttpMap.SIGN, sign);
		return this;
	}
	
	public ReqBodyBuilder sign(){
		String sign = SignUtils.fontSign(map);
		map.put(HttpMap.SIGN, sign);
		return this;
	}
	
	public ReqBodyBuilder sign(ISign sign){
		map.put(HttpMap.SIGN, sign.makeSign(map));
		return this;
	}
	
	
	public ReqBodyBuilder set(String key, String value){
		map.put(key, value);
		return this;
	}
	
	public Map<String, String> getReqMap(){
		return map;
	}
	
	public Map<String, String> build(){
		return map;
	}
	
	public String buildXml(){
		return XmlUtils.mapToXml(build(), HttpMap.REQUEST);
	}
	
	
}

