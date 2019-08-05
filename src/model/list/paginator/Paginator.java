package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

/**
 * Archivo: Paginator.java contiene la definición de la interface Paginator.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public interface Paginator<Dto> {
	// declaración de atributo
	int NUM_PAGINATOR = 15;

	// métodos de la interface
	public List<Dto> first() throws ClassNotFoundException, SQLException;

	public List<Dto> next() throws ClassNotFoundException, SQLException;

}// cierre interface Paginator
