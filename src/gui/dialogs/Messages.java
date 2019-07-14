package gui.dialogs;

import javax.swing.JOptionPane;

public class Messages {

	public static void showMessage(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public static void showError(String msg){
		
		msg="ERROR"+msg+"\n please read load File.";
		JOptionPane.showMessageDialog(null, msg);	
	}
	
}
