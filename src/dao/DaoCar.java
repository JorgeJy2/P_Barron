package dao;

import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PoolConnection;
import model.dto.DtoCar;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

/**
 * Archivo: DaoCar.java contiene la definici�n de la clase DaoCar que implementa
 * DaoInterface.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */

public class DaoCar implements DaoInterface<DtoCar> {
	// declaraci�n de atributos
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
	 * M�todo add
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
	}// cierre m�todo add

	/**
	 * M�todo update
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
	}// cierre m�todo update

	/**
	 * M�todo delete
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
	}// cierre m�todo delete

	/**
	 * M�todo get
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
	}// cierre m�todo get

	/**
	 * M�todo getAll
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
	}// cierre m�todo getAll

	/**
	 * M�todo getPaginator
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
	 * M�todo getFilter
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
	}// cierre m�todo getFilter

	/**
	 * M�todo generateReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @throws ClassNotFoundException excepcion de clase
	 * @throws SQLException           excepcion de base de datos
	 * @throws JRException 
	 * @throws IOException 
	 */
	public void generateReport(FormatReport format) throws ClassNotFoundException, SQLException, JRException, IOException {
		FormatReport reportPeople = format;
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		
		reportPeople.setConexion(connectionPostgresql);
		reportPeople.obtenerInforme();
		reportPeople.compilarInforme();
		reportPeople.MuestraInforme();
		
		connectionPostgresql.close();
	}// cierre m�todo generateReport

}// cierre clase DaoCar
