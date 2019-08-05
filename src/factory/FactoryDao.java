package factory;

import dao.DaoInterface;

/**
 * Archivo: FactoryDao.java contiene la definición de la clase FactoryDao.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 *
 */
public class FactoryDao {
	/**
	 * Método estático crearFabricaDao
	 * 
	 * @param factory objeto de tipo ObjectDao
	 */
	public static void crearFabricaDao(ObjectDao factory) {
		/** Aplicamos Polimorfismo */
		DaoInterface<?> objetoDAO = factory.crearDao();
		// objetoDAO.add();
		// objetoVehiculo.codigoDeVehiculo();
	}// cierre método crearFabricaDao
}// cierre clase FactoryDao
