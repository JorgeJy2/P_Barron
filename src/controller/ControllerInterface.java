package controller;

public interface ControllerInterface { 
	public boolean saveRegistry();
	public boolean filter();
	public boolean updateRegistry();
	public boolean deleteRegistry();
	public boolean getDataOfView();
	public boolean setDataOfView();
	public boolean reloadData(); 
	public boolean addListener();
}
