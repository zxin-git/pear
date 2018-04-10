/**
 * Copyright: 版权所有 ( c ) 北京启明星辰信息技术股份有限公司 2011。保留所有权利。
 * Author: zhuyan
 * Created: 2011-6-20
 */
package com.zxin.report.dynamic;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.List;


import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import net.sf.dynamicreports.report.builder.chart.AbstractChartBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.style.FontBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

/**
 * 报表样式模板
 */
public class Template
{
	//报表基本样式
	public static final StyleBuilder REPORT_STYLE =
		stl.style().setFontName("宋体").setFontSize(12).setForegroundColor(new Color(51, 51, 51))
		.setBold(false).setItalic(false).setStrikeThrough(false).setUnderline(false)
		.setVerticalAlignment(VerticalAlignment.MIDDLE);
	
	public static final StyleBuilder REPORT_CHART_STYLE = stl.style().setFontName("宋体").setFontSize(12);
	
	public static final FontBuilder REPORT_CHART_TITLE_STYLE = stl.font().setFontName("宋体").setFontSize(14);
	//页眉页脚样式
	public static final StyleBuilder PAGE_HEADER_FOOTER_STYLE = stl.style(REPORT_STYLE).setFontSize(10);
	//时间范围/生成时间行样式
	public static final StyleBuilder TIME_ROW_STYLE = stl.style(REPORT_STYLE).setTopPadding(5).setLeftPadding(30).setHorizontalAlignment(HorizontalAlignment.LEFT);
	
	//加粗
	public static final StyleBuilder BOLD_STYLE = stl.style(REPORT_STYLE).bold();
	//加粗居中
	public static final StyleBuilder BOLD_CENTER_STYLE = stl.style(BOLD_STYLE).setHorizontalAlignment(HorizontalAlignment.CENTER);
	//数据列表标题样式
	public static final StyleBuilder COLUMN_HEADER_STYLE = stl.style(BOLD_CENTER_STYLE).setPadding(2)
			.setBorder(stl.pen1Point().setLineColor(new Color(128, 128, 128))).setBackgroundColor(Color.LIGHT_GRAY);
	//数据列表样式
	public static final StyleBuilder COLUMN_STYLE = stl.style(REPORT_STYLE).setPadding(2)
			.setLeftBorder(stl.pen1Point().setLineColor(new Color(128, 128, 128)))
			.setRightBorder(stl.pen1Point().setLineColor(new Color(128, 128, 128)))
			.setBottomBorder(stl.pen1Point().setLineColor(new Color(128, 128, 128)))
			.setVerticalAlignment(VerticalAlignment.MIDDLE).setLeftPadding(10);
	
	public static final StyleBuilder CROSSTAB_CELL_STYLE = stl.style(COLUMN_STYLE).setBorder(stl.pen1Point());
	
	public static final StyleBuilder COLUMN_CROSSTAB_TITLE_STYLE = stl.style(COLUMN_HEADER_STYLE);
	//主标题样式
	public static final StyleBuilder TITLE_STYLE = stl.style(BOLD_CENTER_STYLE).setFontSize(22).setTopPadding(5);
	//副标题样式
	public static final StyleBuilder SUBTITLE_STYLE = stl.style(BOLD_CENTER_STYLE).setFontSize(16).setTopPadding(5);
	
	public static final StyleBuilder REPORT_EXT_DESCRIPTION_STYLE = stl.style(REPORT_STYLE).setLeftPadding(10).setRightPadding(10).setBottomPadding(30);
	
	public static final StyleBuilder REPORTS_SUMMARY_CONTENTS_DESCRIPTION = stl.style(REPORT_STYLE).setFontSize(16);
	
	//时间格式
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//统计图宽度
	public static final int STAT_CHART_WIDTH = 400;
	//统计图高度
	public static final int STAT_CHART_HEIGHT = 300;
	//页眉页脚使用的图标的高度
	public static final int LOGO_HEIGHT = 16;
	//主标题高度
	public static final int TITLE_HEIGHT = 40;
	//副标题高度
	public static final int SUBTITLE_HEIGHT = 30;
	//时间行高度
	public static final int TIME_ROW_HEIGHT = 30;
	//栏与栏之间的间距
	public static final int BAND_SPACE = 50;
	
	public static final int COLUMN_HEADER_HEIGHT = 40;
	
	//报表模板
	public static ReportTemplateBuilder REPORT_TEMPLATE = null;
	
