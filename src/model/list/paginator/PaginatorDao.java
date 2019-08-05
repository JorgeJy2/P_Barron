package model.list.paginator;

import java.sql.SQLException;
import java.util.List;

import dao.DaoInterface;

/**
 * Archivo: PaginatorDao.java contiene la definición de la clase PaginatorDao
 * que implementa de la interfaz Paginator.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class PaginatorDao<Dto> implements Paginator<Dto> {
	// declaración de atributos
	private DaoInterface<Dto> dao;
	private int index = 0;

	/**
	 * Constructor con parámetro
	 * 
	 * @param dao objeto de tipo DaoInterface
	 */
	public PaginatorDao(DaoInterface<Dto> dao) {
		this.dao = dao;
	}// cierre constructor

	// Implementación de métodos
	/**
	 * Método first
	 * 
	 * @return retorna un objeto de tipo List
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public List<Dto> first() throws ClassNotFoundException, SQLException {
		return dao.getPaginator(0, NUM_PAGINATOR);
	}// Cierre método first

	/**
	 * Método next
	 * 
	 * @return retorna un objeto de tipo List
	 * @throws excepcion de clase y base de datos
	 */
	@Override
	public List<Dto> next() throws ClassNotFoundException, SQLException {
		List<Dto> next = dao.getPaginator(index, ((index + 1) + NUM_PAGINATOR));

		if (next.size() > 0)
			index += next.size();

		return next;
	}// cierre método next

}// cierre clase PaginatorDao
