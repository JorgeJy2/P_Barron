package dao;
/**
 * 
 * Archivo: DaoInterface.java
 * 
 * Objetivo: Proporcionar una interfaz para unificar las operaciones que se realizan
 * por medio del patrón de diseño DAO, generalizando la transmición en una clase generica.
 * 
 * @author: jorge
 * @Date: 01/05/2019
 * 
 */
import java.sql.SQLException;

import java.util.List;

public interface DaoInterface<Model> {

	/**
	 * add
	 * 
	 * Agrega los valores de los atributos de la clase DTO generica con la que se está trabajando.
	 * 
	 * @param dto
	 * @return Instancia de tipo de dato primitivo
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Object add(Model dto) throws SQLException, ClassNotFoundException;

	/**
	 * update
	 * 
	 * Actualiza los valores de los atributos de la clase DTO generica con la que se está trabajando.
	 * 
	 * @param dto
	 * @return booleano (True sí la operación es correcta, False sí no se pudo realizar la operación)
	 * @throws SQLException
	 * @throws ClssNotFoundException
	 */
	boolean update(Model dto) throws SQLException, ClassNotFoundException;

	/**
	 * delete
	 * 
	 * Elimina el registro que contenga la clave recibida.
	 * 
	 * @param key
	 * @return booleano (True sí la operación es correcta, False sí no se pudo realizar la operación)
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	boolean delete(Object key) throws SQLException, ClassNotFoundException;

	/**
	 * get
	 * 
	 * Obtiene una instancia con los valores del registro que contenga la clave recibida.
	 * 
	 * 
	 * @param key
	 * @return instancia de DTO generico con el que se esté trabajando
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Model get(Object key) throws SQLException, ClassNotFoundException;

	/**
	 * get all
	 * 
	 * Obtiene una istancia de la interface {@link List}  con todos los registros almacenados de tipo Dto generico.
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	List<Model> getAll() throws SQLException, ClassNotFoundException;
	
	List<Model> getPaginator(int init, int end) throws SQLException, ClassNotFoundException;

}
