package com.tec02.model.impl;

import javax.enterprise.inject.Default;

import com.tec02.model.AbstactMoldel;
import com.tec02.model.IRoleModel;
import com.tec02.model.IUserModel;

@Default
public class UserModel extends AbstactMoldel<IUserModel> implements IUserModel{
	private String username;
	private String userpass;
	private boolean user_status;
	private IRoleModel roleModel;
	
	public IRoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(IRoleModel roleModel) {
		this.roleModel = roleModel;
	}
	
	public boolean isUser_status() {
		return user_status;
	}

	public void setUser_status(boolean user_status) {
		this.user_status = user_status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}
	
	

}
