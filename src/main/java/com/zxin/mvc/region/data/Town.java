package com.zxin.mvc.region.data;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_geo_town")
public class Town {
	
	@Id(auto=false)
	private long adcode;
	
	@Column
	private String name;
	
	@Column
	private String level;
	
	@Column
	private String type;
	
	@Column
	private long padcode;

	public long getAdcode() {
		return adcode;
	}

	public void setAdcode(long adcode) {
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

	public long getPadcode() {
		return padcode;
	}

	public void setPadcode(long padcode) {
		this.padcode = padcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
