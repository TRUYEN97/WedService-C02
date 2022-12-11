package com.tec02.mapper.impl;

import java.sql.ResultSet;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.tec02.mapper.AbstactUserOwnerMapper;
import com.tec02.mapper.IRoleMapper;
import com.tec02.model.IRoleModel;

@Default
public class RoleMapper extends AbstactUserOwnerMapper<IRoleModel> implements IRoleMapper{

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
