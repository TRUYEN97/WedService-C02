package com.tec02.model.user;

import java.util.List;

import com.tec02.model.IModel;

public interface IUserModel extends IModel, Cloneable{

	
	void setIds(long[] ids) ;

	long[] getIds() ;
	
	boolean isUser_status();

	void setUser_status(boolean user_status);

	String getUsername();

	void setUsername(String username);
	
	Long getRole_id();
	
	void setRole_id(Long role_id);

	String getUserpass();

	void setUserpass(String userpass);

	void setUserModels(List<IUserModel> findAll);
	
	List<IUserModel> getUserModels();
}
