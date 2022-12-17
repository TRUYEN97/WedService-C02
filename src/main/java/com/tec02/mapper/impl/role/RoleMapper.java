package com.tec02.mapper.impl.role;

import java.sql.ResultSet;

import javax.inject.Inject;

import com.tec02.mapper.AbstactMapper;
import com.tec02.mapper.IRoleMapper;
import com.tec02.model.role.IRoleModel;


public class RoleMapper extends AbstactMapper<IRoleModel> implements IRoleMapper{

	@Inject
	private IRoleModel roleModel;
	
	@Override
	protected IRoleModel createAndMapping(ResultSet resultSet) {
		try {
			IRoleModel roleModel = (IRoleModel) this.roleModel.clone();
			roleModel.setCode(resultSet.getLong("role_code"));
			roleModel.setName(resultSet.getString("rolename"));
			return roleModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
