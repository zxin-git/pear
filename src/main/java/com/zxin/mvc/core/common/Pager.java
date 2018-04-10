/**
 * Copyright: 版权所有 ( c ) 北京启明星辰信息技术股份有限公司 2011。保留所有权利。
 * Author: Administrator
 * Created: 2011-3-14
 */
package com.zxin.mvc.core.common;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Pattern;

import org.nutz.mvc.annotation.Param;


/**
 * 分页类，用于连接界面与Controller，传递分页信息
 * @author liunan
 *
 */

public class Pager<T>  implements Serializable{
	
	private static Pattern SQL_COMMENT = Pattern.compile("/\\*[^*/]*\\*/");
	
	private static boolean[] ESCAPE_CHARS;
	
	static {
		int size = '>' + 1;
		ESCAPE_CHARS = new boolean[size];
		ESCAPE_CHARS['<'] = true;
		ESCAPE_CHARS['>'] = true;
		ESCAPE_CHARS['&'] = true;
		ESCAPE_CHARS['\''] = true;
		ESCAPE_CHARS['"'] = true;
		ESCAPE_CHARS[';'] = true;
		
		//规避sql盲注问题，将排序字段中的空格和括号全部去掉，如nutz修正issue#527，则可取消
		ESCAPE_CHARS['('] = true;
		ESCAPE_CHARS[')'] = true;
		ESCAPE_CHARS[' '] = true;
	}

	/** 总页数 */
	@Param("total")
	private int total;
	
	/** 当前页码 */
	@Param("page")
	private int page = 1;
	
	/** 每页显示记录数 */
	@Param("rows")
	private int pageSize = 20;

	/** 总记录数 */
	@Param("records")
	private long records;
	
	/** 记录集合 */
	@Param("")
	private List<T> data;
	
	/** 排序字段 */
	@Param("sidx")
	private String sidx;
	
	/**	排序方式 */
	@Param("sord")
	private String sord = "asc";
	
	public Pager() {
	}
	
	public static <K> Pager<K> createNP(Class<K> classOfK) {
		Pager<K> pager = new Pager<K>();
		pager.setPage(-1);
		pager.setPageSize(-1);
		return pager;
	}

	public int getTotal() {
		total = (int) Math.ceil((double) records / pageSize);
		return total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getRecords() {
		return records;
	}

	public void setRecords(long records) {
		this.records = records;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSidx() {
		return sidx;
	}

	public void setSidx(String sidx) {
		if (sidx != null) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < sidx.length(); i++) {
				char c = sidx.charAt(i);
				if (c > ESCAPE_CHARS.length || !ESCAPE_CHARS[c])
					sb.append(c);
			}
			this.sidx = SQL_COMMENT.matcher(sb).replaceAll("");
		}
	}

	public String getSord() {
		return sord;
	}

	public void setSord(String sord) {
		if ("asc".equalsIgnoreCase(sord) || "desc".equalsIgnoreCase(sord))
			this.sord = sord;
		else
			this.sord = "asc";
	}
}
