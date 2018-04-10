package com.zxin.mvc.auth.data;

import java.io.Serializable;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Table;

@Table("t_sys_role_permission")
@Comment("系统角色权限关系表")
public class RolePermission implements Serializable {

	private static final long serialVersionUID = -845374275579199861L;

	@Column("role_id")
	@Comment("角色标识")
	protected Long roleId;
	
	@Column("permission_code")
	@ColDefine(width = 64)
	@Comment("权限字符串")
	protected String permissionCode;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

}
