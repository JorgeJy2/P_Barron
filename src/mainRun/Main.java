package mainRun;


import gui.MainFragment;
import gui.content.car.CarContainerMainGui;

public class Main {

	public static void main(String[] args) {
			//new MainFrame(new PeopleContainerMainGui());
			new MainFragment(new CarContainerMainGui());
			//new TicketFrame(new TicketContainerMainGui());
		
	}
}
