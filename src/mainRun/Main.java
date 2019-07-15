package mainRun;

import java.sql.SQLException;
import java.util.List;

import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import model.list.FilterCar;
import test.TestCar;
import test.TestPeople;

public class Main {

	public static void main(String[] args) {
		new MainFragment(new CarContainerMainGui());
		
		/**
		 * ========= TEST LIST CAR =============
		 */
	
		TestCar  testCar =  new TestCar();
		//testCar.addTest();
		testCar.selectTest();
		FilterCar filterList = new FilterCar("M3443A");
		try {
			filterList.loadList();
			List filter = filterList.getList();
			System.out.println("Filtro");
			filter.stream().forEach(System.out::println);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error en filtro");
		}
		
		
		
		//testCar.updateTest();
		//testCar.delteTest();
		//testCar.selectTest();
		
		/*
		TestPeople  testPeople =  new TestPeople();
		testPeople.selectTest();
		testPeople.addTest();
		//testPeople.updateTest();
		//testPeople.delteTest();
		testPeople.selectTest();
	*/
		
	}
}
