package com.tec02.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tec02.model.IModel;

public abstract class AbstactMapper<T extends IModel> implements IRowMapper<T> {

	@Override
	public T mapping(ResultSet resultSet) {
		T model = createAndMapping(resultSet);
		return mapperDefaultValue(model, resultSet);
	}
	
	protected abstract T createAndMapping(ResultSet resultSet);
	
	protected T mapperDefaultValue(T model, ResultSet resultSet) {
		if (model == null || resultSet == null) {
			return null;
		}
		try {
			model.setId(resultSet.getLong("id"));
			model.setCreationby(resultSet.getLong("creationby"));
			model.setCreationTime(resultSet.getTimestamp("creationtime"));
			return model;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
