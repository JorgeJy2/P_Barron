package controller;

/**
 * Archivo: ControllerInterface.java contiene la definición de la interface
 * ControllerInterface que establece los métodos que deben implementar las
 * clases que extiendan de esta.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public interface ControllerInterface {
	// métodos de la interfaz
	/**
	 * Método saveRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean saveRegistry();

	/**
	 * Método filter
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean filter();

	/**
	 * Método updateRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean updateRegistry();

	/**
	 * Método deleteRegistry
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean deleteRegistry();

	/**
	 * Método getDataOfView
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean getDataOfView();

	/**
	 * Método setDataOfView
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean setDataOfView();

	/**
	 * Método reloadData
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean reloadData();

	/**
	 * Método addListener
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean addListener();
}// cierre interface ControllerInterface
