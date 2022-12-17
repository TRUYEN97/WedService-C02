package com.tec02.model;

import java.sql.Timestamp;

public interface IModel{
	
	long getId();

	void setId(long id);
	
	long getCreationby();
	
	void setCreationby(long creationby);

	String getCreationTime();

	void setCreationTime(Timestamp creationTime);
	
	Object clone() throws CloneNotSupportedException;
}
