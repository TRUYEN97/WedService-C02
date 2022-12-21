package com.tec02.dao;

import java.sql.SQLException;
import java.util.List;

public interface IDAO<T> {
	
	List<T> findAll(long[] ids);
	
	T findOne(long ID);
	
	long save(T model) throws SQLException;
	
	boolean update(T model);
	
	boolean delete(long ID);
}
