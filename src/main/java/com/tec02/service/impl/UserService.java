package com.tec02.service.impl;

import javax.inject.Inject;

import com.tec02.dao.IUserDAO;
import com.tec02.model.IUserModel;
import com.tec02.service.IUserService;


public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
