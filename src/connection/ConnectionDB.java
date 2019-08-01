package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Clase: ConnectionDB.java
 * Objetivo: Otorgar a los usuarios una interfaz la cual permita gestionar la conexión a una base de datos
 * establecida y otorgrar métodos que permitar dar respuesta a consultar SQL. 
 * 
 * @author jorge
 *
 */

public class ConnectionDB {

	
	private static ConnectionDB instance;
	private static Connection connection;

	private static final String _DRIVER = "org.postgresql.Driver";

	private static final String _JDBC = "jdbc:postgresql://";
	
	
	private static final String _HOST = "54.39.151.174:5432";

	private static final String _DB_NAME = "estacionamiento?currentSchema=estacion";

	private static final String _USER = "proyectBE";
	private static final String _PASSWORD = "proyectBE@@";
	/*
	
	private static final String _HOST = "127.0.0.1:5432";
 	private static final String _DB_NAME = "estacionamiento";	
 	private static final String _USER = "postgres";		
	private static final String _PASSWORD = "123456789";
	
	private ConnectionDB() {}

	public static ConnectionDB getInstance() throws ClassNotFoundException, SQLException {
		String url = "";
		if (instance == null) 
			instance = new ConnectionDB();
		
		if (connection == null) {
			Class.forName(_DRIVER);
			url = _JDBC + _HOST + "/" + _DB_NAME;
			connection = DriverManager.getConnection(url, _USER, _PASSWORD);
		}//End if connection
		return instance;
	}//End getInstance

	public PreparedStatement getStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	public void close() throws SQLException {
		connection.close();
	}//End close

} //End class
	