package com.tec02.mapper.impl;


import java.sql.ResultSet;

import javax.inject.Inject;

import com.tec02.mapper.AbstactUserOwnerMapper;
import com.tec02.mapper.IRowMapper;
import com.tec02.model.IRoleModel;
import com.tec02.model.IUserModel;

public class UserMapper extends AbstactUserOwnerMapper<IUserModel> implements IRowMapper<IUserModel> {

	@Inject
	private IRowMapper<IRoleModel> roleMapper;
	
	@Inject
	private IUserModel userModel;
	
	@Override
	public IUserModel mapper(ResultSet resultSet) {
		if (resultSet == null) {
			return null;
		}
		try {
			IUserModel userModel = (IUserModel) this.userModel.clone();
			userModel.setUserName(resultSet.getString("username"));
			userModel.setUserPass(resultSet.getString("userpass"));
			userModel.setUser_status(resultSet.getBoolean("user_status"));
			userModel.setCreationTime(resultSet.getTimestamp("createtime"));
			userModel.setRoleModel(roleMapper.mapper(resultSet));
			return userModel;
		} catch (Exception e) {
			return null;
		}
	}


}
