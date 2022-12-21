package com.tec02.service;

import java.sql.SQLException;
import java.util.List;

import com.tec02.model.user.IUserModel;

public interface IUserService {

	IUserModel  findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status);

	List<IUserModel> findAll(long[] ids);
	
	List<IUserModel> findAll();

	IUserModel addUser(IUserModel userModel) throws SQLException;

	IUserModel findOne(long id);

	List<IUserModel> searchByName(String value);
}
