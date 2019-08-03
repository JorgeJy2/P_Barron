package gui.content.people;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.ControllerPeople; 
import gui.resource.ResourcesGui;

public class PeopleContainerMainGui extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private PeopleGuiView peopleGuiView;
	private PeopleGui peopleGui; 
	
	public PeopleContainerMainGui() {
		createGui();
		new ControllerPeople(this);
	}
	
	private void createGui() {
		this.peopleGuiView = new PeopleGuiView();
		this.peopleGui = new PeopleGui(); 
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(this.peopleGuiView);
		this.add(this.peopleGui);
	}

	public PeopleGuiView getPeopleGuiView() {
		return peopleGuiView;
	}

	public PeopleGui getPeopleGui() {
		return peopleGui;
	}
	

}
