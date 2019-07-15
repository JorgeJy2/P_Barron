package gui.content.car;

import java.awt.GridLayout;
import javax.swing.JPanel;
import gui.resource.ResourcesGui;

public class CarContainerMainGui extends JPanel{

private static final long serialVersionUID = 1L;
	
	public CarContainerMainGui() {
		createGui();
	}
	
	private void createGui() {
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		this.setLayout(new GridLayout(1,2));
		this.add(new CarGuiView());
		this.add(new CarGui());
	}
}
