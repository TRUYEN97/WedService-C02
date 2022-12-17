package com.tec02.model;

import java.sql.Timestamp;

public abstract class AbstactMoldel implements IModel{
	protected long id;
	protected long creationby;
	protected Timestamp creationTime;
	
	protected AbstactMoldel() {};

	
	
	public long getCreationby() {
		return creationby;
	}


	public void setCreationby(long creationby) {
		this.creationby = creationby;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreationTime() {
		return creationTime == null ? null :creationTime.toString();
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	
}
