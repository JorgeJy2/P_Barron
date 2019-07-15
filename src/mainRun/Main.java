package mainRun;


import java.sql.SQLException;

import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import model.list.ListCar;
import model.list.ListPeople;
import model.list.interador.Interator;

public class Main {

	public static void main(String[] args) {
			//new MainFrame(new PeopleContainerMainGui());
			new MainFragment(new CarContainerMainGui());
			//new TicketFrame(new TicketContainerMainGui());
			ListCar listCar =ListCar.getInstance();
			try {
				listCar.loadList();
				//listCar.getList().stream().forEach(System.out::println);
				Interator inte =  listCar.getCars();
				while(inte.hasNext()) {
					System.out.println(inte.now());
					System.out.println(inte.next());
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(e.getLocalizedMessage());
			}
			
	}
}
