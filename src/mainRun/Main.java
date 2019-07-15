package mainRun;


import java.sql.SQLException;
import java.util.List;

import dao.DaoCar;
import dao.DaoTicket;
import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import model.dto.DtoCar;
import model.dto.DtoPeople;
import model.dto.DtoTicket;
import model.list.FilterCar;
import test.TestCar;
import test.TestPeople;

public class Main {

	public static void main(String[] args) {
		new MainFragment(new CarContainerMainGui());

	
		try {
			
		/**
		 * ========= TEST LIST CAR =============
		 */
		
	
		/*
			TestCar  testCar =  new TestCar();
			testCar.addTest();
			testCar.selectTest();
			testCar.updateTest();
			testCar.delteTest();
			testCar.selectTest();
		 */
		
		/*
			TestPeople  testPeople =  new TestPeople();
			testPeople.selectTest();
			testPeople.addTest();
			testPeople.updateTest();
			testPeople.delteTest();
			testPeople.selectTest();
		*/
		
		DaoTicket daoTicket = new DaoTicket();
		
		
		List<DtoTicket> tickets= daoTicket.getAll();

		for(DtoTicket dtoTicket : tickets) {
			System.out.println("Ticket");
			System.out.println(dtoTicket);
		}
		
		DtoTicket dtoTicket = new DtoTicket();
		dtoTicket.setPeople(new DtoPeople());
		dtoTicket.setAuto(new DtoCar());
		
		/*
			FilterCar filterList = new FilterCar("M2Z3S");
			filterList.loadList();
			List<?> filter = filterList.getList();
			System.out.println("Filtro");
			filter.stream().forEach(System.out::println);
		*/

		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error en filtro");
		}		
	}
}
