package controller;

import java.awt.Frame;

import gui.MainFragment;
import gui.content.car.CarContainerMainGui;
import gui.content.car.CarGui;
import gui.content.car.CarGuiView;

public class ControllerCar implements ControllerInterface{

	private Frame mainFragment;
	private CarContainerMainGui viewCar;
	
	private CarGuiView carGuiView;
	private CarGui carGui;
	public ControllerCar() {
		viewCar = new CarContainerMainGui();
		mainFragment = new MainFragment(viewCar); 
		
		carGuiView = viewCar.getCarGuiView();
		carGui = viewCar.getCarGui();
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
		viewCar.
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
 
}
