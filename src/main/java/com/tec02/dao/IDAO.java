package com.tec02.dao;

public interface IDAO<T> {
	
	T findOne(long ID);
	
	long save(T model);
	
	boolean update(T model);
	
	boolean delete(long ID);
}
