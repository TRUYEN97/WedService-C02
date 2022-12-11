package com.tec02.mapper.impl;


import java.sql.ResultSet;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import com.tec02.mapper.AbstactUserOwnerMapper;
import com.tec02.mapper.IRoleMapper;
import com.tec02.mapper.IUserMapper;
import com.tec02.model.IUserModel;

@Default
public class UserMapper extends AbstactUserOwnerMapper<IUserModel> implements IUserMapper {

	@Inject
	private IRoleMapper roleMapper;
	
	@Inject
	private IUserModel userModel;
	
	@Override
	public IUserModel mapper(ResultSet resultSet) {
		if (resultSet == null) {
			return null;
		}
		try {
			IUserModel userModel = (IUserModel) this.userModel.clone();
			userModel.setUsername(resultSet.getString("username"));
			userModel.setUserpass(resultSet.getString("userpass"));
			userModel.setUser_status(resultSet.getBoolean("user_status"));
			userModel.setRoleModel(roleMapper.mapper(resultSet));
			return defaultMapper(userModel, resultSet);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
