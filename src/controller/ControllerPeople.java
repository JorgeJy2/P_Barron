package controller;

import java.awt.event.ActionEvent;

import gui.content.people.PeopleGuiView;

public class ControllerPeople extends ControllerWindow{

	private PeopleGuiView view;
	
	
	public ControllerPeople(PeopleGuiView view) {
		this.view = view;
	}

	
	@Override
	public boolean saveRegistry() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean filter() {
		// TODO Auto-generated method stub
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
	public boolean getDataOfView() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addListener() {
		// TODO Auto-generated method stub
		return false;
	}

 

}
