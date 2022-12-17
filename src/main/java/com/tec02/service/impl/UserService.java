package com.tec02.service.impl;

import javax.inject.Inject;

import com.tec02.dao.IUserDAO;
import com.tec02.model.user.IUserModel;
import com.tec02.service.IUserService;


public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Inject
	private IUserModel userModel;
	
	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

	@Override
	public IUserModel findAll(long[] ids) {
		IUserModel userModel;
		try {
			userModel = (IUserModel) this.userModel.clone();
			userModel.setUserModels( userDAO.findAll(ids));
			return userModel;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public IUserModel findAll() {
		IUserModel userModel;
		try {
			userModel = (IUserModel) this.userModel.clone();
			userModel.setUserModels( userDAO.findAll());
			return userModel;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}

}
