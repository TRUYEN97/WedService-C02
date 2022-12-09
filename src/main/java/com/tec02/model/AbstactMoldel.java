package com.tec02.model;

import java.sql.Timestamp;

public abstract class AbstactMoldel<T> implements IModel<T>{
	protected long id;
	protected T creator;
	protected Timestamp creationTime;
	
	protected AbstactMoldel() {};

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getCreator() {
		return creator;
	}

	public void setCreator(T creator) {
		this.creator = creator;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
}
