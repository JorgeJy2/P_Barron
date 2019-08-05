package gui.content.people;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.resource.ResourcesGui;
import observer.IObserver;

/**
 * Archivo: PeopleGui.java contiene la definición de la clase PeopleGui que
 * extiende de JPanel e implementa de IObserver.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class PeopleGui extends JPanel implements IObserver {
	// declaración de atributos
	private static final long serialVersionUID = 1L;

	private static final String BTN_ADD = "Agregar";
	private static final String BTN_CANCEL = "Cancelar";

	private static final String TEXT_NAME = "Nombre";
	private static final String TEXT_FIRST_NAME = "Apellido paterno";
	private static final String TEXT_LAST_NAME = "Apellido materno";
	private static final String TEXT_TELEPHONE = "Teléfono";
	private static final String TEXT_EMAIL = "Correo";

	private static final String SRC_IMG = "imgs/people.png";
	private static final String SRC_IMG_DEL = "imgs/borrar.png";
	private static final String SRC_IMG_REPORT = "imgs/report.png";

	private static final int BORDER_BTNS_H = 10;
	private static final int BORDER_BTNS_V = 10;

	private static final int BORDER_FORM_H = 20;
	private static final int BORDER_FORM_V = 10;

	private static final int GRID_FORM_ROWS = 0;
	private static final int GRID_FROM_COLS = 2;

	private static final int GRID_BTN_ROWS = 1;
	private static final int GRID_BTN_COLS = 2;

	// declaración de componentes
	private JLabel lbName;
	private JLabel lbFirsName;
	private JLabel lbLastName;
	private JLabel lbTelephone;
	private JLabel lbEmail;

	private JLabel lbImagen;

	private JTextField txtName;
	private JTextField txtFirsName;
	private JTextField txtLastName;
	private JTextField txtTelephone;
	private JTextField txtEmail;

	private JButton btnAdd;
	private JButton btnCancel;

	private JButton btnDelete;
	private JButton btnInforme;

	private JPanel contentButtons;
	private JPanel contentForm;
	private JPanel contentMain;
	private JPanel panelImg;

	// constructor sin parámetros
	public PeopleGui() {
		createGui();
	}

	// método que crea la vista
	private void createGui() {

		this.setLayout(new BorderLayout());
		this.setBackground(ResourcesGui.COLOR.getSecondColor());

		btnDelete = new JButton();
		btnDelete.setBackground(null);
		btnDelete.setIcon(new ImageIcon(SRC_IMG_DEL));
		btnDelete.setBorder(null);

		btnInforme = new JButton();
		btnInforme.setBackground(null);
		btnInforme.setIcon(new ImageIcon(SRC_IMG_REPORT));
		btnInforme.setBorder(null);

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

		lbName = new JLabel(TEXT_NAME, JLabel.RIGHT);
		lbFirsName = new JLabel(TEXT_FIRST_NAME, JLabel.RIGHT);
		lbLastName = new JLabel(TEXT_LAST_NAME, JLabel.RIGHT);
		lbTelephone = new JLabel(TEXT_TELEPHONE, JLabel.RIGHT);
		lbEmail = new JLabel(TEXT_EMAIL, JLabel.RIGHT);

		lbName.setFont(ResourcesGui.FONT.getFontText());
		lbFirsName.setFont(ResourcesGui.FONT.getFontText());
		lbLastName.setFont(ResourcesGui.FONT.getFontText());
		lbTelephone.setFont(ResourcesGui.FONT.getFontText());
		lbEmail.setFont(ResourcesGui.FONT.getFontText());

		txtName = new JTextField();
		txtFirsName = new JTextField();
		txtLastName = new JTextField();
		txtTelephone = new JTextField();
		txtEmail = new JTextField();

		txtName.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtFirsName.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtLastName.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtTelephone.setBorder(ResourcesGui.BORDER.getBorderTxt());
		txtEmail.setBorder(ResourcesGui.BORDER.getBorderTxt());

		txtName.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtFirsName.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtLastName.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtTelephone.setForeground(ResourcesGui.COLOR.getAcentColor());
		txtEmail.setForeground(ResourcesGui.COLOR.getAcentColor());

		txtName.setFont(ResourcesGui.FONT.getFontText());
		txtFirsName.setFont(ResourcesGui.FONT.getFontText());
		txtLastName.setFont(ResourcesGui.FONT.getFontText());
		txtTelephone.setFont(ResourcesGui.FONT.getFontText());
		txtEmail.setFont(ResourcesGui.FONT.getFontText());

		contentForm.add(lbName);
		contentForm.add(txtName);
		contentForm.add(lbFirsName);
		contentForm.add(txtFirsName);
		contentForm.add(lbLastName);
		contentForm.add(txtLastName);
		contentForm.add(lbEmail);
		contentForm.add(txtEmail);
		contentForm.add(lbTelephone);
		contentForm.add(txtTelephone);

		contentForm.add(btnDelete);
		contentForm.add(btnInforme);

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

	}// cierre método createGui

	// getters
	public JTextField getTxtName() {
		return txtName;
	}

	public JTextField getTxtFirsName() {
		return txtFirsName;
	}

	public JTextField getTxtLastName() {
		return txtLastName;
	}

	public JTextField getTxtTelephone() {
		return txtTelephone;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public JButton getBtnCancel() {
		return btnCancel;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnInforme() {
		return btnInforme;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}// cierre clase PeopleGui
