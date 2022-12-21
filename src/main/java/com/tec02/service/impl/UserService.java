package com.tec02.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.tec02.dao.IUserDAO;
import com.tec02.model.user.IUserModel;
import com.tec02.service.IUserService;


public class UserService implements IUserService{

	@Inject
	private IUserDAO userDAO;
	
	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

	@Override
	public List<IUserModel> findAll(long[] ids) {
		return userDAO.findAll(ids);
	}

	@Override
	public List<IUserModel> findAll() {
		return userDAO.findAll();
	}

	@Override
	public IUserModel addUser(IUserModel userModel) throws SQLException {
		long id = this.userDAO.save(userModel);
		if(id < 0) {
			return null;
		}
		return userDAO.findOne(id);
	}

	@Override
	public IUserModel findOne(long id) {
		return this.userDAO.findOne(id);
	}

	@Override
	public List<IUserModel> searchByName(String value) {
		return this.userDAO.search(value);
	}

}
