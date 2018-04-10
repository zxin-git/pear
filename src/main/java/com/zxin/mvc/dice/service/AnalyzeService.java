package com.zxin.mvc.dice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.hyperic.sigar.cmd.Ifconfig;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.random.R;

import com.zxin.mvc.core.DBUtil;
import com.zxin.mvc.dice.data.ChartBall;

public class AnalyzeService {
	public static Dao dao = DBUtil.dao;
	
	public static Set<Integer> redSet = new HashSet<>(33);
	public static Set<Integer> blueSet= new HashSet<>(16);
	
	static {
		for (int i = 1; i <= 33; i++) {
			redSet.add(i);
			if(i<=16) blueSet.add(i);
		}
	}
	
	public static List<ChartBall> preChartBall(int num,int size){
		List<ChartBall> preList = dao.query(ChartBall.class, Cnd.orderBy().desc("term"),dao.createPager(num, size));
		return preList;
	}
	
	public ChartBall makeChartBall(){
		ChartBall chartBall = new ChartBall();		
		
		
		return chartBall;
	}
	
	public static List<Integer> makeReds(int num,int size){
		List<Integer> reds = new ArrayList<>();
		List<ChartBall> preList = preChartBall(num,size);
		Map<Integer,Integer> map = new HashMap<>();
		if (preList!=null) {			
			for (ChartBall pre : preList) {
				if(pre.getRedBall()!=null){
					for (int red : pre.getRedBall()) {
						if(map.containsKey(red)){
							map.put(red,map.get(red)+1);							
						}else{
							map.put(red, 1);
						}
					}					
				}
			}
		}
		List<Map.Entry<Integer, Integer>> orderList = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet()); 
		Collections.sort(orderList, new Comparator<Map.Entry<Integer, Integer>>() {  
            @Override  
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });
//		System.out.println(orderList.size());
		int i =0;
		for (Entry<Integer, Integer> entry : orderList) {
//			System.out.println(entry.getKey()+" : "+entry.getValue()+" 次;");
			if(i<6){
				reds.add(entry.getKey());
				i++;
			}
				
		}
		return reds;
		
	}
	
	public static List<Integer> makeBlue(int num,int size){
		List<Integer> blues = new ArrayList<>();
		List<ChartBall> preList = preChartBall(num,size);
		Map<Integer,Integer> map = new HashMap<>();
		if (preList!=null) {			
			for (ChartBall pre : preList) {
				int blue = pre.getBlueBall();
				if (blue==0) break;
				if(map.containsKey(blue)){
					map.put(blue,map.get(blue)+1);							
				}else{
					map.put(blue, 1);
				}				
				
			}
		}
		List<Map.Entry<Integer, Integer>> orderList = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet()); 
		Collections.sort(orderList, new Comparator<Map.Entry<Integer, Integer>>() {  
            @Override  
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {  
                return o2.getValue().compareTo(o1.getValue());  
            }  
        });
		System.out.println(orderList.size());
		int i =0;
		for (Entry<Integer, Integer> entry : orderList) {
//			System.out.println(entry.getKey()+" : "+entry.getValue()+" 次;");
			if(i<5){
				blues.add(entry.getKey());
				i++;
			}
				
		}
		return blues;
	}
	
	public static Set<Integer> notIn(int pre,int sur, int size){
		List<ChartBall> preList = dao.query(ChartBall.class, Cnd.orderBy().desc("term"),dao.createPager(pre,size));
		List<ChartBall> surList = dao.query(ChartBall.class, Cnd.orderBy().desc("term"),dao.createPager(sur,size));
		Set<Integer> preSet = new HashSet<>();
		Set<Integer> surSet = new HashSet<>();
		for (ChartBall chartBall : preList) {
			preSet.addAll(chartBall.getRedBall());
		}
		for (ChartBall chartBall : surList) {
			surSet.addAll(chartBall.getRedBall());
		}
//		sset.retainAll(pset);
		surSet.removeAll(preSet);
		return surSet;
	}
	
	public static int random(int[] array){			
		int num = 0;
		while(num==0) {
			num = makeReds(1,R.random(7,30)).get(R.random(0,4));
			if(ArrayUtils.contains(array, num) || old.contains(num)) {
				num = 0;
			}
		}
		old.add(num);
		return num;
	}
	
	public static List<ChartBall> randomBalls(Set<Integer> redSet,int count){
		List<ChartBall> chartBalls = new ArrayList<>();
		while(count--!=0){
			List<Integer> redList = Lang.collection2list(redSet);
			List<Integer> reds = new ArrayList<>(); 
			Set<Integer> old = new HashSet<>();
			int i=6;
			while(i--!=0){
				int red=0;				
				while(red==0 || old.contains(red)|| condition(old,red) ){
					red = redList.get( R.random(0,redList.size()-1) );				
				}
				old.add(red);
				reds.add(red);			
			}
			int blue = R.random(1,16);
			ChartBall chartBall = new ChartBall(reds,blue);
			chartBalls.add(chartBall);
		}
		return chartBalls;
	}
	
	public static List<ChartBall> randomBalls(Set<Integer> redSet,Set<Integer> blueSet, int count){
		List<ChartBall> chartBalls = new ArrayList<>();
		while(count--!=0){
			List<Integer> redList = Lang.collection2list(redSet);
			List<Integer> reds = new ArrayList<>(); 
			Set<Integer> old = new HashSet<>();
			int i=6;
			while(i--!=0){
				int red=0;				
				while(red==0 || old.contains(red)|| condition(old,red) ){
					red = redList.get( R.random(0,redList.size()-1) );				
				}
				old.add(red);
				reds.add(red);			
			}
			int blue = R.random(1,16);
			if(blueSet!=null){
				List<Integer> blueList = Lang.collection2list(blueSet);
				blue = blueList.get( R.random(0,blueList.size()-1) );
			}
			ChartBall chartBall = new ChartBall(reds,blue);
			chartBalls.add(chartBall);
		}
		return chartBalls;
	}

	public static boolean condition(Set<Integer> old,int red){
		boolean flag = false;
		if(old.size()<=1) return false;
		if(old.contains(red+1) &&old.contains(red+2)) flag = true;
		if(old.contains(red-1) &&old.contains(red-2)) flag = true;
		int sum =0;
		for (Integer i : old) {
			sum+=i;
		}
		if((sum+red)/(old.size()+1)<5) flag = true;
		if((sum+red)/(old.size()+1)>28) flag = true;
		
		return flag;
	}
	private static Set<Integer> old = new HashSet<>();
	public static void main(String[] args) {
//		makeReds(8);
//		System.out.println("");
//		makeBlue(100);
		List<ChartBall> chartBalls = new ArrayList<>();
		int[] array = {5, 8, 17, 19, 21};
		int[] array1210 = {2, 11, 24, 28};
//		makePreset(, 5)
		chartBalls.addAll(makePreset(array, 5));
		chartBalls.addAll(makePreset(array1210, 5));
		redSet.removeAll(Lang.array2list(array, Integer.class));
		redSet.removeAll(Lang.array2list(array1210, Integer.class));
		chartBalls.addAll(randomBalls(redSet,10));		
		chartBalls.addAll(makeHighRate(10,50,10));
		redSet.addAll(Lang.array2list(array, Integer.class));
		redSet.addAll(Lang.array2list(array1210, Integer.class));
		
		Set<Integer> blueOldSet = new HashSet<>();
		for (ChartBall chartBall : chartBalls) {
			blueOldSet.add(chartBall.getBlueBall());
		}
		if (blueOldSet.size()<16){
			int i = 16-blueOldSet.size();
			chartBalls.addAll(randomBalls(redSet,blueSet,10-i));
			blueSet.removeAll(blueOldSet);
			for (Integer blue : blueSet) {
				Set<Integer> set = new HashSet<Integer>();
				set.add(blue);
				chartBalls.addAll(randomBalls(redSet,set,1));
			}
		}else{
			chartBalls.addAll(randomBalls(redSet,blueSet,10));			
		}
		printChartBalls(chartBalls);
	
//		System.out.println(notIn(2, 3, 7));
	}
	
	public static List<ChartBall> makeHighRate(int from,int to,int count){
		List<ChartBall> chartBalls = new ArrayList<>();
		while(count--!=0){
			chartBalls.add(new ChartBall( makeReds(1, R.random(from,to)),makeBlue(1,R.random(from,to)).get(R.random(1,3)) ) );
		}
		return chartBalls;
	}
	
	public static List<ChartBall> makePreset(int[] array,int count){
		List<ChartBall> chartBalls = new ArrayList<>();
		int less = 6 - array.length;
		while(count--!=0){
			List<Integer> reds = Lang.array2list(array, Integer.class);
			for (int i = 0; i < less; i++) {
				reds.add(random(array));
			}
			int blue = R.random(1,16);
			ChartBall chartBall = new ChartBall(reds,blue);
			chartBalls.add(chartBall);
		}
		return chartBalls;
	}
	
	public static void printChartBalls(List<ChartBall> chartBalls){
		for (int i=0;i<chartBalls.size();i++) {
			ChartBall chartBall = chartBalls.get(i);
			List<Integer> reds = chartBall.getRedBall();
			Collections.sort(reds, new Comparator<Integer>() {  
	            @Override  
	            public int compare(Integer o1, Integer o2) {  
	                return o1.compareTo(o2);  
	            }  
	        });
			System.out.println("");
			int b=i+1;
			String bStr = b<10?"   "+b:""+b;
			System.out.print("第"+bStr+"注 : ");
			for (int red : reds) {
				String redStr = red<10?"0"+red:""+red;
				System.out.print(redStr+",");
			}
			
			String blueStr = chartBall.getBlueBall()<10?"0"+chartBall.getBlueBall():""+chartBall.getBlueBall();
			System.out.print("    "+blueStr+"");
			if ((i+1)%5==0) {
				System.out.println("");
			}
		}
	}
	
}
