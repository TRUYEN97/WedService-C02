package com.tec02.dao;

import java.util.List;

import com.tec02.model.impl.RoleModel;

public interface IUserRoleDAO extends IDAO<RoleModel> {
	List<RoleModel> findAll();
}
