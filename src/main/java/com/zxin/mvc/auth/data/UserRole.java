package com.zxin.mvc.auth.data;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

@Table("t_sys_user_role")
@Comment("系统用户角色关系表")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = 4887700688215382068L;

	@Column("user_id")
	protected Long userId;
	
	@Column("role_id")
	protected Long roleId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
