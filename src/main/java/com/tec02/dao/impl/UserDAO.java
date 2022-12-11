package com.tec02.dao.impl;

import java.util.List;

import javax.inject.Inject;

import com.tec02.dao.AbstractDAO;
import com.tec02.dao.IUserDAO;
import com.tec02.mapper.IUserMapper;
import com.tec02.mapper.impl.UserMapper;
import com.tec02.model.IUserModel;

public class UserDAO extends AbstractDAO<IUserModel> implements IUserDAO {
	
	@Inject
	private IUserMapper userMapper;

	@Override
	public IUserModel findOne(long ID) {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.*, role.role_code, role.rolename");
		sql.append(" from user join role on role.id = user.role_id");
		sql.append(" user.id = ?");
		List<IUserModel> userModels = query(sql.toString(), userMapper, ID);
		return userModels != null && userModels.isEmpty()? null : userModels.get(0);
	}

	@Override
	public long save(IUserModel userModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into user");
		sql.append("(username, userpass, role_id, user_status, creationby)");
		sql.append(" value(?,?,?,?,?)");
		return insert(sql.toString(), userModel.getUsername(), userModel.getUserpass(),
				userModel.getRoleModel().getId(),userModel.isUser_status(), userModel.getCreator().getId());
	}

	@Override
	public boolean update(IUserModel userModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("update user");
		sql.append("set username = ?, userpass = ?, role_id = ?, user_status = ?");
		return updateRow(sql.toString(), userModel.getUsername(), userModel.getUserpass(),
				userModel.getRoleModel().getId(),userModel.isUser_status());
	}

	@Override
	public boolean delete(long ID) {
		return updateRow("delete user where id = ?", ID);
	}

	@Override
	public List<IUserModel> findAll() {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.*, role.role_code, role.rolename");
		sql.append(" from user join role on role.id = user.role_id");
		return query(sql.toString(), new UserMapper());
	}

	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		StringBuilder sql = new StringBuilder();
		sql.append("select user.*, role.role_code, role.rolename");
		sql.append(" from user join role on role.id = user.role_id");
		sql.append(" where user.username like ? and user.userpass like ? and user_status = ?");
		List<IUserModel> userModels = query(sql.toString(), userMapper, userName, password, status);
		return userModels != null && userModels.isEmpty()? null : userModels.get(0);
	}

}
