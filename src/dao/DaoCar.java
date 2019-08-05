package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PoolConnection;
import model.dto.DtoCar;
import report.FormatReport;
import report.ReportCar;

/**
 * Archivo: DaoCar.java contiene la definición de la clase DaoCar que implementa
 * DaoInterface.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */

public class DaoCar implements DaoInterface<DtoCar> {
	// declaración de atributos
	private static final String _ADD = "INSERT INTO automovil(modelo,placa,color) VALUES (?,?,?) RETURNING id";
	private static final String _UPDATE = "UPDATE automovil SET modelo = ? , placa = ?, color = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM automovil WHERE id = ?";
	private static final String _SELECT_BASE = "SELECT id,modelo,placa,color FROM automovil";
	private static final String _GET_ONE = " WHERE id = ?";

	private static final String _START = " OFFSET ";
	private static final String _LIMIT = " LIMIT ";

	private static final String _GET_ALL = "SELECT id,modelo,placa,color FROM automovil ORDER BY id DESC";
	private static final String _GET_FILTER = "SELECT id,modelo,placa,color FROM automovil WHERE UPPER(@)  LIKE # ORDER BY id DESC";

	/**
	 * Método add
	 * 
	 * @param dto objeto de tipo DtoCar
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoCar dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
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
		connectionPostgresql.close();
		return idAdded;
	}// cierre método add

	/**
	 * Método update
	 * 
	 * @param dto objeto de tipo DtoCar
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean update(DtoCar dto) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);

		preparedStatement.setString(1, dto.getModelo());
		preparedStatement.setString(2, dto.getPlaca());
		preparedStatement.setString(3, dto.getColor());
		preparedStatement.setInt(4, dto.getId());

		int resultUpdate = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();

		return (resultUpdate > 0);
	}// cierre método update

	/**
	 * Método delete
	 * 
	 * @param key de tipo objeto
	 * @return retorna valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean delete(Object key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_DELETE);

		preparedStatement.setInt(1, (int) key);

		int result = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();

		return (result > 0);
	}// cierre método delete

	/**
	 * Método get
	 * 
	 * @param key de tipo objeto
	 * @return retorna un objeto de tipo DtoCar
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public DtoCar get(Object key) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_SELECT_BASE + _GET_ONE);

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
		connectionPostgresql.close();

		return resultDao;
	}// cierre método get

	/**
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Lista
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public List<DtoCar> getAll() throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);
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
		connectionPostgresql.close();

		return list;
	}// cierre método getAll

	/**
	 * Método getPaginator
	 * 
	 * @param init valor de tipo entero
	 * @param end  valor de tipo entero
	 * @return retorna una lista
	 * @exception excepcion de clase y base de datos
	 */
	@Override
	public List<DtoCar> getPaginator(int init, int end) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql
				.prepareStatement(_SELECT_BASE + " ORDER BY id DESC " + _LIMIT + end + _START + init);

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
		connectionPostgresql.close();

		return list;
	}// cierre metodo getPaginator

	/**
	 * Método getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @exception excepcion de clase y de base de datos
	 */
	@Override
	public List<DtoCar> getFilter(String parameter, String value) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(
				_GET_FILTER.replaceAll("@", parameter).replaceAll("#", "'%" + value.toUpperCase() + "%'"));
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
		connectionPostgresql.close();

		return list;
	}// cierre método getFilter

	/**
	 * Método generateReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @throws ClassNotFoundException excepcion de clase
	 * @throws SQLException           excepcion de base de datos
	 */
	public void generateReport(FormatReport format) throws ClassNotFoundException, SQLException {
		FormatReport reportPeople = format;
		reportPeople.setConexion(PoolConnection.getInstancePool().getConnectionToPoll());
		reportPeople.obtenerInforme();
		reportPeople.compilarInforme();
		reportPeople.MuestraInforme();
	}// cierre método generateReport

}// cierre clase DaoCar
