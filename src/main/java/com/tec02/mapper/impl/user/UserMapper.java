package com.tec02.mapper.impl.user;


import java.sql.ResultSet;

import javax.inject.Inject;

import com.tec02.mapper.AbstactMapper;
import com.tec02.mapper.IUserMapper;
import com.tec02.model.user.IUserModel;


public class UserMapper extends AbstactMapper<IUserModel> implements IUserMapper {

	@Inject
	private IUserModel userModel;

	@Override
	protected IUserModel createAndMapping(ResultSet resultSet) {
		if (resultSet == null) {
			return null;
		}
		try {
			IUserModel userModel = (IUserModel) this.userModel.clone();
			userModel.setUsername(resultSet.getString("username"));
			userModel.setUserpass(resultSet.getString("userpass"));
			userModel.setRole_id(resultSet.getLong("role_id"));
			userModel.setUser_status(resultSet.getBoolean("user_status"));
			return userModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
