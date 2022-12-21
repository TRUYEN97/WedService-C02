package com.tec02.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import com.tec02.dao.AbstractDAO;
import com.tec02.dao.IRoleDAO;
import com.tec02.mapper.IRoleMapper;
import com.tec02.model.role.IRoleModel;

public class RoleDAO extends AbstractDAO<IRoleModel> implements IRoleDAO {
	private static final String FIND_COMMON_SQL = "select id, role_code, rolename, creationby, creationtime from role ";
	@Inject
	private IRoleMapper roleMapper;

	@Override
	public long save(IRoleModel model) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean update(IRoleModel model) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long ID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<IRoleModel> findAll(long[] ids) {
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
		return query(sql.toString(), roleMapper);
	}

}
