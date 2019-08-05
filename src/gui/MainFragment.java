package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerWindowAdapter;
import gui.content.car.CarContainerMainGui;
import gui.content.people.PeopleContainerMainGui;
import gui.content.ticket.TicketContainerMainGui;
import gui.resource.ResourcesGui;

public class MainFragment   extends JFrame {

	private static final int MIN_V = 350;
	private static final int MIN_H = 500;
	
	private static final String SRC_MENU = "imgs/ic_menu.png";

	private static  String title = "Automovil ";

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel panelTitle;
	private JPanel panelChange;
	
	private JLabel lbTitle;
	private JLabel lbMenu;
	
	private JPanel panelMenu;
	private JPanel conteint;
	
	public MainFragment(JPanel contentPanel) {		
		this.contentPanel = contentPanel;
		this.setLocationRelativeTo(null);
		createGui();
	}
	
	private void createGui() {
		this.setLayout(new BorderLayout());
		
		// ================== TITLE start ==================
		panelTitle = new JPanel();
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTitle.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		
		lbTitle = new JLabel(title);
		lbTitle.setFont(ResourcesGui.FONT.geFontTitle());
		lbTitle.setForeground( ResourcesGui.COLOR.getSecondColor());
		lbTitle.setBorder(ResourcesGui.BORDER.getBorderTitle());
		
		lbMenu = new JLabel();
		lbMenu.setIcon(new ImageIcon(SRC_MENU));
		
		panelTitle.add(lbMenu);
		panelTitle.add(lbTitle);
		this.add(panelTitle, BorderLayout.PAGE_START);
		// ================== TITLE end ==================
		
		panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		
		panelMenu.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());
		
		JButton btnCar = new JButton();
		btnCar.setIcon(new ImageIcon("imgs/car.png"));
		JButton btnPeople = new JButton("");
		btnPeople.setIcon(new ImageIcon("imgs/people.png"));
		JButton btnTicket = new JButton("");
		btnTicket.setIcon(new ImageIcon("imgs/ticket.png"));
		
		
		btnCar.addActionListener((ActionEvent arg0) -> {
				title = "Automovil";
				lbTitle.setText(title);
				changePanel(new CarContainerMainGui());
		});
		
		btnPeople.addActionListener((ActionEvent arg0) -> {
			title = "Personas";
			lbTitle.setText(title);
				changePanel (new PeopleContainerMainGui());
		});
		
		btnTicket.addActionListener((ActionEvent arg0) -> {
			title = "Boletos";
			lbTitle.setText(title);
				changePanel (new TicketContainerMainGui());
		});
		
		btnCar.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnCar.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnCar.setForeground(ResourcesGui.COLOR.getSecondColor());

		btnPeople.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnPeople.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnPeople.setForeground(ResourcesGui.COLOR.getSecondColor());
		
		btnTicket.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnTicket.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnTicket.setForeground(ResourcesGui.COLOR.getSecondColor());
		
		JPanel menuTitle= new JPanel();
		menuTitle.setLayout(new GridLayout(0,1));
		JLabel titleSubTitle = new JLabel("Estacionamiento");
		titleSubTitle.setFont(ResourcesGui.FONT.geFontTitle());
		JLabel titleMenu = new JLabel("ProCar");
		titleMenu.setFont(ResourcesGui.FONT.getFontText());
		
		menuTitle.add(titleSubTitle);
		menuTitle.add(titleMenu);
	
		panelMenu.add(menuTitle, BorderLayout.PAGE_START);
		
		JPanel panelOption = new JPanel();
		panelOption.setLayout(new GridLayout(0,1,30,10));
		panelOption.add(btnCar);
		panelOption.add(btnPeople);
		panelOption.add(btnTicket);
		
		panelMenu.add(panelOption, BorderLayout.CENTER);
		
		
		JPanel piedPaginaTitle= new JPanel();
		piedPaginaTitle.setLayout(new GridLayout(0,1));
		JLabel info = new JLabel("Versión 1.0");
		piedPaginaTitle.add(info);
			
		
		panelMenu.add(piedPaginaTitle, BorderLayout.PAGE_END);
		
		conteint = new JPanel();	
		conteint.setLayout(new BorderLayout());
		conteint.add(panelMenu, BorderLayout.LINE_START);
		panelChange = new JPanel();
		panelChange.setLayout(new BorderLayout());
		panelChange.add(contentPanel,BorderLayout.CENTER);
		conteint.add(panelChange, BorderLayout.CENTER);
		
		
		this.setMinimumSize(new Dimension(MIN_V, MIN_H));
		this.add(conteint, BorderLayout.CENTER);
		this.pack();	
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		this.addWindowListener(new ControllerWindowAdapter());
	
	}
	
	private void changePanel(JPanel panel) {
		panelChange.removeAll();
		panelChange.add(panel);
		panelChange.revalidate();
		panelChange.repaint();
	}
	
}
