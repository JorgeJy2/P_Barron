package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Archivo: ConnectionDB.java contiene la definición de la clase ConnectionBD.
 * Objetivo: Otorgar a los usuarios una interfaz la cual permita gestionar la
 * conexión a una base de datos establecida y otorgar métodos que permitan dar
 * respuesta a consultas SQL.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */

public class ConnectionDB {
	// declaración de variables
	private static ConnectionDB instance;
	public static Connection connection;

	private static final String _DRIVER = "org.postgresql.Driver";

	private static final String _JDBC = "jdbc:postgresql://";

	/*
	 * private static final String _HOST = "54.39.151.174:5432";
	 * 
	 * private static final String _DB_NAME =
	 * "estacionamiento?currentSchema=estacion";
	 * 
	 * private static final String _USER = "proyectBE"; private static final String
	 * _PASSWORD = "proyectBE@@";
	 * 
	 */

	private static final String _HOST = "127.0.0.1:5432";
	private static final String _DB_NAME = "estacionamiento";
	private static final String _USER = "postgres";
	private static final String _PASSWORD = "123456789";

	// Constructor sin parámetros
	private ConnectionDB() {
	}

	/**
	 * Método getInstance(). Genera la instancia de la clase ConnectionDB.
	 * 
	 * @return retorna la instancia de la clase ConnectionDB.
	 * @throws ClassNotFoundException Excepción de clase
	 * @throws SQLException           Excepción de base de datos
	 */
	public static ConnectionDB getInstance() throws ClassNotFoundException, SQLException {
		String url = "";
		if (instance == null) {
			instance = new ConnectionDB();
			System.out.println("Good instance");
		}
		if (connection == null) {
			Class.forName(_DRIVER);
			url = _JDBC + _HOST + "/" + _DB_NAME;
			connection = DriverManager.getConnection(url, _USER, _PASSWORD);
		}
		return instance;
	}

	/**
	 * Método getStatement
	 * 
	 * @param sql valor de tipo String
	 * @return retorna el prepareStatement de la sentencia sql.
	 * @throws SQLException Excepción de base de datos.
	 */
	public PreparedStatement getStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Método close(). Cierra la conexión a la base de datos.
	 * 
	 * @throws SQLException Excepción de base de datos.
	 */
	public void close() throws SQLException {
		System.out.println("Close");
		connection.close();
	}// cierre método close

} // cierre clase ConnectionDB
