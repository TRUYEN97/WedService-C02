package com.tec02.model.user;

import com.tec02.model.IModel;

public interface IUserModel extends IModel, Cloneable{
	
	boolean isUser_status();

	void setUser_status(boolean user_status);

	String getUsername();

	void setUsername(String username);
	
	Long getRole_id();
	
	void setRole_id(Long role_id);

	String getUserpass();

	void setUserpass(String userpass);
}
