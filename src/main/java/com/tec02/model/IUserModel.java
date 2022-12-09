package com.tec02.model;

public interface IUserModel extends IModel<IUserModel>, Cloneable{

	IRoleModel getRoleModel();

	void setRoleModel(IRoleModel roleModel);

	boolean isUser_status();

	void setUser_status(boolean user_status);

	String getUserName();

	void setUserName(String userName);

	String getUserPass();

	void setUserPass(String userPass);
}
