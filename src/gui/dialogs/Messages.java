package gui.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Messages {

	public static void showMessage(String msg){
		 ImageIcon icon = new ImageIcon("imgs/como.png");
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(102, 205, 130));
	        panel.setSize(new Dimension(200, 100));
	        panel.setLayout(null);

	        JLabel label = new JLabel(msg);
	        label.setBounds(0, 0, 200, 64);
	        label.setFont(new Font("Arial", Font.BOLD, 11));
	        label.setHorizontalAlignment(SwingConstants.CENTER);
	        panel.add(label);
	        UIManager.put("OptionPane.minimumSize",new Dimension(300, 100));        
	        JOptionPane.showMessageDialog(null, panel, "Información", JOptionPane.INFORMATION_MESSAGE, icon);
	}
	
	public static void showError(String msg){
		msg=msg+"\r\n please read load File.";
		 ImageIcon icon = new ImageIcon("imgs/aversion.png");
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 0, 0));
	        panel.setSize(new Dimension(240, 100));
	        panel.setLayout(null);
	        JLabel label = new JLabel(msg);
	        label.setBounds(0, 0, 260, 60);
	        label.setFont(new Font("Arial", Font.BOLD, 11));
	        label.setHorizontalAlignment(SwingConstants.HORIZONTAL);
	        panel.add(label);
	        UIManager.put("OptionPane.minimumSize",new Dimension(350, 100));        
	        JOptionPane.showMessageDialog(null, panel, "Información de ERROR", JOptionPane.ERROR_MESSAGE, icon);

	}
	
}
