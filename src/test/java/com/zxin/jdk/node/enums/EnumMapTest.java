package com.zxin.jdk.node.enums;
import java.util.*;  
  
 
public class EnumMapTest  
{  
	enum Season  
	{  
	    SPRING,SUMMER,FALL,WINTER  
	} 
	
    public static void main(String[] args)  
    {  
        // 创建EnumMap对象，该EnumMap的所有key都是Season枚举类的枚举值  
        EnumMap<Season,String> enumMap = new EnumMap<>(Season.class);  
        enumMap.put(Season.SUMMER , "小荷才露尖尖角");  
        enumMap.put(Season.SPRING , "满园春色关不住");  
        System.out.println(enumMap);  
    }  
} 