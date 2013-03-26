package com.tavenli.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_app_user_role")
public class UserRoleEntity {

	private long roleId;

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
	
	
}
