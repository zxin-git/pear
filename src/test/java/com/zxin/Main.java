package com.zxin;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		 HashedMap map = new HashedMap();
		 map.put("qwe", "qwe");
		 Map map2 = Collections.unmodifiableMap(map);
		 map2.put("asd", "asd");
	}
	
}

