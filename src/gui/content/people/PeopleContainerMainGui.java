package gui.content.people;

import java.awt.GridLayout;

import javax.swing.JPanel;

import controller.ControllerPeople;
import gui.resource.ResourcesGui;

/**
 * Archivo: PeopleContainerMainGui.java contiene la definición de la clase
 * PeopleContainerMainGui que extiende de JPanel
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class PeopleContainerMainGui extends JPanel {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private PeopleGuiView peopleGuiView;
	private PeopleGui peopleGui;

	// constructor sin parámetros
	public PeopleContainerMainGui() {
		createGui();
		new ControllerPeople(this);
	}// cierre constructor

	// método que crea la vista
	private void createGui() {
		this.peopleGuiView = new PeopleGuiView();
		this.peopleGui = new PeopleGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.add(this.peopleGuiView);
		this.add(this.peopleGui);
	}// cierre método createGui

	// getters
	public PeopleGuiView getPeopleGuiView() {
		return peopleGuiView;
	}

	public PeopleGui getPeopleGui() {
		return peopleGui;
	}

}// cierre clase PeopleContainerMainGui
