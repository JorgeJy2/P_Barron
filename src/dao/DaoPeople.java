package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connection.PoolConnection;
import model.dto.DtoPeople;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;


/**
 * Archivo: DaoPeople.java contiene la definici�n de la clase DaoPeople que
 * implementa DaoInterface.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class DaoPeople implements DaoInterface<DtoPeople> {
	// declaraci�n de atributos
	private static final String _ADD = "INSERT INTO persona (nombre,apellido_paterno,apellido_materno,telefono,correo) VALUES (?,?,?,?,?) RETURNING id";
	private static final String _GET_ONE = "SELECT id,nombre,apellido_paterno,apellido_materno,telefono,correo FROM persona WHERE id=?";
	private static final String _GET_ALL = "SELECT id,nombre,apellido_paterno,apellido_materno,telefono,correo FROM persona  ";

	private static final String _SELECT_BASE = "SELECT id,nombre,apellido_paterno,apellido_materno,telefono,correo FROM persona ";

	private static final String _START = " OFFSET ";
	private static final String _LIMIT = " LIMIT ";

	private static final String _DELETE = "DELETE FROM persona WHERE id=?";
	private static final String _UPDATE = "UPDATE persona SET nombre=?,apellido_paterno=?,apellido_materno=?,telefono=?,correo=? WHERE id= ?";
	private static final String _GET_FILTER = "SELECT id,nombre,apellido_paterno,apellido_materno,telefono,correo FROM persona WHERE UPPER(@)  LIKE # ORDER BY id DESC";

	// M�todos implementados de la interface DaoInterface
	/**
	 * M�todo add
	 * 
	 * @param dto objeto de tipo DtoPeople
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoPeople dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
		preparedStatement.setString(1, dto.getName());
		preparedStatement.setString(2, dto.getFirstName());
		preparedStatement.setString(3, dto.getLastName());
		preparedStatement.setString(4, dto.getTelephone());
		preparedStatement.setString(5, dto.getEmail());
		ResultSet result = null;
		result = preparedStatement.executeQuery();
		int idResult = -1;

		while (result.next()) {
			idResult = result.getInt(1);
		}

		result.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return idResult;
	}// cierre m�todo add

	/**
	 * M�todo update
	 * 
	 * @param dto objeto de tipo DtoPeople
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean update(DtoPeople dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);

		preparedStatement.setString(1, dto.getName());
		preparedStatement.setString(2, dto.getFirstName());
		preparedStatement.setString(3, dto.getLastName());
		preparedStatement.setString(4, dto.getTelephone());
		preparedStatement.setString(5, dto.getEmail());
		preparedStatement.setInt(6, dto.getId());

		int tuplasChange = preparedStatement.executeUpdate();
		preparedStatement.close();
		connectionPostgresql.close();

		return (tuplasChange > 0);
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
	public DtoPeople get(Object key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ONE);

		preparedStatement.setInt(1, (int) key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoPeople dtoPeople = new DtoPeople();

		while (resultSet.next()) {
			dtoPeople.setName(resultSet.getString(1));
			dtoPeople.setFirstName(resultSet.getString(2));
			dtoPeople.setLastName(resultSet.getString(3));
			dtoPeople.setTelephone(resultSet.getString(4));
			dtoPeople.setEmail(resultSet.getString(5));
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();
		return dtoPeople;
	}// cierre m�todo get

	/**
	 * M�todo getAll
	 * 
	 * @return retorna un objeto de tipo Lista
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public List<DtoPeople> getAll() throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);

		ResultSet tableResultSet = preparedStatement.executeQuery();

		DtoPeople dtoPeople;

		List<DtoPeople> listPeople = new ArrayList<DtoPeople>();

		while (tableResultSet.next()) {

			dtoPeople = new DtoPeople();

			dtoPeople.setId(tableResultSet.getInt(1));
			dtoPeople.setName(tableResultSet.getString(2));
			dtoPeople.setFirstName(tableResultSet.getString(3));
			dtoPeople.setLastName(tableResultSet.getString(4));
			dtoPeople.setTelephone(tableResultSet.getString(5));
			dtoPeople.setEmail(tableResultSet.getString(6));

			listPeople.add(dtoPeople);
		}

		tableResultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listPeople;
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
	public List<DtoPeople> getPaginator(int init, int end) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql
				.prepareStatement(_SELECT_BASE + " ORDER BY id DESC " + _LIMIT + end + _START + init);

		ResultSet tableResultSet = preparedStatement.executeQuery();

		DtoPeople dtoPeople;

		List<DtoPeople> listPeople = new ArrayList<DtoPeople>();

		while (tableResultSet.next()) {

			dtoPeople = new DtoPeople();

			dtoPeople.setId(tableResultSet.getInt(1));
			dtoPeople.setName(tableResultSet.getString(2));
			dtoPeople.setFirstName(tableResultSet.getString(3));
			dtoPeople.setLastName(tableResultSet.getString(4));
			dtoPeople.setTelephone(tableResultSet.getString(5));
			dtoPeople.setEmail(tableResultSet.getString(6));

			listPeople.add(dtoPeople);
		}

		tableResultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listPeople;
	}// cierre m�todo getPaginator

	/**
	 * M�todo getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @exception excepcion de clase y de base de datos
	 */
	public List<DtoPeople> getFilter(String parameter, String value) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(
				_GET_FILTER.replaceAll("@", parameter).replaceAll("#", "'%" + value.toUpperCase() + "%'"));
		ResultSet tableResultSet = preparedStatement.executeQuery();
		List<DtoPeople> list = new ArrayList<DtoPeople>();
		DtoPeople dtoPeople;
		while (tableResultSet.next()) {
			dtoPeople = new DtoPeople();
			dtoPeople.setId(tableResultSet.getInt(1));
			dtoPeople.setName(tableResultSet.getString(2));
			dtoPeople.setFirstName(tableResultSet.getString(3));
			dtoPeople.setLastName(tableResultSet.getString(4));
			dtoPeople.setTelephone(tableResultSet.getString(5));
			dtoPeople.setEmail(tableResultSet.getString(6));
			list.add(dtoPeople);
		}
		tableResultSet.close();
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
	 * @throws IOException 
	 * @throws JRException 
	 */
	public void generateReport(FormatReport format) throws ClassNotFoundException, SQLException, IOException, JRException {
		FormatReport reportPeople = format;
		reportPeople.setConexion(PoolConnection.getInstancePool().getConnectionToPoll());
		reportPeople.obtenerInforme();
		reportPeople.compilarInforme();
		reportPeople.MuestraInforme();
	}// cierre m�todo generateReport
}// cierre clase DaoPeople
