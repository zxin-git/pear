package com.zxin.mvc.dice.data;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_lottery")
public class Lottery {
	@Id
	private int term;
	private Type type;
	@Column
	@ColDefine(type = ColType.TIMESTAMP)
	private Date date;
	private int[] values;
	
	
	public Lottery() {
		super();
	}
	public Lottery(Type type, Date date, int term, int[] values) {
		super();
		this.type = type;
		this.date = date;
		this.term = term;
		this.values = values;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	public int[] getValues() {
		return values;
	}
	public void setValues(int[] values) {
		this.values = values;
	}
}
