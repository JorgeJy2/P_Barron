package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.PoolConnection;
import model.dto.DtoTicket;

public class DaoTicket implements DaoInterface<DtoTicket> {
	private static final String _ADD = "INSERT INTO boleto (id_auto,id_persona) VALUES (?,?) RETURNING id";
	
	/*
	private static final String _GET_ALL = "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
			+ "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
			+ "inner join automovil on boleto.id_auto = automovil.id";
	private static final String _GET_ONE = "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
			+ "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
			+ "inner join automovil on boleto.id_auto = automovil.id WHERE boleto.id = ?";
	*/
	
	private static final String _GET_ALL = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id "
			+ "inner join automovil on boleto.id_auto = automovil.id";
	
	private static final String _GET_ONE = "SELECT persona.id,persona.correo,automovil.id,automovil.placa,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id inner join automovil on boleto.id_auto = automovil.id WHERE boleto.id = ?";
	
	
	private static final String _UPDATE = "UPDATE boleto SET fecha_salida = ?,total_pago = ?,estatus = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM boleto WHERE id = ?";

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
	}

	@Override
	public boolean update(DtoTicket dto) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_UPDATE);
		///
		preparedStatement.setString(1, dto.getFechaSalida());
		preparedStatement.setDouble(2, dto.getTotalPago());
		preparedStatement.setString(3, dto.getEstatus());
		preparedStatement.setInt(4, dto.getId());

		int resultUpdate = preparedStatement.executeUpdate();

		preparedStatement.close();
		connectionPostgresql.close();
		return (resultUpdate > 0);
	}

	@Override
	public boolean delete(Object key) throws SQLException, ClassNotFoundException {
		Connection connectionPostgresql = PoolConnection.getInstancePool().getConnectionToPoll();
		PreparedStatement preparedStatement = connectionPostgresql.prepareStatement(_DELETE);
		preparedStatement.setInt(1, (int) key);
		int resultDelete = preparedStatement.executeUpdate();
		preparedStatement.close();
		connectionPostgresql.close();
		return (resultDelete > 0);
	}

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
	}

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
			dtoTicket.setFechaSalida(resultSet.getString(7));
			dtoTicket.setTotalPago(resultSet.getDouble(8));
			dtoTicket.setEstatus(resultSet.getString(9));
			listaBoleto.add(dtoTicket);
		}
		
		
		resultSet.close();
		preparedStatement.close();
		connectionPostgresql.close();

		return listaBoleto;
	}

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
		
	}
	public List<DtoTicket> getFilter(String parameter, String value) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
