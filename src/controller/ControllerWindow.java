package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;

/**
 * Archivo: ControllerWindow.java contiene la definición de la clase abstracta
 * ControllerWindow que extiende de KeyAdapter e implementa de ActionListener,
 * ControllerInterface.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public abstract class ControllerWindow extends KeyAdapter implements ActionListener, ControllerInterface {

	// método actionPerformed
	@Override
	public void actionPerformed(ActionEvent e) {

	}// cierre método actionPerformed

}// cierre clase ControllerWindow
