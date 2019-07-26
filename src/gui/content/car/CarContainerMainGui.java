package gui.content.car;

import java.awt.GridLayout;
import javax.swing.JPanel;

import controller.ControllerCar;
import gui.resource.ResourcesGui;

public class CarContainerMainGui extends JPanel{

private static final long serialVersionUID = 1L;
	
	private CarGuiView carGuiView;
	private CarGui carGui;
	public CarContainerMainGui() {
		createGui();
	}
	
	private void createGui() {
		this.carGuiView = new CarGuiView();
		this.carGui = new CarGui();
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(this.carGuiView);
		this.add(this.carGui);
	}

	public CarGuiView getCarGuiView() {
		return carGuiView;
	}

	public CarGui getCarGui() {
		return carGui;
	}
	
}
