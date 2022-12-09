package com.tec02.service;

import com.tec02.model.IUserModel;

public interface IUserService {

	IUserModel  findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status);
}
