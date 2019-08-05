package gui.content.car;

import java.awt.GridLayout;
import javax.swing.JPanel;

import controller.ControllerCar;
import gui.resource.ResourcesGui;

/**
 * Archivo: CarContainerMainGui.java contiene la definición de la clase
 * CarContainerMainGui que extiende de JPanel.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class CarContainerMainGui extends JPanel {
//declaració de atributos
	private static final long serialVersionUID = 1L;

	private CarGuiView carGuiView;
	private CarGui carGui;

	/**
	 * Constructor sin parámetros
	 */
	public CarContainerMainGui() {
		createGui();
		new ControllerCar(this);
	}// cierre constructor

	// método que crea la vista
	private void createGui() {
		this.carGuiView = new CarGuiView();
		this.carGui = new CarGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1, 2));
		this.add(this.carGuiView);
		this.add(this.carGui);
	}// cierre método createGui

	/**
	 * Método getCarGuiView
	 * 
	 * @return retorna un objeto CarGuiView
	 */
	public CarGuiView getCarGuiView() {
		return carGuiView;
	}// cierre método getCarGuiView

	/**
	 * Método getCarGui
	 * 
	 * @return retorna un objeto CarGui
	 */
	public CarGui getCarGui() {
		return carGui;
	}// cierre método getCarGui

}// cierre clase CarContainerMainGui
