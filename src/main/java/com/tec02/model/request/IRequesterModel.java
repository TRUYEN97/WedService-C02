package com.tec02.model.request;

public interface IRequesterModel {
	
	void setAction(String action);
	
	String getAction();
	
	long[] getIds();
	
	void setIds(long[] ids);
	
	void setId(long id);

	long getId();
	
	String getValue();

	<T>T getValue(Class<T> clazz);
	
	void setValue(Object value);
	
	void setJwt(String jwt);

	String getJwt();
}