	static
	{
		REPORT_TEMPLATE = template()
						.setPageMargin(margin().setBottom(20).setTop(20).setLeft(20).setRight(20))
						.setTextStyle(REPORT_STYLE)
						.setColumnTitleStyle(COLUMN_HEADER_STYLE)
						.setColumnStyle(COLUMN_STYLE)
						.setPageColumnSpace(2)
						.highlightDetailEvenRows()
						.setCrosstabCellStyle(CROSSTAB_CELL_STYLE)
		                .setCrosstabGroupStyle(CROSSTAB_CELL_STYLE)
						.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);
	}
	
	
	public static HorizontalListBuilder createPageHeaderComponent(HorizontalListBuilder pageHeader)
	{
		return cmp.horizontalList()
		        .add(pageHeader.setStyle(PAGE_HEADER_FOOTER_STYLE))
		        .newRow()
		        .add(cmp.line())
		        .newRow()
		        .add(cmp.filler().setFixedHeight(BAND_SPACE));
	}
	
	public static HorizontalListBuilder createPageFooterComponent(HorizontalListBuilder pageFooter)
	{
		return cmp.horizontalList()
		        .add(cmp.filler().setFixedHeight(BAND_SPACE))
		        .newRow()
		        .add(cmp.line())
		        .newRow()
		        .add(pageFooter.setStyle(PAGE_HEADER_FOOTER_STYLE));
	}
	
	public static VerticalListBuilder createTitleComponent(HorizontalListBuilder pageHeaderInTitle, String ext,
			String title, String subTitle, String description, String timeCreateRow, String timeRow, AbstractChartBuilder<?> statChart)
	{
		VerticalListBuilder result = cmp.verticalList();
		
		if(pageHeaderInTitle != null)
			result.add(pageHeaderInTitle);
		
		if (ext != null && ext.trim().length() > 0) {
			result.add(cmp.text(ext)).setStyle(REPORT_EXT_DESCRIPTION_STYLE);
		}
		
		result.add(cmp.text(exp.jasperSyntaxText(title)).setStyle(TITLE_STYLE).setFixedHeight(TITLE_HEIGHT));
		
		if(subTitle != null && subTitle.trim().length() > 0)
			result.add(cmp.text(exp.jasperSyntaxText(subTitle)).setStyle(SUBTITLE_STYLE).setFixedHeight(SUBTITLE_HEIGHT));
		
		if(timeCreateRow != null && timeCreateRow.trim().length() > 0) {
			result.add(cmp.text(exp.jasperSyntaxText(timeCreateRow)).setStyle(TIME_ROW_STYLE).setFixedHeight(TIME_ROW_HEIGHT));
		}
		
		if(timeRow != null && timeRow.trim().length() > 0) {
			result.add(cmp.text(exp.jasperSyntaxText(timeRow)).setStyle(TIME_ROW_STYLE).setFixedHeight(TIME_ROW_HEIGHT));
		}
		
		if(description != null && description.trim().length() > 0)
			result.add(cmp.text(exp.jasperSyntaxText(description)));
		
		if(statChart != null) {
			result.add(cmp.filler().setFixedHeight(BAND_SPACE));
			result.add(statChart.setDimension(STAT_CHART_WIDTH, STAT_CHART_HEIGHT));
		}

		result.add(cmp.filler().setFixedHeight(BAND_SPACE));
		
		return result;
	}
	
//	public static VerticalListBuilder createReportsTitleComponent(HorizontalListBuilder pageHeaderInTitle, String ext,
//			String title, String subTitle, String description, String timeRow, AbstractChartBuilder<?> statChart, 
//			ReportsSummaryContents reportsSummaryContents)
//	{
//		VerticalListBuilder result = cmp.verticalList();
//		
//		if(pageHeaderInTitle != null)
//			result.add(pageHeaderInTitle);
//		
//		if (ext != null && ext.trim().length() > 0) {
//			result.add(cmp.text(ext)).setStyle(REPORT_EXT_DESCRIPTION_STYLE);
//		}
//		
//		result.add(cmp.text(exp.jasperSyntaxText(title)).setStyle(TITLE_STYLE).setFixedHeight(TITLE_HEIGHT));
//		
//		if(subTitle != null && subTitle.trim().length() > 0)
//			result.add(cmp.text(exp.jasperSyntaxText(subTitle)).setStyle(SUBTITLE_STYLE).setFixedHeight(SUBTITLE_HEIGHT));
//		
//		if(timeRow != null && timeRow.trim().length() > 0)
//			result.add(cmp.text(exp.jasperSyntaxText(timeRow)).setStyle(TIME_ROW_STYLE).setFixedHeight(TIME_ROW_HEIGHT));
//		
//		List<String> reportsSummaryContentsList = reportsSummaryContents.getReportsSummaryContents();
//		if(description != null && description.trim().length() > 0) {
//			if(reportsSummaryContentsList != null && !reportsSummaryContentsList.isEmpty()) {
//				result.add(cmp.text(exp.jasperSyntaxText(description))).setStyle(REPORTS_SUMMARY_CONTENTS_DESCRIPTION);
//			} else
//				result.add(cmp.text(exp.jasperSyntaxText(description)));
//		}
//		
//		if(reportsSummaryContentsList != null && !reportsSummaryContentsList.isEmpty()) {
//			result.add(cmp.horizontalList().newRow().add(cmp.line()));
//			for(int i = 0; i < reportsSummaryContentsList.size(); i ++) {
//				result.add(cmp.text(exp.jasperSyntaxText(String.valueOf(i + 1) + "、 " + reportsSummaryContentsList.get(i)))).setStyle(REPORT_STYLE);
//				result.add(cmp.horizontalList().newRow().add(cmp.line()));
//			}
//		}
//		
//		if(statChart != null)
//			result.add(statChart.setDimension(STAT_CHART_WIDTH, STAT_CHART_HEIGHT));
//		
//		result.add(cmp.filler().setFixedHeight(BAND_SPACE));
//		
//		return result;
//	}
	
	public static float getCrosstabCellBorderWidth() {
		float crosstabCellBorderWidth = CROSSTAB_CELL_STYLE.getStyle().getBorder().getBottomPen().getLineWidth();
		return crosstabCellBorderWidth;
	}

}
