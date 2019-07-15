package gui.content.ticket;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.content.car.CarGuiView;
import gui.content.people.PeopleGuiView;
import gui.resource.ResourcesGui;

public class TicketContainerMainGui extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public TicketContainerMainGui() {
		createGui();
	}

	private void createGui() {
		
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(new TicketGuiView());
		this.add(new TicketGui());
		
	}
	
	
}
