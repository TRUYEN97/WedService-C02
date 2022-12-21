package com.tec02.model.impl.user;

import com.tec02.model.AbstactMoldel;
import com.tec02.model.user.IUserModel;


public class UserModel extends AbstactMoldel implements IUserModel{
	private String username;
	private String userpass;
	private Long role_id;
	private boolean user_status;
	
	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
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
