package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PoolConnection;
import model.dto.DtoTicket;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

/**
 * Archivo: DaoTicket.java contiene la definici�n de la clase DaoTicket que
 * implementa DaoInterface.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class DaoTicket implements DaoInterface<DtoTicket> {
	// declaraci�n de atributos
	private static final String _ADD = "INSERT INTO boleto (id_auto,id_persona) VALUES (?,?) RETURNING id";

	/*
	 * private static final String _GET_ALL =
	 * "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
	 * +
	 * "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
	 * + "inner join automovil on boleto.id_auto = automovil.id"; private static
	 * final String _GET_ONE =
	 * "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
	 * +
	 * "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
	 * +
	 * "inner join automovil on boleto.id_auto = automovil.id WHERE boleto.id = ?";
	 */

	private static final String _GET_ALL = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id "
			+ "inner join automovil on boleto.id_auto = automovil.id ORDER BY boleto.id DESC";
	
	
	
	
	private static final String _GET_ONE = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id inner join automovil on boleto.id_auto = automovil.id WHERE boleto.id = ? ORDER BY boleto.id DESC";	

	//private static final String _GET_FILTER = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id inner join automovil on boleto.id_auto = automovil.id WHERE boleto.@ LIKE # ORDER BY boleto.id DESC";
	
	private static final String _GET_FILTER_ = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id inner join automovil on boleto.id_auto = automovil.id ";

	private static final String _UPDATE = "UPDATE boleto SET fecha_salida = now(),total_pago = ?,estatus = CAST(? AS estatus_boleto) WHERE id = ?";
	private static final String _DELETE = "DELETE FROM boleto WHERE id = ?";

	/**
	 * M�todo add
	 * 
	 * @param dto objeto de tipo DtoTicket
	 * @return retorna un objeto
	 * @exception Excepciones de base de datos y de clase
	 */
	@Override
	public Object add(DtoTicket dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_ADD);
		preparedStatement.setInt(1, dto.getIdAuto());
		preparedStatement.setInt(2, dto.getIdPesona());

		ResultSet resultSet = preparedStatement.executeQuery();

		int resultId = -1;

		while (resultSet.next()) {
			resultId = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return resultId;
	}// cierre m�todo add

	/**
	 * M�todo update
	 * 
	 * @param dto objeto de tipo DtoTicket
	 * @return retorna un valor de tipo booleano
	 * @exception Excepcion de tipo clase y de base de datos
	 */
	@Override
	public boolean update(DtoTicket dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);
		///
		// preparedStatement.setString(1, dto.getFechaSalida());
		preparedStatement.setDouble(1, dto.getTotalPago());
		preparedStatement.setString(2, dto.getEstatus());

		preparedStatement.setInt(3, dto.getId());

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
		int resultDelete = preparedStatement.executeUpdate();
		preparedStatement.close();
		connectionPostgresql.close();
		return (resultDelete > 0);
	}// cierre m�todo delete

	/**
	 * M�todo get
	 * 
	 * @param key de tipo objeto
	 * @return retorna un objeto de tipo DtoCar
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public DtoTicket get(Object key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ONE);

		preparedStatement.setInt(1, (int) key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoTicket dtoTicket = new DtoTicket();
		while (resultSet.next()) {
			dtoTicket.setIdPesona(resultSet.getInt(1));
			dtoTicket.setEmailAuto(resultSet.getString(2));
			dtoTicket.setIdAuto(resultSet.getInt(3));
			dtoTicket.setPlacaAuto(resultSet.getString(4));
			dtoTicket.setId(resultSet.getInt(5));
			dtoTicket.setFechaEntrada(resultSet.getString(6));
			dtoTicket.setFechaSalida(resultSet.getString(7));
			dtoTicket.setTotalPago(resultSet.getDouble(8));
			dtoTicket.setEstatus(resultSet.getString(9));
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return dtoTicket;
	}// cierre m�todo get

	/**
	 * M�todo getAll
	 * 
	 * @return retorna un objeto de tipo Lista
	 * @exception excepcion de tipo clase y base de datos
	 */
	@Override
	public List<DtoTicket> getAll() throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoTicket> listaBoleto = new ArrayList<DtoTicket>();

		System.out.println("llego al result");
		while (resultSet.next()) {

			DtoTicket dtoTicket = new DtoTicket();
			dtoTicket.setIdPesona(resultSet.getInt(1));
			dtoTicket.setEmailAuto(resultSet.getString(2));
			dtoTicket.setIdAuto(resultSet.getInt(3));
			dtoTicket.setPlacaAuto(resultSet.getString(4));
			dtoTicket.setId(resultSet.getInt(5));
			dtoTicket.setFechaEntrada(resultSet.getString(6));
			dtoTicket.setDate(resultSet.getTimestamp(6));

			dtoTicket.setFechaSalida(resultSet.getString(7));
			dtoTicket.setTotalPago(resultSet.getDouble(8));
			dtoTicket.setEstatus(resultSet.getString(9));
			listaBoleto.add(dtoTicket);
		}

		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listaBoleto;
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
	public List<DtoTicket> getPaginator(int init, int end) throws SQLException, ClassNotFoundException {

		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_GET_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoTicket> listaBoleto = new ArrayList<DtoTicket>();

		while (resultSet.next()) {
			DtoTicket dtoTicket = new DtoTicket();
			dtoTicket.setIdPesona(resultSet.getInt(1));
			dtoTicket.setEmailAuto(resultSet.getString(2));
			dtoTicket.setIdAuto(resultSet.getInt(3));
			dtoTicket.setPlacaAuto(resultSet.getString(4));
			dtoTicket.setId(resultSet.getInt(5));
			dtoTicket.setFechaEntrada(resultSet.getString(6));
			dtoTicket.setFechaSalida(resultSet.getString(7));
			dtoTicket.setTotalPago(resultSet.getDouble(8));
			dtoTicket.setEstatus(resultSet.getString(9));
			listaBoleto.add(dtoTicket);

		}
		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listaBoleto;

	}// cierre m�todo getPaginator

	/**
	 * M�todo getFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @return retorna una lista
	 * @exception excepcion de clase y de base de datos
	 */
	public List<DtoTicket> getFilter(String parameter, String value) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();

		/*
		 * PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(
		 * _GET_FILTER.replaceAll("@", parameter).replaceAll("#","'%"
		 * +value.toUpperCase()+"%'"));
		 * 
		 * 
		 * System.out.println( _GET_FILTER.replaceAll("@",
		 * parameter).replaceAll("#","'%" +value.toUpperCase()+"%'"));
		 */
		String select = "";
		if (parameter.equals("estatus")) {
			select = _GET_FILTER_ + "WHERE estatus = '" + value + "' ORDER BY boleto.id DESC";
		} else if (parameter.equals("Auto")) {
			select = _GET_FILTER_ + "WHERE automovil.placa LIKE '%" + value + "%' ORDER BY boleto.id DESC";
		} else if (parameter.equals("Persona")) {
			select = _GET_FILTER_ + "WHERE persona.correo LIKE '%" + value + "%' ORDER BY boleto.id DESC";
		}

		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(select);

		ResultSet resultSet = preparedStatement.executeQuery();

		List<DtoTicket> listaBoleto = new ArrayList<DtoTicket>();

		while (resultSet.next()) {
			DtoTicket dtoTicket = new DtoTicket();
			dtoTicket.setIdPesona(resultSet.getInt(1));
			dtoTicket.setEmailAuto(resultSet.getString(2));
			dtoTicket.setIdAuto(resultSet.getInt(3));
			dtoTicket.setPlacaAuto(resultSet.getString(4));
			dtoTicket.setId(resultSet.getInt(5));
			dtoTicket.setFechaEntrada(resultSet.getString(6));
			dtoTicket.setFechaSalida(resultSet.getString(7));
			dtoTicket.setTotalPago(resultSet.getDouble(8));
			dtoTicket.setEstatus(resultSet.getString(9));
			listaBoleto.add(dtoTicket);

		}
		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listaBoleto;
	}// cierre m�todo getFilter

	@Override
	public void generateReport(FormatReport format)  throws ClassNotFoundException, SQLException, JRException, IOException { 
		FormatReport reportPeople = format; 
		
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		
		reportPeople.setConexion(connectionPostgresql);
		reportPeople.obtenerInforme();
		reportPeople.compilarInforme();
		reportPeople.MuestraInforme();
		
		connectionPostgresql.close();
	}
}
