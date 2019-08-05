package factory;

import dao.DaoCar;
import dao.DaoInterface;

/**
 * Archivo: FactoryCar.java contiene la definición de la clase FactoryCar que
 * implementa ObjectDao
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class FactoryCar implements ObjectDao {

	/**
	 * Método crearDao
	 * 
	 * @return retorna un objeto.
	 */
	@Override
	public DaoInterface<?> crearDao() {
		DaoCar daoCar = new DaoCar();
		return daoCar;
	}// cierre método crearDao

}// cierre clase FactoryCar
