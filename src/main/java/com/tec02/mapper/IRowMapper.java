package com.tec02.mapper;

import java.sql.ResultSet;

public interface IRowMapper<T> {
	T mapper(ResultSet resultSet);
}
