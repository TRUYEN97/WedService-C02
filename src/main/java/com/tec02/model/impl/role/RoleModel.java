package com.tec02.model.impl.role;

import com.tec02.model.AbstactMoldel;
import com.tec02.model.role.IRoleModel;


public class RoleModel extends AbstactMoldel implements IRoleModel {
	private long code;
	private String name;

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
