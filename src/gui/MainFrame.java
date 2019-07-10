package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame  extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	
	public MainFrame(JPanel contentPanel) {
		this.contentPanel = contentPanel;
		this.setLocationRelativeTo(null);
		createGui();
	}
	
	private void createGui() {
		
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(350, 450));
		this.add(contentPanel, BorderLayout.CENTER);
		//this.pack();
		this.setSize(600,500);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
}
