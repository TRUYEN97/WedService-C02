package com.tec02.dao;

import java.util.List;

import com.tec02.model.IUserModel;

public interface IUserDAO extends IDAO<IUserModel>{
	
	IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status);
	
	List<IUserModel> findAll();
}