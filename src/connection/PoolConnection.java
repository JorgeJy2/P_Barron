package connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Archivo: PoolConnection.java contiene la definición de la clase
 * PoolConnection.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class PoolConnection {
	// declaración de atributos
	
	private static final String _DRIVER = "org.postgresql.Driver";
	private static final String _JDBC = "jdbc:postgresql://";
	//Remoto
	/*
	private static final String _HOST = "54.39.151.174:5432";
	private static final String _DB_NAME = "estacionamiento?currentSchema=estacion";
	private static final String _USER = "proyectBE";
	private static final String _PASSWORD = "proyectBE@@";
*/
	//Remoto
	private static final String _HOST = "127.0.0.1:5432";
	private static final String _DB_NAME = "estacionamiento?currentSchema=estacion";
	private static final String _USER = "postgres";
	private static final String _PASSWORD = "123456789";


	private static final String _URL = _JDBC + _HOST + "/" + _DB_NAME;
	private static BasicDataSource basicDataSource;
	private static Connection connectionToPool;
	private static PoolConnection instance;

	// Constructor sin parámetros
	private PoolConnection() {
		initConfiguration();
	}

	/**
	 * Método getInstancePool(). Genera la instancia de la clase PoolConnection.
	 * 
	 * @return retorna la instancia de la clase PoolConnection.
	 */
	public static PoolConnection getInstancePool() {
		if (instance == null) {
			instance = new PoolConnection();
		}
		return instance;
	}

	/**
	 * Método initConfiguración. Descripción: Contiene las conexiones de
	 * PoolConnection.
	 */
	private static void initConfiguration() {

		if (basicDataSource == null) {
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(_DRIVER);
			basicDataSource.setUsername(_USER);
			basicDataSource.setPassword(_PASSWORD);
			basicDataSource.setUrl(_URL);
			basicDataSource.setMaxTotal(5);// Maximo de conexiones activas que
											// se asignan desde este pool,
											// negativo = sin limites
			basicDataSource.setMaxIdle(0);// Minimo de conexiones que permanecen
											// inactivas en el grupo sin crear
											// otras adicionales, cero para no
											// crear ninguna
			basicDataSource.setMaxWaitMillis(0);// numero de milesegundos que
												// esperara el pool (cuando no
												// hay conexiones disponibles)
		}
	}

	/**
	 * Método getConnectionToPoll().Obtiene la conexión para PoolConnection.
	 * 
	 * @return retorna connectionToPool
	 * @throws SQLException Excepción de base de datos.
	 */
	public Connection getConnectionToPoll() throws SQLException {
		return connectionToPool = basicDataSource.getConnection();

	}

	/**
	 * Método closePoolConnection(). Cierra la conexión PoolConnection.
	 * 
	 * @throws SQLException Excepción de base de datos.
	 */
	public void closePoolConnection() throws SQLException {
		basicDataSource.close();
	}

}// cierre clase PoolConnection
