package com.tec02.mapper.impl;

import java.sql.ResultSet;

import javax.inject.Inject;

import com.tec02.mapper.AbstactUserOwnerMapper;
import com.tec02.mapper.IRowMapper;
import com.tec02.model.IRoleModel;

public class RoleMapper extends AbstactUserOwnerMapper<IRoleModel> implements IRowMapper<IRoleModel> {

	@Inject
	private IRoleModel roleModel;
	

	@Override
	public IRoleModel mapper(ResultSet resultSet) {
		try {
			IRoleModel roleModel = (IRoleModel) this.roleModel.clone();
			defaultMapper(roleModel, resultSet);
			roleModel.setCode(resultSet.getLong("role_code"));
			roleModel.setName(resultSet.getString("rolename"));
			return roleModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
