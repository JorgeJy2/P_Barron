package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class PoolConnectionPostgresql {
	
	private static final String _DRIVER = "org.postgresql.Driver";
	private static final String _JDBC = "jdbc:postgresql://";	
	private static final String _HOST = "54.39.151.174:5432";
	private static final String _DB_NAME = "estacionamiento?currentSchema=estacion";
	private static final String _USER = "proyectBE";
	private static final String _PASSWORD = "proyectBE@@";
	private static final String _URL = _JDBC + _HOST + "/" + _DB_NAME;
	private static BasicDataSource bDS = null;
	private static DataSource dS;
	private static Connection conPS =null;
	
	private PoolConnectionPostgresql(){}
	
	public static BasicDataSource getPoolPs(){
		if (bDS == null) {
			bDS = new BasicDataSource();
			bDS.setDriverClassName(_DRIVER);
			bDS.setUsername(_USER);
			bDS.setPassword(_PASSWORD);
			bDS.setUrl(_URL);
			bDS.setMaxTotal(1);//Maximo de conexiones activas que se asignan desde este pool, negativo = sin limites
			bDS.setMaxIdle(1);//Minimo de conexiones que permanecen inactivas en el grupo sin crear otras adicionales, cero para no crear ninguna
			bDS.setMaxWaitMillis(0);//numero de milesegundos que esperara el pool (cuando no hay conexiones disponibles)
			return bDS;
		}else{
			return bDS;
		}
		
	}
	
	
	public static void main(String[] args) {
		bDS = getPoolPs();
		dS = bDS;
		

		try {
			conPS =  dS.getConnection();
			PreparedStatement preparedStatement = conPS.prepareStatement("SELECT id,modelo,placa,color FROM automovil WHERE id = ?");
			preparedStatement.setInt(1, (int) 44);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {				
				System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));

			}			
			resultSet.close();
			preparedStatement.close();
			//conPS.close();
			
			Connection conPS2 = dS.getConnection();
			PreparedStatement preparedStatement2 = conPS2.prepareStatement("SELECT id,modelo,placa,color FROM automovil WHERE id = ?");
			preparedStatement.setInt(1, (int) 36);
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			while (resultSet.next()) {				
				System.out.println(resultSet2.getString(1)+" "+resultSet2.getString(2));

			}			
			resultSet.close();
			preparedStatement.close();
			conPS.close();
			
			//System.out.println(bDS.getConnection() != null ? "Conexion" : "No");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("No hay conexiones diponibles" + e.getMessage());
		}
	}

}
