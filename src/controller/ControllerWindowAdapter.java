package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import connection.PoolConnection;
import gui.dialogs.Messages;

public class ControllerWindowAdapter extends WindowAdapter{		
	public void windowClosing(WindowEvent arg0) {
		try {
			PoolConnection.getInstancePool().closePoolConnection();
		} catch (SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		System.out.println("Adios..");
		System.exit(0);
	}
}