package com.tec02.service.impl;

import com.tec02.dao.IUserDAO;
import com.tec02.model.IUserModel;
import com.tec02.service.IUserService;


public class UserService implements IUserService{

	private IUserDAO userDAO;
	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
