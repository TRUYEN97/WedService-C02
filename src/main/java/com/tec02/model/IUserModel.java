package com.tec02.model;

public interface IUserModel extends IModel<IUserModel>, Cloneable{

	IRoleModel getRoleModel();

	void setRoleModel(IRoleModel roleModel);

	boolean isUser_status();

	void setUser_status(boolean user_status);

	String getUsername();

	void setUsername(String username);

	String getUserpass();

	void setUserpass(String userpass);
}
