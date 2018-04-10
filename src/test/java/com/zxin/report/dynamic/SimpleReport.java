package com.zxin.report.dynamic;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.chart.BarChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;



public class SimpleReport {
	public static void main(String[] args) {
//		CrosstabBuilder
//	    getData();
		JasperReportBuilder reportBuilder = report().setTemplate(Template.REPORT_TEMPLATE);
//	    JasperReportBuilder report = DynamicReports.report();// 创建空报表
	    // 样式
	    StyleBuilder boldStl = DynamicReports.stl.style().bold();
	    StyleBuilder boldCenteredStl = DynamicReports.stl.style(boldStl)
	            .setHorizontalAlignment(HorizontalAlignment.CENTER);
	    StyleBuilder titleStl = DynamicReports.stl.style(boldCenteredStl)
	            .setFontSize(16);
	    StyleBuilder columnTitleStl = DynamicReports.stl.style(boldCenteredStl)
	            .setBorder(DynamicReports.stl.pen1Point())
	            .setBackgroundColor(Color.LIGHT_GRAY);

	    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("id", 123);
	    map.put("code", "185");
	    map.put("service", "中国移动");
	    map.put("province", "重庆");
	    map.put("city", "重庆");
	    map.put("type", "apple");
	    map.put("name", "测试");
	    list.add(map);

	    TextColumnBuilder<Integer> idColumn = col.column("ID", "id", DataTypes.integerType()).setHorizontalAlignment(HorizontalAlignment.CENTER);// 列
	    TextColumnBuilder<String> codeColumn = col.column("手机号段", "code", DataTypes.stringType());
	    TextColumnBuilder<String> serviceColumn =col.column("运营商", "service", DataTypes.stringType());
	    TextColumnBuilder<String> proColumn =col.column("省份", "province", DataTypes.stringType());
	    TextColumnBuilder<String> cityColumn =col.column("城市", "city", DataTypes.stringType());
	    TextColumnBuilder<String> typeColumn =col.column("品牌", "type", DataTypes.stringType());
	    
	    BarChartBuilder itemChart = cht.barChart().setTitle("aaaaaaaaaaaaaaa")
                .setCategory(codeColumn)
                .addSerie(
                   cht.serie(idColumn));
	    
	    reportBuilder.columns(idColumn,codeColumn,serviceColumn,proColumn,cityColumn,typeColumn)
	            .setColumnTitleStyle(columnTitleStl)
	            .setHighlightDetailEvenRows(true)
	            .title(Components.text("手机号段").setStyle(titleStl))
	            // 标题
	            .pageFooter(Components.pageXofY().setStyle(boldCenteredStl))
	            .summary(itemChart)
	            .setDataSource(list);// 数据源
	    	
	    
	    	

	   
	    try {
	        // 显示报表
	    	reportBuilder.show();
	        //report.toXls(new FileOutputStream("F:/test.xls"));
	        // 生成PDF文件
	        // report.toPdf(new FileOutputStream("F:/test.pdf"));
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}
