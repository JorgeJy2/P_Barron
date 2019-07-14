package gui.content.people;

import java.awt.GridLayout;

import javax.swing.JPanel;

import gui.resource.ResourcesGui;

public class PeopleContainerMainGui extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public PeopleContainerMainGui() {
		createGui();
	}
	
	private void createGui() {
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(new PeopleGuiView());
		this.add(new PeopleGui());
	}
}
