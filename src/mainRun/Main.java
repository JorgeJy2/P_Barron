package mainRun;


import java.sql.SQLException;
import java.util.List;

import dao.DaoCar;
import dao.DaoTicket;
import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import gui.content.car.CarGui;
import model.dto.DtoCar;
import model.dto.DtoPeople;
import model.dto.DtoTicket;
import model.list.FilterCar;
import test.TestCar;
import test.TestPeople;

public class Main {

	public static void main(String[] args) {
	//	new MainFragment(new CarContainerMainGui());
		
			/**
			 * ========= TEST LIST CAR =============
			 */
			
		
			
				TestCar  testCar =  new TestCar();
				testCar.addTest();
				testCar.selectTest();
				testCar.updateTest();
				testCar.delteTest();
				testCar.selectTest();
			 
			
			/*
				TestPeople  testPeople =  new TestPeople();
				testPeople.selectTest();
				testPeople.addTest();
				testPeople.updateTest();
				testPeople.delteTest();
				testPeople.selectTest();
			*/
			
	
			/**
			 * ============ TICKE ===============
			 */
			
		/*	
		try {
			
			DaoTicket daoTicket = new DaoTicket();
			
			DtoTicket dtoTicket = new DtoTicket();
			
			TestCar  testCar =  new TestCar();
			DtoCar addCar =testCar.getFirst();
			
			TestPeople  testPeople =  new TestPeople();
			DtoPeople addDtoPeople = testPeople.getFirst();
			
			if(addCar != null && addDtoPeople != null) {
				System.out.println(addCar);
				System.out.println(addDtoPeople);
				
				dtoTicket.setAuto(addCar);
				dtoTicket.setPeople(addDtoPeople);
				daoTicket.add(dtoTicket);
				
				
			}else {
				System.out.println("Datos null para guardar el ticket.");
			}
			
	
			
			
			List<DtoTicket> tickets= daoTicket.getAll();

			for(DtoTicket dtoTicketS : tickets) {
				System.out.println("Ticket");
				System.out.println(dtoTicketS);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error en filtro");
		}
			
			/*
				FilterCar filterList = new FilterCar("M2Z3S");
				filterList.loadList();
				List<?> filter = filterList.getList();
				System.out.println("Filtro");
				filter.stream().forEach(System.out::println);
			*/

			
			
	}
}
