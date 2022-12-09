package com.tec02.model;

import java.sql.Timestamp;

public interface IModel<T>{
	
	long getId();

	void setId(long id);

	T getCreator();

	void setCreator(T creator);

	Timestamp getCreationTime();

	void setCreationTime(Timestamp creationTime);
	
	Object clone() throws CloneNotSupportedException;
}
