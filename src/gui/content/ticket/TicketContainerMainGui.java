package gui.content.ticket;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.ControllerTicket;
import gui.resource.ResourcesGui;

public class TicketContainerMainGui extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	
	private TicketGui ticketGui;
	private TicketGuiView ticketGuiView;
	
	
	public TicketContainerMainGui() {
		createGui();
		
		new ControllerTicket(this);
	}

	private void createGui() {
		ticketGui = new TicketGui();
		ticketGuiView = new TicketGuiView();
		
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(ticketGuiView);
		this.add(ticketGui);
	}

	public TicketGui getTicketGui() {
		return ticketGui;
	}

	public TicketGuiView getTicketGuiView() {
		return ticketGuiView;
	}
	
	
	
}
