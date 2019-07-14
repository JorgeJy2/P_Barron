package factory;

import dao.DaoInterface;
import dao.DaoPeople;

public class FactoryPeople implements ObjectDao{

	@Override
	public DaoInterface<?> crearDao() {
		DaoPeople daoPeople=new DaoPeople();
		return daoPeople;
	}

}
