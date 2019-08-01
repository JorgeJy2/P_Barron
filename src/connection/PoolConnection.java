package connection;

import java.sql.Connection;
import java.sql.SQLException;


import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConnection {
		
	private static final String _DRIVER = "org.postgresql.Driver";
	private static final String _JDBC = "jdbc:postgresql://";	
	private static final String _HOST = "54.39.151.174:5432";
	private static final String _DB_NAME = "estacionamiento?currentSchema=estacion";
	private static final String _USER = "proyectBE";
	private static final String _PASSWORD = "proyectBE@@";
	private static final String _URL = _JDBC + _HOST + "/" + _DB_NAME;
	private static BasicDataSource basicDataSource = null;
	private static Connection connectionToPool = null;
	private static PoolConnection instance;
		
	private PoolConnection() {
		initConfiguration();	
	}
		
	public static PoolConnection getInstancePool(){
		if (instance == null ) {
			instance = new PoolConnection();
		}
		return instance;
	}
		
	private static void initConfiguration(){
		
		if (basicDataSource == null) {
			basicDataSource = new BasicDataSource();
			basicDataSource.setDriverClassName(_DRIVER);
			basicDataSource.setUsername(_USER);
			basicDataSource.setPassword(_PASSWORD);
			basicDataSource.setUrl(_URL);
			basicDataSource.setMaxTotal(5);//Maximo de conexiones activas que se asignan desde este pool, negativo = sin limites
			basicDataSource.setMaxIdle(0);//Minimo de conexiones que permanecen inactivas en el grupo sin crear otras adicionales, cero para no crear ninguna
			basicDataSource.setMaxWaitMillis(0);//numero de milesegundos que esperara el pool (cuando no hay conexiones disponibles)
		}
	}
		
	public Connection getConnectionToPoll(){
		try {
			return connectionToPool = basicDataSource.getConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connectionToPool;
	}

}

