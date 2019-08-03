package gui.content.ticket;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.resource.ResourcesGui;

import observer.IObserver;

public class TicketGui extends JPanel implements IObserver{
	
private static final long serialVersionUID = 1L;
	
	private static final String BTN_ADD = "Agregar";
	private static final String BTN_CANCEL = "Cancelar";	
	
	private static final int BORDER_FORM_H = 20;
	private static final int BORDER_FORM_V = 10;
	
	private static final int GRID_FORM_ROWS = 0;
	private static final int GRID_FROM_COLS = 2;
	
	private static final String SRC_IMG = "imgs/ticket.png";

	private static final int BORDER_BTNS_H = 10;
	private static final int BORDER_BTNS_V = 10;
	//private static final String[] DATE_CAR = { "MY3445", "DJU234", "MIH432"}; 
	//private static final String[] DATE_PEOPLE = { "231ggg@gmail.com", "gahgg@gmail.com", "gkit@gmail.com"}; 
	
	private static final String TEXT_PEOPLE_SELECT = "Seleciona a la persona";
	private static final String TEXT_CAR_SELECT = "Seleciona a automovil";

	
	private static final int GRID_BTN_ROWS = 2;
	private static final int GRID_BTN_COLS = 2;

	private static final String TEXT_PEOPLE = "Email";

	private static final String TEXT_CAR = "Placa";
	
	private JLabel lbImagen;
	private JLabel lbPeople;
	private JLabel lbCar;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	/*private JComboBox<String> cbxPeople;
	private JComboBox<String> cbxCar;
	*/
	
	private JButton btnPeople;
	private JButton btnCar;
	
	
	private JPanel contentButtons;
	private JPanel contentMain;
	private JPanel panelImg;
	private JPanel contentForm;

	
	
	
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
		
		contentForm = new JPanel();
		
		contentForm.setLayout(new GridLayout(GRID_FORM_ROWS, GRID_FROM_COLS, BORDER_FORM_H, BORDER_FORM_V));
		contentForm.setBorder(ResourcesGui.BORDER.getBorderForm());
		contentForm.setBackground(ResourcesGui.COLOR.getSecondColor());
		
		lbPeople	= new JLabel(TEXT_PEOPLE, JLabel.RIGHT);
		lbCar 	= new JLabel(TEXT_CAR, JLabel.RIGHT);
		lbPeople.setFont(ResourcesGui.FONT.getFontText());
		lbCar.setFont(ResourcesGui.FONT.getFontText());
		contentForm.add(lbCar);
		//cbxCar = new JComboBox<>(DATE_CAR);
		
		btnCar = new JButton(TEXT_CAR_SELECT);
		
		contentForm.add(btnCar);
		contentForm.add(lbPeople);
		
		//cbxPeople = new JComboBox<>(DATE_PEOPLE);
		
		btnPeople = new JButton(TEXT_PEOPLE_SELECT);
		contentForm.add(btnPeople);
		
		contentMain.add(contentForm, BorderLayout.CENTER);
			
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


	@Override
	public void update() {
		// TODO Auto-generated method stub
	}


	public JButton getBtnAdd() {
		return btnAdd;
	}


	public JButton getBtnCancel() {
		return btnCancel;
	}


	public JButton getBtnPeople() {
		return btnPeople;
	}


	public JButton getBtnCar() {
		return btnCar;
	}
	
	
	public void resetBtnSelect() {
		btnCar.setText(TEXT_CAR_SELECT);
		btnPeople.setText(TEXT_PEOPLE_SELECT);
	}
	
	public void resetBtnAdd() {
		btnAdd.setText(BTN_ADD);
	}
	

}
