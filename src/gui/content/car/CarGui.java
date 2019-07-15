package gui.content.car;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.resource.ResourcesGui;

public class CarGui extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private static final String BTN_ADD = "Agregar";
	private static final String BTN_CANCEL = "Cancelar";
	//, , 
	private static final String TEXT_MODELO = "Modelo";
	private static final String TEXT_PLACA = "Placa";
	private static final String TEXT_COLOR = "Color";
	
	private static final String SRC_IMG = "imgs/car.png";

	private static final int BORDER_BTNS_H = 10;
	private static final int BORDER_BTNS_V = 10;
	
	private static final int BORDER_FORM_H = 20;
	private static final int BORDER_FORM_V = 10;
	
	private static final int GRID_FORM_ROWS = 0;
	private static final int GRID_FROM_COLS = 2;
	
	private static final int GRID_BTN_ROWS = 1;
	private static final int GRID_BTN_COLS = 2;
	
	
	private JLabel lbModelo;
	private JLabel lbPlaca;
	private JLabel lbColor;
	
	private JLabel lbImagen;
	
	
	private JTextField txtModelo;
	private JTextField txtPlaca;
	private JTextField txtColor;
	
	private JButton btnAdd;
	private JButton btnCancel;
	
	private JPanel contentButtons;
	private JPanel contentForm;
	private JPanel contentMain;
	private JPanel panelImg;
	
	
	public CarGui() {
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
		
		lbModelo 		= new JLabel(TEXT_MODELO, JLabel.RIGHT);
		lbPlaca 	= new JLabel(TEXT_PLACA, JLabel.RIGHT);
		lbColor 	= new JLabel(TEXT_COLOR, JLabel.RIGHT);
		
		lbModelo.setFont(ResourcesGui.FONT.getFontText());
		lbPlaca.setFont(ResourcesGui.FONT.getFontText());
		lbColor.setFont(ResourcesGui.FONT.getFontText());
		
		txtModelo 		= new JTextField();
		txtPlaca 	= new JTextField();
		txtColor 	= new JTextField();
		
		txtModelo.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtPlaca.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtColor.setBorder(ResourcesGui.BORDER.getBorderTxt());
       
        txtModelo.setForeground(ResourcesGui.COLOR.getAcentColor());
        txtPlaca.setForeground(ResourcesGui.COLOR.getAcentColor());
        txtColor.setForeground(ResourcesGui.COLOR.getAcentColor());
        
        
        txtModelo.setFont(ResourcesGui.FONT.getFontText());
        txtPlaca.setFont(ResourcesGui.FONT.getFontText());
        txtColor.setFont(ResourcesGui.FONT.getFontText());
        
		contentForm.add(lbModelo);
		contentForm.add(txtModelo);
		contentForm.add(lbPlaca);
		contentForm.add(txtPlaca);
		contentForm.add(lbColor);
		contentForm.add(txtColor);
		
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
}
