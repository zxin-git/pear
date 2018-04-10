package com.zxin.mvc.dice.data;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_chartball")
public class ChartBall {
	@Id(auto=false)
	private int term;
	
	@Column
	private String timeStr;
	
	@Column
	private long time;
	
	@Column
	private List<Integer> redBall;
	
	@Column
	private String redStr; 
	
	@Column
	private int blueBall;

	
	
	public ChartBall() {
		super();
	}
	
	

	public ChartBall(List<Integer> redBall, int blueBall) {
		super();
		this.redBall = redBall;
		this.blueBall = blueBall;
	}



	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
		
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<Integer> getRedBall() {
		return redBall;
	}

	public void setRedBall(List<Integer> redBall) {
		this.redBall = redBall;
	}

	public String getRedStr() {
		return redStr;
	}

	public void setRedStr(String redStr) {
		this.redStr = redStr;
	}

	public int getBlueBall() {
		return blueBall;
	}

	public void setBlueBall(int blueBall) {
		this.blueBall = blueBall;
	}
	
	
}
