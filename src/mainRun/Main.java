package mainRun;

import gui.MainFragment;
import gui.content.car.CarContainerMainGui;

/**
 * Archivo: Main.java contiene la definición de la clase Main.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class Main {
	// método main
	public static void main(String[] args) {

		// Material Desing.
		/*
		 * JFrame.setDefaultLookAndFeelDecorated(true);
		 * SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.BusinessBlueSteelSkin"
		 * );
		 */
		new MainFragment(new CarContainerMainGui());

		/**
		 * ========= TEST LIST CAR =============
		 */

		/*
		 * 
		 * TestCar testCar = new TestCar(); testCar.addTest(); testCar.selectTest();
		 * testCar.updateTest(); testCar.delteTest(); testCar.selectTest();
		 * 
		 * 
		 * TestPeople testPeople = new TestPeople(); testPeople.selectTest();
		 * testPeople.addTest(); testPeople.updateTest(); testPeople.delteTest();
		 * testPeople.selectTest();
		 */

		/**
		 * ============ TICKE ===============
		 */

		/*
		 * try {
		 * 
		 * DaoTicket daoTicket = new DaoTicket();
		 * 
		 * DtoTicket dtoTicket = new DtoTicket();
		 * 
		 * TestCar testCar = new TestCar(); DtoCar addCar =testCar.getFirst();
		 * 
		 * TestPeople testPeople = new TestPeople(); DtoPeople addDtoPeople =
		 * testPeople.getFirst();
		 * 
		 * if(addCar != null && addDtoPeople != null) { System.out.println(addCar);
		 * System.out.println(addDtoPeople);
		 * 
		 * dtoTicket.setAuto(addCar); dtoTicket.setPeople(addDtoPeople);
		 * daoTicket.add(dtoTicket);
		 * 
		 * 
		 * }else { System.out.println("Datos null para guardar el ticket."); }
		 * 
		 * 
		 * 
		 * 
		 * List<DtoTicket> tickets= daoTicket.getAll();
		 * 
		 * for(DtoTicket dtoTicketS : tickets) { System.out.println("Ticket");
		 * System.out.println(dtoTicketS); }
		 * 
		 * } catch (ClassNotFoundException | SQLException e) {
		 * System.out.println("Error en filtro"); }
		 * 
		 * /* FilterCar filterList = new FilterCar("M2Z3S"); filterList.loadList();
		 * List<?> filter = filterList.getList(); System.out.println("Filtro");
		 * filter.stream().forEach(System.out::println);
		 */

	}// cierre método main
}// cierre clase Main
