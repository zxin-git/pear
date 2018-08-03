package com.zxin.jdk.test;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.zxin.util.JsonUtil;

public class ComparatorTest {

	private static Logger logger = LoggerFactory.getLogger(ComparatorTest.class);
	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
		Map<Integer,Integer> map = new TreeMap<>();
		map.put(3, 1);
		map.put(2, 1);
		map.put(4, 1);
		
		System.out.println(JsonUtil.obj2json(map));
	}
}

