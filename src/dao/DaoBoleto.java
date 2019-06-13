package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionPostgresql;
import model.dto.DtoAuto;
import model.dto.DtoBoleto;
import model.dto.DtoPeople;
import model.dto.DtoUser;

public class DaoBoleto implements DaoInterface<DtoBoleto> {
	private static final String _ADD = "INSERT INTO boleto (id_auto,id_persona) VALUES (?,?) RETURNING id";
	private static final String _GET_ALL = "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
			+ "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
			+ "inner join automovil on boleto.id_auto = automovil.id";
	private static final String _GET_ONE = "SELECT persona.id,persona.nombre,persona.apellido_paterno,persona.apellido_materno,persona.telefono,persona.correo,automovil.id,automovil.modelo,\n"
			+ "automovil.placa,automovil.color,boleto.id,boleto.fecha_entrada,boleto.fecha_salida,boleto.total_pago,boleto.estatus FROM boleto inner join persona on boleto.id_persona = persona.id\n"
			+ "inner join automovil on boleto.id_auto = automovil.id WHERE boleto.id = ?";
	private static final String _UPDATE = "UPDATE boleto SET fecha_salida = ?,total_pago = ?,estatus = ? WHERE id = ?";
	private static final String _DELETE = "DELETE FROM boleto WHERE id = ?";

	@Override
	public Object add(DtoBoleto dto) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_ADD);
		preparedStatement.setInt(1, dto.getAuto().getId());
		preparedStatement.setInt(2, dto.getPeople().getId());

		ResultSet resultSet = preparedStatement.executeQuery();

		int resultId = -1;

		while (resultSet.next()) {
			resultId = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();

		return resultId;
	}

	@Override
	public boolean update(DtoBoleto dto) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_UPDATE);
		
		preparedStatement.setString(1, dto.getFecha_salida());
		preparedStatement.setDouble(2, dto.getTotal_pago());
		preparedStatement.setString(3, dto.getEstatus());
		preparedStatement.setInt(4, dto.getId());
		
		int resultUpdate=preparedStatement.executeUpdate();
		
		preparedStatement.close();
		return (resultUpdate > 0);
	}

	@Override
	public boolean delete(Object key) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_DELETE);
		preparedStatement.setInt(1, (int)key);
		int resultDelete = preparedStatement.executeUpdate();
		preparedStatement.close();
		return (resultDelete>0);
	}

	@Override
	public DtoBoleto get(Object key) throws SQLException, ClassNotFoundException {
		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();
		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ONE);

		preparedStatement.setInt(1, (int) key);

		ResultSet resultSet = preparedStatement.executeQuery();

		DtoBoleto dtoBoleto = new DtoBoleto();
		while(resultSet.next()) {
			//persona
			DtoPeople dtoPeople = new DtoPeople();
			dtoPeople.setId(resultSet.getInt(1));
			dtoPeople.setName(resultSet.getString(2));
			dtoPeople.setFirstName(resultSet.getString(3));
			dtoPeople.setLastName(resultSet.getString(4));
			dtoPeople.setTelephone(resultSet.getString(5));
			dtoPeople.setEmail(resultSet.getString(6));
			//automovil
			DtoAuto dtoAuto = new DtoAuto();
			dtoAuto.setId(resultSet.getInt(7));
			dtoAuto.setModelo(resultSet.getString(8));
			dtoAuto.setPlaca(resultSet.getString(9));
			dtoAuto.setColor(resultSet.getString(10));
			//boleto
			dtoBoleto.setId(resultSet.getInt(11));
			dtoBoleto.setFecha_entrada(resultSet.getString(12));
			dtoBoleto.setFecha_salida(resultSet.getString(13));
			dtoBoleto.setTotal_pago(resultSet.getDouble(14));
			dtoBoleto.setEstatus(resultSet.getString(15));
			dtoBoleto.setAuto(dtoAuto);
			dtoBoleto.setPeople(dtoPeople);
		}
		
		resultSet.close();
		preparedStatement.close();

		return dtoBoleto;
	}

	@Override
	public List<DtoBoleto> getAll() throws SQLException, ClassNotFoundException {

		ConnectionPostgresql connectionPostgresql = ConnectionPostgresql.getInstance();

		PreparedStatement preparedStatement = connectionPostgresql.getStatement(_GET_ALL);

		ResultSet resultSet = preparedStatement.executeQuery();

		List listaBoleto = new ArrayList<DtoBoleto>();

		// TODO Revisar como se manejan los objetos en auto y persona
		// TODO cambiarlo.
		while (resultSet.next()) {
			DtoPeople dtoPeople = new DtoPeople();
			dtoPeople.setId(resultSet.getInt(1));
			dtoPeople.setName(resultSet.getString(2));
			dtoPeople.setFirstName(resultSet.getString(3));
			dtoPeople.setLastName(resultSet.getString(4));
			dtoPeople.setTelephone(resultSet.getString(5));
			dtoPeople.setEmail(resultSet.getString(6));

			DtoAuto auto = new DtoAuto();
			auto.setId(resultSet.getInt(7));
			auto.setModelo(resultSet.getString(8));
			auto.setPlaca(resultSet.getString(9));
			auto.setColor(resultSet.getString(10));

			DtoBoleto boleto = new DtoBoleto();

			boleto.setId(resultSet.getInt(11));
			// TODO debe ser objeto date
			boleto.setFecha_entrada(resultSet.getString(12));
			boleto.setFecha_salida(resultSet.getString(13));
			boleto.setTotal_pago(resultSet.getDouble(14));
			boleto.setEstatus(resultSet.getString(15));
			boleto.setAuto(auto);
			boleto.setPeople(dtoPeople);

			listaBoleto.add(boleto);
		}
		resultSet.close();
		preparedStatement.close();

		return listaBoleto;
	}

}