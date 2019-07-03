package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import connection.ConnectionPostgresql;
import model.dto.DtoUser;

public class DaoUser implements DaoInterface<DtoUser> {

	private static final String _SELECT_ALL_SQL = "SELECT id,name FROM table1;";
	private static final String _ADD_SQL = "INSERT INTO table1(name) VALUES (?) RETURNING id";
	private static final String _UPDATE_SQL = "UPDATE table1 SET name = ? WHERE id = ? ";
	private static final String _SELECT_ONE_SQL = "SELECT id,name FROM table1 WHERE id = ?";
	private static final String _DELETE_SQL = "DELETE FROM table1 WHERE id = ?";

	@Override
	public Object add(DtoUser dto) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_ADD_SQL);

		preparedStatement.setString(1, dto.getName());

		ResultSet result = preparedStatement.executeQuery();

		int idResult = -1;

		while (result.next()) {
			idResult = result.getInt(1);
		}

		result.close();
		preparedStatement.close();

		return idResult;
	}

	@Override
	public boolean update(DtoUser dto) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_UPDATE_SQL);

		preparedStatement.setString(1, dto.getName());
		preparedStatement.setInt(2, dto.getId());

		int tuplasChange = preparedStatement.executeUpdate();

		preparedStatement.close();

		return (tuplasChange > 0);
	}

	@Override
	public DtoUser get(Object key) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_SELECT_ONE_SQL);
		
		preparedStatement.setInt(1, (int) key);
		
		ResultSet resultSet = preparedStatement.executeQuery();

		DtoUser dtoUser = new DtoUser();

		while (resultSet.next()) {
			dtoUser.setId(resultSet.getInt(1));
			dtoUser.setName(resultSet.getString(2));
		}

		resultSet.close();
		preparedStatement.close();

		return dtoUser;
	}

	@Override
	public List<DtoUser> getAll() throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connection = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connection.getStatement(_SELECT_ALL_SQL);

		ResultSet tableResultSet = preparedStatement.executeQuery();

		DtoUser dtoUser;
		
		List<DtoUser> listUser = new ArrayList<DtoUser>();
		
		while (tableResultSet.next()) {
			dtoUser = new DtoUser();
			dtoUser.setId(tableResultSet.getInt(1));
			dtoUser.setName(tableResultSet.getString(2));
			listUser.add(dtoUser);
		}

		tableResultSet.close();
		preparedStatement.close();

		return listUser;
	}

	@Override
	public boolean delete(Object key) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_DELETE_SQL);

		preparedStatement.setInt(1, (int) key);

		int result = preparedStatement.executeUpdate();

		preparedStatement.close();

		return (result > 0);

	}

}
