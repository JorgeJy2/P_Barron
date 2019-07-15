package mainRun;

import gui.CarFrame;
import gui.MainFrame;
import gui.content.car.CarContainerMainGui;
import gui.content.people.PeopleContainerMainGui;

public class Main {

	public static void main(String[] args) {
			//new MainFrame(new PeopleContainerMainGui());
			new CarFrame(new CarContainerMainGui());
	}
}
