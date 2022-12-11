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
	
	protected T defaultMapper(T model, ResultSet resultSet) {
		if(model == null || resultSet == null) {
			return null;
		}
		
		try {
			IUserModel userModel = (IUserModel) this.userModel.clone();
			userModel.setId(resultSet.getLong("creationby"));
			userModel.setUsername(resultSet.getString("username"));
			model.setId(resultSet.getLong("id"));
			model.setCreator(userModel);
			model.setCreationTime(resultSet.getTimestamp("creationtime"));
			return model;
		} catch (SQLException | CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
