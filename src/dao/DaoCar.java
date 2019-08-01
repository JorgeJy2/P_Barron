package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.ConnectionDB;
import model.dto.DtoCar;
import report.CompileReporte;

public class DaoCar implements DaoInterface<DtoCar> {

	private static final String _ADD = "INSERT INTO automovil(modelo,placa,color) VALUES (?,?,?) RETURNING id";
	private static final String _UPDATE = "UPDATE automovil SET modelo = ? , placa = ?, color = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM automovil WHERE id = ?";
	private static final String _SELECT_BASE = "SELECT id,modelo,placa,color FROM automovil";
	private static final String _GET_ONE = " WHERE id = ?";
	
	private static final String _START = " OFFSET ";
	private static final String _LIMIT = " LIMIT ";
	
	private static final String _GET_ALL = "SELECT id,modelo,placa,color FROM automovil ORDER BY id DESC";
	private static final String _GET_FILTER = "SELECT id,modelo,placa,color FROM automovil WHERE UPPER(@)  LIKE # ORDER BY id DESC";

	@Override
	public Object add(DtoCar dto) throws SQLException, ClassNotFoundException {
		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();
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
	public boolean update(DtoCar dto) throws SQLException, ClassNotFoundException {

		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();

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
		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_DELETE);

		preparedStatement.setInt(1, (int) key);

		int result = preparedStatement.executeUpdate();

		preparedStatement.close();

		return (result > 0);
	}

	@Override
	public DtoCar get(Object key) throws SQLException, ClassNotFoundException {

		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_SELECT_BASE + _GET_ONE);

		preparedStatement.setInt(1, (int) key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoCar resultDao = new DtoCar();

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
	public List<DtoCar> getAll() throws SQLException, ClassNotFoundException {
		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ALL );
		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoCar> list = new ArrayList<DtoCar>();

		while (resultSet.next()) {
			DtoCar auto = new DtoCar();
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

	@Override
	public List<DtoCar> getPaginator(int init, int end) throws SQLException, ClassNotFoundException {
		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();
		
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_SELECT_BASE +" ORDER BY id "+ _LIMIT + end + _START + init);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		List<DtoCar> list = new ArrayList<DtoCar>();

		while (resultSet.next()) {
			DtoCar auto = new DtoCar();
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
	
	@Override
	public List<DtoCar> getFilter(String parameter,String value) throws SQLException, ClassNotFoundException {
		ConnectionDB connectionPostgresql = ConnectionDB.getInstance();
		PreparedStatement preparedStatement = connectionPostgresql.getStatement( _GET_FILTER.replaceAll("@", parameter).replaceAll("#","'%" +value.toUpperCase()+"%'"));
		ResultSet resultSet = preparedStatement.executeQuery();
		List<DtoCar> list = new ArrayList<DtoCar>();
		while (resultSet.next()) {
			DtoCar auto = new DtoCar();
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
	
	public void generateReport() throws ClassNotFoundException, SQLException {
		ConnectionDB.getInstance(); 
		CompileReporte.excecuteReport(ConnectionDB.connection,"reporte.jasper");
	}

}
