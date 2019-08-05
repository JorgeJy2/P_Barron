package gui.dialogs;

import java.awt.BorderLayout;

import java.awt.Container;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.resource.ResourcesGui;

/**
 * Archivo: Messages.java contiene la definición de la clase Messages
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class Messages {
	// declaración de atributos
	private static final String PATH_IMG_ERR = "imgs/aversion.png";
	private static final String TITLE_ERR = "¡Lo sentimos ocurrió un error!";
	private static final String I_UNDESTAND = "Entiendo";

	private static final String PATH_IMG_INFO = "imgs/como.png";
	private static final String TITLE_INFO = "Información";

	/**
	 * Método showMessage
	 * 
	 * @param msg valor de tipo String
	 */
	public static void showMessage(String msg) {

		JDialog dialog = new JDialog(new JFrame(), TITLE_INFO);

		dialog.setBackground(ResourcesGui.COLOR.getSecondColor());

		JPanel topLeft = new JPanel();
		topLeft.setBackground(ResourcesGui.COLOR.getSecondColor());
		topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		topLeft.add(new JLabel(new ImageIcon(PATH_IMG_INFO)));

		JPanel topRight = new JPanel();
		topRight.setBackground(ResourcesGui.COLOR.getSecondColor());
		topRight.setLayout(new BorderLayout());
		topRight.add(new JLabel(msg, JLabel.CENTER), BorderLayout.EAST);

		JPanel top = new JPanel();
		top.setBackground(ResourcesGui.COLOR.getSecondColor());
		top.setLayout(new BorderLayout(25, 0));
		top.add(topLeft, BorderLayout.WEST);
		top.add(topRight, BorderLayout.EAST);

		JButton button = new JButton(I_UNDESTAND);
		// TODO: Lambdas.
		button.addActionListener((ae) -> dialog.setVisible(false));

		button.setBackground(ResourcesGui.COLOR.getPrimaryColor());

		button.setForeground(ResourcesGui.COLOR.getSecondColor());

		JPanel dialogPanel = new JPanel();
		dialogPanel.setBackground(ResourcesGui.COLOR.getSecondColor());

		dialogPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 10));

		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(top, BorderLayout.NORTH);
		dialogPanel.add(button, BorderLayout.SOUTH);
		Container cp = dialog.getContentPane();

		cp.add(dialogPanel);

		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	}// cierre método showMessage

	/**
	 * Método showError
	 * 
	 * @param msg valor de tipo String
	 */
	public static void showError(String msg) {

		JDialog dialog = new JDialog(new JFrame(), TITLE_ERR);

		dialog.setBackground(ResourcesGui.COLOR.getSecondColor());

		JPanel topLeft = new JPanel();
		topLeft.setBackground(ResourcesGui.COLOR.getSecondColor());
		topLeft.setLayout(new FlowLayout(FlowLayout.LEFT));
		topLeft.add(new JLabel(new ImageIcon(PATH_IMG_ERR)));

		JPanel topRight = new JPanel();
		topRight.setBackground(ResourcesGui.COLOR.getSecondColor());
		topRight.setLayout(new BorderLayout());
		topRight.add(new JLabel(msg, JLabel.CENTER), BorderLayout.EAST);

		JPanel top = new JPanel();
		top.setBackground(ResourcesGui.COLOR.getSecondColor());
		top.setLayout(new BorderLayout(25, 0));
		top.add(topLeft, BorderLayout.WEST);
		top.add(topRight, BorderLayout.EAST);

		JButton button = new JButton(I_UNDESTAND);
		// TODO: Lambdas.
		button.addActionListener((ae) -> dialog.setVisible(false));

		button.setBackground(ResourcesGui.COLOR.getWaringColor());

		button.setForeground(ResourcesGui.COLOR.getSecondColor());

		JPanel dialogPanel = new JPanel();
		dialogPanel.setBackground(ResourcesGui.COLOR.getSecondColor());

		dialogPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 10));

		dialogPanel.setLayout(new BorderLayout());
		dialogPanel.add(top, BorderLayout.NORTH);
		dialogPanel.add(button, BorderLayout.SOUTH);
		Container cp = dialog.getContentPane();

		cp.add(dialogPanel);

		dialog.setResizable(false);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);

	}// cierre método showError

}// cierre clase Messages
