package com.tec02.dao;

import java.util.List;

import com.tec02.model.user.IUserModel;

public interface IUserDAO extends IDAO<IUserModel>{
	
	IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status);
	
	List<IUserModel> findAll();

	List<IUserModel> findAll(long[] ids);

	List<IUserModel> search(String value);
}
