package com.tec02.model.role;

import com.tec02.model.IModel;

public interface IRoleModel extends IModel, Cloneable{
	
	long getCode();

	void setCode(long code);

	String getName();

	void setName(String name);
	
}
