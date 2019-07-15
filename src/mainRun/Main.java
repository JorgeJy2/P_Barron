package mainRun;


import gui.CarFrame;
import gui.TicketFrame;
import gui.content.car.CarContainerMainGui;
import gui.content.ticket.TicketContainerMainGui;

public class Main {

	public static void main(String[] args) {
			//new MainFrame(new PeopleContainerMainGui());
			new CarFrame(new CarContainerMainGui());
			new TicketFrame(new TicketContainerMainGui());
		
	}
}
