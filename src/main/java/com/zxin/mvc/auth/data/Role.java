package com.zxin.mvc.auth.data;

import java.io.Serializable;
import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.EL;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Many;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

@Table("t_sys_role")
@Comment("系统角色表")
public class Role implements Serializable {

	private static final long serialVersionUID = 5166877752117325519L;

	@Id(auto = false)
	@Column("role_id")
	@Prev(els = @EL("autoIncrement(view.tableName)"))
	@Comment("角色标识")
	protected Long roleId;
	
	@Name
	@Column("role_name")
	@Comment("角色名称")
	@ColDefine(width = 64)
	protected String roleName;
	
	@Column
	@Comment("角色描述")
	protected String description;

	@Column
	@Comment("角色状态")
	protected Integer status;
	
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
	
	@Many(target=RolePermission.class, field="roleId")
	protected List<RolePermission> permissions;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<RolePermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<RolePermission> permissions) {
		this.permissions = permissions;
	}
}
