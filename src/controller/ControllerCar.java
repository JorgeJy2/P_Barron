package controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.DaoCar;
import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import gui.content.car.CarGui;
import gui.content.car.CarGuiView;
import model.dto.DtoCar;
import model.list.ListCar;

public class ControllerCar extends ControllerWindow{

	private static ControllerCar _instance = null;
	 
	private Frame mainFragment;
	private CarContainerMainGui viewCar; 
	private CarGuiView carGuiView;
	private CarGui carGui;
	
	public static ControllerCar getInstance() {
		if (_instance == null) {
			_instance = new ControllerCar();
		}
		return _instance;
	}
	 
	@Override
	public boolean saveRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createNewRegistry() { 
		return false;
	}

	@Override
	public boolean updateRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDataOneRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDataAllRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getDataOfView() { 
		//carGuiView.getTxt
		return false;
	}

	@Override
	public boolean setDataOfView() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean reloadData() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == carGuiView.getBtnFilter()) {
			 JOptionPane.showMessageDialog(null, "filter");
		} else if(e.getSource() == carGui.getBtnCancel()) {
			 JOptionPane.showMessageDialog(null, "Cancel");
		}else if(e.getSource() == carGui.getBtnAdd()) {
			 JOptionPane.showMessageDialog(null, "ADD");
		}
	}
	@Override
	public boolean initView() {
		viewCar = new CarContainerMainGui();
		mainFragment = new MainFragment(viewCar);  
		carGuiView = viewCar.getCarGuiView();
		carGui = viewCar.getCarGui();
		return true;
	}
 
}
