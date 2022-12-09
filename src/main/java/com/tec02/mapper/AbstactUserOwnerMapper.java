package com.tec02.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;

import com.tec02.model.IModel;
import com.tec02.model.IUserModel;

public abstract class AbstactUserOwnerMapper<T extends IModel<IUserModel>> implements IRowMapper<T> {
	
	@Inject
	private IUserModel userModel;
	
	protected AbstactUserOwnerMapper() {};
	
	protected void defaultMapper(T model, ResultSet resultSet) {
		if(model == null || resultSet == null) {
			return;
		}
		
		try {
			IUserModel userModel = (IUserModel) this.userModel.clone();
			model.setId(resultSet.getLong("id"));
			userModel.setId(resultSet.getLong("creationby"));
			userModel.setUserName(resultSet.getString("username"));
			model.setCreator(userModel);
			model.setCreationTime(resultSet.getTimestamp("creationtime"));
		} catch (SQLException | CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
