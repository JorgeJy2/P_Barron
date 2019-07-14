package factory;

import dao.DaoCar;
import dao.DaoInterface;

public class FactoryCar implements ObjectDao{

	@Override
	public DaoInterface<?> crearDao() {
		DaoCar daoCar = new DaoCar();
		return daoCar;
	}

}
