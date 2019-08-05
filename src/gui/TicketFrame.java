package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gui.resource.ResourcesGui;

/**
 * Archivo: TicketFrame.java contiene la definición de la clase TicketFrame que
 * extiende de JFrame.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class TicketFrame extends JFrame {
	// declaración de atributos
	private static final int MIN_V = 350;
	private static final int MIN_H = 500;

	private static final String SRC_MENU = "imgs/ic_menu.png";

	private static final String TITLE = "Ticket ";

	private static final long serialVersionUID = 1L;
	// declaración de componentes
	private JPanel contentPanel;
	private JPanel panelTitle;

	private JLabel lbTitle;
	private JLabel lbMenu;

	/**
	 * Constructor con parámetro
	 * 
	 * @param contentPanel objeto de tipo JPanel
	 */
	public TicketFrame(JPanel contentPanel) {
		this.contentPanel = contentPanel;
		this.setLocationRelativeTo(null);
		createGui();
	}// cierre constructor

	// método que crea la vista
	private void createGui() {
		this.setLayout(new BorderLayout());

		// ================== TITLE start ==================
		panelTitle = new JPanel();
		panelTitle.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelTitle.setBackground(ResourcesGui.COLOR.getPrimaryColor());

		lbTitle = new JLabel(TITLE);
		lbTitle.setFont(ResourcesGui.FONT.geFontTitle());
		lbTitle.setForeground(ResourcesGui.COLOR.getSecondColor());
		lbTitle.setBorder(ResourcesGui.BORDER.getBorderTitle());

		lbMenu = new JLabel();
		lbMenu.setIcon(new ImageIcon(SRC_MENU));

		panelTitle.add(lbMenu);
		panelTitle.add(lbTitle);
		// ================== FILTER end ==================
		this.add(panelTitle, BorderLayout.PAGE_START);

		this.setMinimumSize(new Dimension(MIN_V, MIN_H));
		this.add(contentPanel, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}// cierre método createGui

}// cierre clase TicketFrame
