package factory;

import dao.DaoInterface;

public class FactoryDao {
	 public static void crearFabricaDao(ObjectDao factory){
		  /**Aplicamos Polimorfismo*/
		 DaoInterface<?>  objetoDAO= factory.crearDao();
		 //objetoDAO.add();
		 // objetoVehiculo.codigoDeVehiculo();
		 }
}
