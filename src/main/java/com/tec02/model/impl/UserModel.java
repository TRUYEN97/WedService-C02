package com.tec02.model.impl;

import com.tec02.model.AbstactMoldel;
import com.tec02.model.IRoleModel;
import com.tec02.model.IUserModel;

public class UserModel extends AbstactMoldel<IUserModel> implements IUserModel{
	private String userName;
	private String userPass;
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

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	

}
