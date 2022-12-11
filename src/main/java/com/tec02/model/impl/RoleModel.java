package com.tec02.model.impl;

import javax.enterprise.inject.Default;

import com.tec02.model.AbstactMoldel;
import com.tec02.model.IRoleModel;
import com.tec02.model.IUserModel;

@Default
public class RoleModel extends AbstactMoldel<IUserModel> implements IRoleModel{
	private long code;
	private String name;
	
	public long getCode() {
		return code;
	}
	public void setCode(long l) {
		this.code = l;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
