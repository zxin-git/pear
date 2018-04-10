package com.zxin.mvc;

import org.beetl.ext.nutz.BeetlViewMaker;
import org.nutz.integration.shiro.ShiroSessionProvider;
import org.nutz.mvc.annotation.ChainBy;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.annotation.SessionBy;
import org.nutz.mvc.annotation.SetupBy;
import org.nutz.mvc.annotation.Views;
import org.nutz.mvc.ioc.provider.ComboIocProvider;
import org.nutz.plugins.view.pdf.PdfViewMaker;  
   
  
/** 
 * 主模块 
 * 
 * @author zxin 
 * 
 */  
@Modules(scanPackage = true,packages = "com.zxin")  
@IocBy(type = ComboIocProvider.class, args = {  
    "*org.nutz.ioc.loader.json.JsonLoader", "dao.js", "*com.zxin.mvc.core.PathXmlIocLoader" , "../conf",
//		"*org.nutz.ioc.loader.xml.XmlIocLoader","properties/daoXml.xml",
    "*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.zxin.mvc",
    "*quartz",
    "*async", // 建议一起启用,否则@SLog(async=true)会以同步方式插入,而非异步.
	"*slog", // 启用即可,不需要其他配置. 会自动建表的.
	"*sigar"
    })  //最好全称..报错 --扫描限于本或子目录
//@IocBy(type=ComboIocProvider.class,args={"*js","dao.js","*org.nutz.loader.annotation.AnnotationIocLoader","com.mynutz"})
@Views({BeetlViewMaker.class, PdfViewMaker.class})
@SetupBy(value=MainSetup.class)
@ChainBy(args="mvc/mvc-chain.json")
@SessionBy(ShiroSessionProvider.class)
public class MainModule {

}
