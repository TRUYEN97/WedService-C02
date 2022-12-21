package com.tec02.model.impl.request;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;
import com.tec02.model.request.IRequesterModel;

public class RequesterModel implements IRequesterModel{

	private long[] ids;
	private long id;
	private String action;
	private String jwt;
	private Object value;

	public long[] getIds() {
		return ids;
	}

	public void setIds(long[] ids) {
		this.ids = ids;
	}

	@Override
	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String getAction() {
		return action;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue(Class<T> clazz) {
		if (!(value instanceof Map)) {
			return null;
		}
		try {
			return new ObjectMapper().readValue(
					new JSONObject((Map<String,Object>) value).toJSONString(), clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		if (value == null) {
			return null;
		}
		return value.toString();
	}

	@Override
	public void setJwt(String jwt) {
		this.jwt = jwt;
		
	}

	@Override
	public String getJwt() {
		return jwt;
	}
	
}
