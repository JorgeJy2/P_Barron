package gui.content.ticket;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.resource.ResourcesGui;

public class TicketGui extends JPanel{
	
private static final long serialVersionUID = 1L;
	
	private static final String BTN_ADD = "Agregar";
	private static final String BTN_CANCEL = "Cancelar";	
	
	private static final String SRC_IMG = "imgs/ticket.png";

	private static final int BORDER_BTNS_H = 10;
	private static final int BORDER_BTNS_V = 10;
	
	
	private static final int GRID_BTN_ROWS = 2;
	private static final int GRID_BTN_COLS = 2;
	
	private JLabel lbImagen;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	private JPanel contentButtons;
	private JPanel contentMain;
	private JPanel panelImg;
	
	
	public TicketGui() {
		createGui();
	}
	
	
	private void createGui() {
	
		this.setLayout(new BorderLayout());
		this.setBackground(ResourcesGui.COLOR.getSecondColor());
		
		contentMain = new JPanel();
		contentMain.setLayout(new BorderLayout());
		contentMain.setBorder(ResourcesGui.BORDER.getBorderConteinerMain());
		contentMain.setBackground(ResourcesGui.COLOR.getSecondColor());

		panelImg = new JPanel();
		panelImg.setBackground(ResourcesGui.COLOR.getSecondColor());

		lbImagen = new JLabel();
		lbImagen.setIcon(new ImageIcon(SRC_IMG));
		
		panelImg.add(lbImagen);
		
		contentMain.add(panelImg, BorderLayout.PAGE_START);
			
		contentButtons = new JPanel();
		contentButtons.setLayout(new GridLayout(GRID_BTN_ROWS, GRID_BTN_COLS, BORDER_BTNS_H, BORDER_BTNS_V));
		contentButtons.setBackground(ResourcesGui.COLOR.getSecondColor());
		
		btnAdd = new JButton(BTN_ADD);
		btnCancel = new JButton(BTN_CANCEL);
		
		btnAdd.setBackground(ResourcesGui.COLOR.getPrimaryColor());
		btnCancel.setBackground(ResourcesGui.COLOR.getSecondColor());
		
		btnAdd.setBorder(ResourcesGui.BORDER.getBorderBtnAcept());
		btnCancel.setBorder(ResourcesGui.BORDER.getBorderBtnCancel());
		
		btnAdd.setForeground(ResourcesGui.COLOR.getSecondColor());
		btnCancel.setForeground(ResourcesGui.COLOR.getAcentColor());
		
		contentButtons.add(btnAdd);
		contentButtons.add(btnCancel);
		
		contentMain.add(contentButtons, BorderLayout.PAGE_END);
		
		this.add(contentMain, BorderLayout.CENTER);
		
	}

}
