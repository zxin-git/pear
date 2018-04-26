package com.zxin.jdk.node.enums;

import java.util.EnumSet;

enum Season  
{  
    SPRING,SUMMER,FALL,WINTER  
}  
public class EnumSetTest  
{  
	
//	EnumSet是一个专为枚举类设计的集合类，EnumSet中所有元素都必须是指定枚举类型的枚举值，该枚举类型在创建EnumSet时显式或隐式地指定。EnumSet的集合元素也是有序的，EnumSet以枚举值在Enum类的定义顺序来决定集合元素的顺序。
//	EnumSet在内部以位向量的形式存储，这种存储形式非常紧凑、高效，因此EnumSet对象占用内存很小，而且运行效率很好。尤其是当进行批量操作（如调用containsAll 和 retainAll方法）时，如果其参数也是EnumSet集合，则该批量操作的执行速度也非常快。
//	EnumSet集合不允许加入null元素。如果试图插入null元素，EnumSet将抛出 NullPointerException异常。如果仅仅只是试图测试是否出现null元素、或删除null元素都不会抛出异常，只是删除操作将返回false，因为没有任何null元素被删除。
    public static void main(String[] args)  
    {  
    	//RegularEnumSet,JumboEnumSet
        // 创建一个EnumSet集合，集合元素就是Season枚举类的全部枚举值  
        EnumSet<Season> es1 = EnumSet.allOf(Season.class);  
        System.out.println(es1); // 输出[SPRING,SUMMER,FALL,WINTER]  
        // 创建一个EnumSet空集合，指定其集合元素是Season类的枚举值。  
        EnumSet<Season> es2 = EnumSet.noneOf(Season.class);  
        System.out.println(es2); // 输出[]  
        // 手动添加两个元素  
        es2.add(Season.WINTER);  
        es2.add(Season.SPRING);  
        System.out.println(es2); // 输出[SPRING,WINTER]  
        // 以指定枚举值创建EnumSet集合  
        EnumSet<Season> es3 = EnumSet.of(Season.SUMMER , Season.WINTER);  
        System.out.println(es3); // 输出[SUMMER,WINTER]  
        EnumSet<Season> es4 = EnumSet.range(Season.SUMMER , Season.WINTER);  
        System.out.println(es4); // 输出[SUMMER,FALL,WINTER]  
        // 新创建的EnumSet集合的元素和es4集合的元素有相同类型，  
        // es5的集合元素 + es4集合元素 = Season枚举类的全部枚举值  
        EnumSet<Season> es5 = EnumSet.complementOf(es4);  
        System.out.println(es5); // 输出[SPRING]  
    }  
}  