package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import connection.ConnectionPostgresql;
import model.DtoPeople;

public class DaoPeople implements DaoInterface<DtoPeople> {

	private static final String _ADD = "INSERT INTO persona "
			+ "(nombre,apellido_paterno,apellido_materno,telefono,correo) VALUES (?,?,?,?,?) RETURNING id";
	private static final String _GET_ONE = "SELECT * FROM persona WHERE id=?";
	private static final String _GET_ALL = "SELECT * FROM persona";
	private static final String _DELETE = "DELETE FROM persona WHERE id=?";
	private static final String _UPDATE = "UPDATE persona SET nombre=?,"
			+ "apellido_paterno=?,apellido_materno=?,telefono=?,correo=?";

	@Override
	public Object add(DtoPeople dto) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement statement = connectionPostgresql.getStatement(_ADD);
		statement.setString(1, dto.getName());
		statement.setString(2, dto.getFirstName());
		statement.setString(3, dto.getLastName());
		statement.setString(4, dto.getTelephone());
		statement.setString(5, dto.getEmail());
		
		ResultSet resultSet = statement.executeQuery();
		
		int idResult = -1; // Aquí se va a almacenar el id que retorne la sentencia

		while (resultSet.next()) {
			idResult = resultSet.getInt(1);
		}

		resultSet.close();
		statement.close();

		return idResult;
	}

	@Override
	public boolean update(DtoPeople dto) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(DtoPeople dto) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DtoPeople get(Object key) throws SQLException, ClassNotFoundException {
		
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();
		
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ONE);
		
		int id = (int) key;

		preparedStatement.setInt(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		DtoPeople daoPeople = new DtoPeople();
		
		while(resultSet.next()) {
			daoPeople.setId(resultSet.getInt(1));
			daoPeople.setName(resultSet.getString(2));
			daoPeople.setFirstName(resultSet.getString(3));
			daoPeople.setLastName(resultSet.getString(4));
			daoPeople.setTelephone(resultSet.getString(5));
			daoPeople.setEmail(resultSet.getString(6));
		}
		
		return daoPeople;
	}

	@Override
	public List<DtoPeople> getAll() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
