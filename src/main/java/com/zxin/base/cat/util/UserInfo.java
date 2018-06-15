package com.zxin.base.cat.util;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;


@Table("t_sys_user")
@Comment("系统用户表")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = -4112939888903211823L;

	@Id(auto = false)
	@Column("id")
	@Comment("用户标识")
//	@Prev(els = @EL("autoIncrement(view.tableName)"))
	protected Long userId;
	
	@Column("sharding_id")
	protected Long sId;
	
	@Name
	@Column("user_name")
	@ColDefine(width = 64)
	@Comment("用户名称")
	protected String userName;

	@Column("password")
	@ColDefine(width = 128)
	@Comment("用户密码")
	protected String password;
	
	@Column("real_name")
	@Comment("用户昵称")
	protected String realName;

	@Column
	@Comment("用户邮箱")
	protected String email;

	@Column
	@Comment("用户电话")
	protected String phone;
	
	@Column
	@Comment("用户手机")
	protected String mobile;

	@Column
	@Comment("用户类型0为系统内置用户无法页面删除")
	protected Integer type;

	@Column
	@Comment("用户锁定（0正常1锁定）")
	protected Integer locked;

	@Column
	@Comment("用户描述")
	protected String description;

	@Column
	@Comment("用户状态")
	protected Boolean status;
	
	@Column("login_time")
	@Comment("用户登录时间")
	protected Long loginTime;

	@Column("create_time")
	@Comment("创建时间")
	protected Long createTime;

	@Column("update_time")
	@Comment("修改时间")
	protected Long updateTime;

	@Column
	@Comment("创建用户")
	protected Long creator;

	@Column
	@Comment("修改用户")
	protected Long updater;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Long getUpdater() {
		return updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	public Long getsId() {
		return sId;
	}

	public void setsId(Long sId) {
		this.sId = sId;
	}
	
	
}

