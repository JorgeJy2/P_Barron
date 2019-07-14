package connection;
/**
 * Archivo: ConnectionPostgresql
 * 
 * Objestivo: Proporcionar al usuario una interfaz para la coneción a 
 * la base de datos en el servidor postgresql.
 * 
 * @author jorge
 * 
 * @date 01/06/2019
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionPostgresql {

	// **Atributos**
	// De clase
	
	private static ConnectionPostgresql instance;
	
	// Atributos de configuración para JDBC
	
	private static Connection connection;

	private static final String _DRIVER = "org.postgresql.Driver";
	
	private static final String _JDBC = "jdbc:postgresql://";

	/**
	 * Local
	 */
	/*
	
	private static final String _HOST = "127.0.0.1:5432";

	private static final String _DB_NAME = "estacionamiento";

	private static final String _USER = "postgres";
	private static final String _PASSWORD = "123456789";

	 */
	/**
	 * Remote
	 */
	
	private static final String _HOST = "raja.db.elephantsql.com";

	private static final String _DB_NAME = "ipvbsaqa";

	private static final String _USER = "ipvbsaqa";
	private static final String _PASSWORD = "o0pMwJZa3u0DZPREY2sAG5Q3TetVUCs8";
	
	
	// Constructores
	private ConnectionPostgresql() {}

	/**
	 * getInstancia
	 * 
	 * Retorna una instancia del objeto haciendo el uso del patron singleton. 
	 * @return instancia esta misma clase  {@link Connection}
	 * */
	public static ConnectionPostgresql getInstance() throws ClassNotFoundException, SQLException {
		String url = "";
		if ( instance == null ) //Sí no existe una istancia de la misma clase
			instance = new ConnectionPostgresql(); //Se crea una 
		
		if ( connection == null ) { //Sí no existe una instancia de conexión
			Class.forName(_DRIVER);
			url = _JDBC + _HOST + "/" + _DB_NAME;
			connection = DriverManager.getConnection(url, _USER, _PASSWORD);
		} //end if connection == null
		
		return instance;
	}//end get instance

	/** 
	 * getStatement
	 * 
	 * Toma los objetos instanciados por el metodo getInstance
	 * 
	 * @param sql tipo string con la consulta SQL que se desea ejecutar.
	 * @return PreparedStatement resultado de la sentencia SQL recibida.
	 * @throws SQLException
	 */
	public PreparedStatement getStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * close
	 * 
	 * Cierra la conexión a JDBC.
	 * 
	 * @throws SQLException
	 */
	public void close() throws SQLException {
		connection.close();
	}//End close

} //End class
