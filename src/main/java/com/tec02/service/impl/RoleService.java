package com.tec02.service.impl;

import javax.inject.Inject;

import com.tec02.dao.IRoleDAO;
import com.tec02.model.role.IRoleModel;
import com.tec02.service.IRoleService;

public class RoleService implements IRoleService {

	@Inject
	private IRoleDAO roleDAO;
	
	@Override
	public IRoleModel getByID(long id) {
		return this.roleDAO.findOne(id);
	}

}
