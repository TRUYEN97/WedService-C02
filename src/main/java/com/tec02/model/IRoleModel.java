package com.tec02.model;

public interface IRoleModel extends IModel<IUserModel>{
	
	long getCode();

	void setCode(long l);

	String getName();

	void setName(String name);
	
}
