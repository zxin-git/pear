package com.zxin.mvc.region.data;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_geo_region")
public class Region {
	
	@Id(auto=false)
	private int adcode;
	
	@Column
	private String name;
	
	@Column
	private String level;
	
	@Column
	private int padcode;

	public int getAdcode() {
		return adcode;
	}

	public void setAdcode(int adcode) {
		this.adcode = adcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getPadcode() {
		return padcode;
	}

	public void setPadcode(int padcode) {
		this.padcode = padcode;
	}

	
	
	
}
