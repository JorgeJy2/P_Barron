package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionPostgresql;
import model.dto.DtoAuto;

public class DaoAuto implements DaoInterface<DtoAuto> {

	private static final String _ADD = "INSERT INTO automovil(modelo,placa,color) VALUES (?,?,?) RETURNING id";
	private static final String _UPDATE = "UPDATE automovil SET modelo = ? , placa = ?, color = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM automovil WHERE id = ?";
	private static final String _GET_ONE = "SELECT id,modelo,placa,color FROM automovil WHERE id = ?";
	private static final String _GET_ALL = "SELECT id,modelo,placa,color FROM automovil";

	@Override
	public Object add(DtoAuto dto) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_ADD);
		preparedStatement.setString(1, dto.getModelo());
		preparedStatement.setString(2, dto.getPlaca());
		preparedStatement.setString(3, dto.getColor());

		ResultSet resultSet = preparedStatement.executeQuery();

		int idAdded = -1;

		while (resultSet.next()) {
			idAdded = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();

		return idAdded;
	}

	@Override
	public boolean update(DtoAuto dto) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_UPDATE);

		preparedStatement.setString(1, dto.getModelo());
		preparedStatement.setString(2, dto.getPlaca());
		preparedStatement.setString(3, dto.getColor());
		preparedStatement.setInt(4, dto.getId());

		int resultUpdate = preparedStatement.executeUpdate();

		preparedStatement.close();

		return (resultUpdate > 0);
	}

	@Override
	public boolean delete(Object key) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_DELETE);

		preparedStatement.setInt(1, (int) key);

		int result = preparedStatement.executeUpdate();

		preparedStatement.close();

		return (result > 0);
	}

	@Override
	public DtoAuto get(Object key) throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ONE);

		preparedStatement.setInt(1, (int) key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoAuto resultDao = new DtoAuto();

		while (resultSet.next()) {

			resultDao.setId(resultSet.getInt(1));
			resultDao.setModelo(resultSet.getString(2));
			resultDao.setPlaca(resultSet.getString(3));
			resultDao.setColor(resultSet.getString(4));

		}

		resultSet.close();
		preparedStatement.close();

		return resultDao;
	}

	@Override
	public List<DtoAuto> getAll() throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ALL);
		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoAuto> list = new ArrayList<DtoAuto>();

		while (resultSet.next()) {
			DtoAuto auto = new DtoAuto();
			auto.setId(resultSet.getInt(1));
			auto.setModelo(resultSet.getString(2));
			auto.setPlaca(resultSet.getString(3));
			auto.setColor(resultSet.getString(4));
			list.add(auto);
		}

		resultSet.close();
		preparedStatement.close();

		return list;
	}

}
