package util.test;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class OrderTest {
	public static void main(String[] args) {
		Map<Integer, String> treeMap = new TreeMap<Integer, String>(new Comparator<Integer>() {  
		    @Override  
		    public int compare(Integer o1, Integer o2) {  
		        // return o1.compareTo(o2); // 默认：升序排列  
		        return o2.compareTo(o1);  // 降序排列  
		        // return 0;    // 只返回存储的第一个key的值，这里是"ccccc"  
		    }  
		});  
		treeMap.put(1, "ccccc");  
		treeMap.put(2, "aaaaa");  
		treeMap.put(3, "bbbbb");  
		treeMap.put(4, "ddddd");  
		  
		for (Integer key : treeMap.keySet()) {  
		    System.out.println(key+" : "+treeMap.get(key));  
		}  
	}
}
