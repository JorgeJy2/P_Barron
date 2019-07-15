package mainRun;

import factory.FactoryCar;
import factory.FactoryDao;
import factory.FactoryPeople;
import gui.MainFrame;
import gui.content.people.PeopleContainerMainGui;

public class Main {

	public static void main(String[] args) {


		FactoryPeople factoryPeople = new FactoryPeople();
		FactoryCar factoryCar = new FactoryCar();
		try {
			FactoryDao.crearFabricaDao(factoryPeople);
			FactoryDao.crearFabricaDao(factoryCar);
			
			
		}catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
			new MainFrame(new PeopleContainerMainGui());
			
	}
}
