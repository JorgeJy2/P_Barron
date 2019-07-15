package mainRun;

import java.util.List;

import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import model.list.FilterList;
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
		FilterList filterList = new FilterList("M2Z3S");
		List filter = filterList.getList();
		filter.stream().forEach(System.out::println);
		//testCar.updateTest();
		//testCar.delteTest();
		//testCar.selectTest();
		
		
	/*	
		TestPeople  testPeople =  new TestPeople();
		testPeople.selectTest();
		//testPeople.addTest();
		//testPeople.updateTest();
		testPeople.delteTest();
		testPeople.selectTest();
		*/
		
	}
}
