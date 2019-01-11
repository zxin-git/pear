package com.zxin.umpay;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PatternTest {

	private static final Logger logger = LoggerFactory.getLogger(PatternTest.class);

	static enum Type{tokenid, jsonData, checkCode, apiCode; }
	
	public static void main(String[] args) {
		String s2="tokenid=zhaolian_60586A7EBD6548626ACBF4455376E642&apiCode=3000109&jsonData={\"cell\":[\"85dcf9d4bf0e8066e6c57c3a76b3f611\"],\"id\":\"a3c8451a555f713cdb9e06819161eeb2\",\"name\":\"李建明\",\"meal\":\"EquipmentCheck\",\"device_id\":\"864682038149944&460036500452292\",\"device_type\":\"IMEI\",\"apply_type\":\"g\",\"ExtData\":{}}&checkCode=e3d3e37139312a1b4771ccc53221a624";
		
		String[] paramItems = {"tokenid", "jsonData", "checkCode", "apiCode"}; 
		final String SYMBOL = "=";  
		TreeSet<Integer> treeSet = new TreeSet<>(); 
		for (String item : paramItems) {
			int index = s2.indexOf(item + SYMBOL);
			treeSet.add(index);
		}
		
		Map<String, String> map = new HashMap<>();
		for (String item : paramItems) {
			int index = s2.indexOf(item + SYMBOL);
			if(index < 0)
				continue;
			int start = index + item.length() + SYMBOL.length();
			int end = s2.length();
			if (!treeSet.last().equals(index)) {
				end = treeSet.higher(start) - 1;	//非最后意向前，都有'&'符号,所以减一
			}
			map.put(item, s2.substring(start, end));
		}
		System.out.println(Json.toJson(map));
	}
	
}

