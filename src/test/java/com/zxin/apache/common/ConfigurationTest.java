package com.zxin.apache.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationTest {

	private static Logger logger = LoggerFactory.getLogger(ConfigurationTest.class);
	
//	public static void testReadXML() {  
//        try{  
//            XMLConfiguration config = new XMLConfiguration("test.xml");  
//            //对于单独的元素，可以直接通过元素名称获取值  
//            String str = config.getString("boy");  
//            System.out.println("boy:\t"+str);  
//              
//            //对于循环出现的嵌套元素，可以通过父元素.子元素来获取集合值  
//            List<String> names = config.getList("student.name");  
//            System.out.println("student.name:\t"+Arrays.toString(names.toArray()));  
//              
//            //对于单独的元素包含的值有多个的话如：a,b,c,d 可以通过获取集合  
//            List<String> titles = config.getList("title");  
//            System.out.println("title:\t"+Arrays.toString(titles.toArray()));  
//              
//            //对于标签元素的属性，可以通过 标签[@属性名]这样的方式来获取  
//            String size = config.getString("ball[@size]");  
//            System.out.println("ball[@size]:\t"+size);  
//              
//            //对于嵌套标签的话，想获得某一项的话可以通过 标签名(索引名)这样的方式来获取  
//            String id = config.getString("student(1)[@id]");  
//            System.out.println("student(1)[@id]:\t"+id);  
//              
//            //对于标签里面的属性名称可以这么取  
//            String go = config.getString("student(0).name[@go]");  
//            System.out.println("student(0).name[@go]:\t"+go);  
//              
//            //对于标签里面的属性名称还可以这么取  
//            go = config.getString("student.name(0)[@go]");  
//            System.out.println("student.name(0)[@go]:\t"+go);  
//              
//            /** 依次输出结果如下： 
//                boy:    tom 
//                student.name:   [lily, lucy] 
//                title:  [abc, cbc, bbc, bbs] 
//                ball[@size]:    20 
//                student(1)[@id]:    2 
//                student(0).name[@go]:   common1 
//                student.name(0)[@go]:   common1 
//             */  
//                      
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//	}
//	
//    public static void testReadProperties(){  
//        //注意路径默认指向的是classpath的根目录     
//        Configuration config;  
//        try {  
//            config = new PropertiesConfiguration("test.properties");  
//            //对于一般属性直接获取就行  
//            String ip=config.getString("ip");     
//            int port=config.getInt("port");     
//              
//            //这种根据上面属性获取而来的属性也是直接获取  
//            String title=config.getString("application.title");     
//            System.out.println(ip+"\n"+port+"\n"+title);     
//              
//            //再举个Configuration的比较实用的方法吧,在读取配置文件的时候有可能这个键值对应的值为空，那么在下面这个方法中     
//            //你就可以为它设置默认值。比如下面这个例子就会在config.properties这个文件中找id的值，如果文件中没有配置id，就会给id设置值为123     
//            //这样就保证了java的包装类不会返回空值。虽然功能很简单，但是很方便很实用。     
//            String id=config.getString("id", "defaultid");   
//            System.out.println(id);  
//              
//            //如果在properties 文件中有如下属性keys=cn,com,org,uk,edu,jp,hk，这样的类似一个值含有多个元素值     
//            //那么下面两种方法都可以  
//            String[] keys1=config.getStringArray("keys");   
//            System.out.println(Arrays.toString(keys1));  
//            List keys2=config.getList("keys");    
//            System.out.println(Arrays.toString(keys2.toArray()));  
//              
//            //接下来这两步加上的原因是，默认分割符号是逗号，而在配置文件中con的值中含有多个短横线分隔符，所以要重新设置分隔符读出来喽  
//            AbstractConfiguration.setDefaultListDelimiter('-');  
//            config = new PropertiesConfiguration("test.properties");  
//            List cons=config.getList("con");    
//            System.out.println(Arrays.toString(cons.toArray()));  
//            /** 
//             *  
//             *  依次输出结果如下 
//             *  127.0.0.1 
//             *  8080 
//             *  Killer App 1.6.2 
//             *  defaultid 
//             *  [cn, com, org, uk, edu, jp, hk] 
//             *  [cn, com, org, uk, edu, jp, hk] 
//             *  [cn, com, org, uk, edu, jp, hk] 
//             */  
//        } catch (ConfigurationException e) {  
//            e.printStackTrace();  
//        }     
//          
//    }  
//	
//	public static void main(String[] args) {
////		testReadProperties();
//		testReadXML();
////		try {
////			
////			PropertiesConfiguration propConfig = new PropertiesConfiguration("test.properties");
////			propConfig.setProperty("zz", "张震");
////			propConfig.save();
//////			double score = propConfig.getDouble("port");
//////			int port = propConfig.getInt("port");
//////			byte b = propConfig.getByte("port");
////			System.out.println(AbstractConfiguration.getDefaultListDelimiter()+propConfig.getString("zx")+propConfig.getEncoding());
////			
////			
////		} catch (Exception e) {	//必须最低级别异常,否则转换异常打不出来
////			logger.debug("",e);
////		} 
//	}
	
	
}

