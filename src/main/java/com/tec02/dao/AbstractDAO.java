package com.tec02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.tec02.mapper.IRowMapper;

public abstract class AbstractDAO<T> implements IDAO<T> {
	public static final int faileID = -1;

	public AbstractDAO() {
	}

	public Connection getConnection() {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
		String url = resourceBundle.getString("url");
		String user = resourceBundle.getString("user");
		String pass = resourceBundle.getString("password");
		try {
			Class.forName(resourceBundle.getString("driverName"));
			return DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected List<T> query(String sql, IRowMapper<T> mapper, Object... parameters) {
		try (Connection connection = getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				setParameter(preparedStatement, parameters);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					List<T> result = new ArrayList<>();
					while (resultSet.next()) {
						result.add(mapper.mapping(resultSet));
					}
					return result;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public T findOne(long ID) {
		List<T> list = findAll(new long[]{ID});
		return list == null || list.isEmpty() ? null : list.get(0);
	}


	protected long countRow(String sql, Object... parameters) {
		try (Connection connection = getConnection()) {
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				setParameter(preparedStatement, parameters);
				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					if (resultSet.next()) {
						return resultSet.getLong(1);
					}
					return faileID;
				}

			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				return faileID;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return faileID;
		}
	}

	protected boolean updateRow(String sql, Object... parameters) {
		try (Connection connection = getConnection()) {
			connection.setAutoCommit(false);
			try {
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
					setParameter(preparedStatement, parameters);
					preparedStatement.executeUpdate();
					connection.commit();
					return true;
				}
			} catch (Exception e) {
				connection.rollback();
				e.printStackTrace();
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	protected long insert(String sql, Object... parameters) throws SQLException {
		try (Connection connection = getConnection()) {
			connection.setAutoCommit(false);
			try {
				try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
					setParameter(preparedStatement, parameters);
					preparedStatement.executeUpdate();
					connection.commit();
					try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
						if (resultSet.next()) {
							return resultSet.getLong(1);
						}
						return faileID;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				connection.rollback();
				throw e;
			}
		}
	}

	private PreparedStatement setParameter(PreparedStatement preparedStatement, Object... parameters) {
		try {
			int index = 1;
			for (Object object : parameters) {
				if (object instanceof String) {
					preparedStatement.setString(index, (String) object);
				} else if (object instanceof Integer) {
					preparedStatement.setInt(index, (Integer) object);
				} else if (object instanceof Long) {
					preparedStatement.setLong(index, (Long) object);
				} else if (object instanceof Timestamp) {
					preparedStatement.setTimestamp(index, (Timestamp) object);
				} else if (object instanceof Boolean) {
					preparedStatement.setBoolean(index, (Boolean) object);
				} else {
					preparedStatement.setNull(index, java.sql.Types.NULL);
				}
				index++;
			}
			return preparedStatement;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
