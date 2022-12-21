package com.tec02.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.tec02.dao.AbstractDAO;
import com.tec02.dao.IUserDAO;
import com.tec02.mapper.IUserMapper;
import com.tec02.model.user.IUserModel;

public class UserDAO extends AbstractDAO<IUserModel> implements IUserDAO {

	private static final String FIND_COMMON_SQL = "select id, username, role_id, user_status, creationby, creationtime from user ";
	@Inject
	private IUserMapper userMapper;

	@Override
	public long save(IUserModel userModel) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into user");
		sql.append("(username, userpass, role_id, user_status, creationby)");
		sql.append(" value(?,?,?,?,?)");
		return insert(sql.toString(), userModel.getUsername(), userModel.getUserpass(), userModel.getRole_id(),
				userModel.isUser_status(), userModel.getCreationby());
	}

	@Override
	public boolean update(IUserModel userModel) {
		StringBuilder sql = new StringBuilder();
		sql.append("update user ");
		sql.append("set username = ?, userpass = ?, role_id = ?, user_status = ?");
		return updateRow(sql.toString(), userModel.getUsername(), userModel.getUserpass(), userModel.getRole_id(),
				userModel.isUser_status());
	}

	@Override
	public boolean delete(long ID) {
		return updateRow("delete user where id = ?", ID);
	}

	@Override
	public List<IUserModel> findAll() {
		return query(FIND_COMMON_SQL, userMapper);
	}

	@Override
	public IUserModel findByUserNameAndPasswordAndStatus(String userName, String password, Boolean status) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from user where user.username like ? and user.userpass like ? and user_status = ?");
		List<IUserModel> userModels = query(sql.toString(), userMapper, userName, password, status);
		return userModels == null || userModels.isEmpty() ? null : userModels.get(0);
	}

	@Override
	public List<IUserModel> findAll(long[] ids) {
		if (ids == null || ids.length <= 0) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append(FIND_COMMON_SQL);
		sql.append("where id in (");
		for (long id : ids) {
			sql.append(id).append(',');
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return query(sql.toString(), userMapper);
	}

	@Override
	public List<IUserModel> search(String value) {
		StringBuilder sql = new StringBuilder();
		sql.append(FIND_COMMON_SQL);
		sql.append("where username like ?");
		return query(sql.toString(), userMapper, value);
	}

}
