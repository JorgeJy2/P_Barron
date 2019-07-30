package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import dao.DaoCar;
import gui.content.car.CarContainerMainGui;
import gui.content.car.CarGui;
import gui.content.car.CarGuiView;
import gui.dialogs.Messages;
import model.dto.DtoCar;
import model.list.ListCar;
import model.list.interador.Interator; 

public class ControllerCar extends ControllerWindow {
	 
	private CarContainerMainGui viewCar; 
	private CarGuiView carGuiView;
	private CarGui carGui;
	private DaoCar daoCar;
	private DtoCar dtoCar;
	private int indexSelectOnView;
	private MauseClickedOnTable mauseClickedOnTable;
	
	public ControllerCar(CarContainerMainGui viewCar) {
		this.viewCar = viewCar;
		mauseClickedOnTable = new MauseClickedOnTable(this);
		carGuiView = this.viewCar.getCarGuiView();
		carGui = this.viewCar.getCarGui();
		daoCar = new DaoCar();
		addListener();
		loadTable();
	
	}
	@Override
	public boolean saveRegistry() {
		dtoCar = new DtoCar(); 
		try {
			getDataOfView();
			daoCar.add(dtoCar);
			return true;
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean createNewRegistry() { 
		return false;
	}

	@Override
	public boolean updateRegistry() {
		try {
			daoCar.update(dtoCar);
			return true;
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
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
		try {
			dtoCar.setColor(carGui.getTxtColor().getText());
			dtoCar.setModelo(carGui.getTxtModelo().getText());
			dtoCar.setPlaca(carGui.getTxtPlaca().getText());
			loadTable();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

	public boolean loadTable() { 
		carGuiView.setListCar(ListCar.getInstance());
        try {
        	carGuiView.getListCar().loadList();
        	String[][] data= new String[carGuiView.getListCar().sizeDtos()][5]; 
            Interator<DtoCar> inte =  carGuiView.getListCar().getAll(); 
    		while(inte.hasNext()) {
    			int pointerCar = inte.now();
    			DtoCar car =inte.next();
    			data[pointerCar][0] = car.getModelo();
            	data[pointerCar][1] = car.getPlaca();
            	data[pointerCar][2] = car.getColor();
    		} 
        	carGuiView.setModelTable(data);
        	carGuiView.getTable().addKeyListener(this);
        	carGuiView.getTable().addMouseListener(mauseClickedOnTable);
        	
        	return true;
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
			return false;
		}  
	}
	
	@Override
    public void keyReleased( KeyEvent d ) { 
       if( carGuiView.getTable().getSelectedRows().length > 0 ) { 
         indexSelectOnView = carGuiView.getTable().getSelectedRow();
         dtoCar = carGuiView.getListCar().getList().get(indexSelectOnView);
         dtoCar.setModelo(carGuiView.getTable().getValueAt(indexSelectOnView, 0).toString());
         dtoCar.setPlaca(carGuiView.getTable().getValueAt(indexSelectOnView, 1).toString());
         dtoCar.setColor(carGuiView.getTable().getValueAt(indexSelectOnView, 2).toString());
       }
       updateRegistry();
    }

	@Override
	public boolean setDataOfView() {
		dtoCar = carGuiView.getListCar().getList().get(indexSelectOnView);
		carGui.getTxtColor().setText(dtoCar.getColor());
		carGui.getTxtModelo().setText(dtoCar.getModelo());
		carGui.getTxtPlaca().setText(dtoCar.getPlaca());
		carGui.getBtnAdd().setText("Modificar");
		return false;
	}

	@Override
	public boolean reloadData() {
		 
		return false;
	}
	
	@Override
	public boolean addListener() { 
		carGuiView.getBtnFilter().addActionListener(this);
		carGui.getBtnAdd().addActionListener(this);
		carGui.getBtnCancel().addActionListener(this);
	    return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) { 
		if (e.getSource() == carGuiView.getBtnFilter()) {
			 JOptionPane.showMessageDialog(null, "filter");
		} else if(e.getSource() == carGui.getBtnCancel()) {
			 JOptionPane.showMessageDialog(null, "Cancel");
		}else if(e.getSource() == carGui.getBtnAdd()) {
			saveRegistry();  
			 JOptionPane.showMessageDialog(null, "Save OK");
			 loadTable();
			 
		}
	}
	
	private class MauseClickedOnTable extends MouseAdapter{
		private ControllerCar controllerCar;
		public MauseClickedOnTable(ControllerCar controllerCar) {
			this.controllerCar = controllerCar;
		}
		public void mouseClicked(MouseEvent evnt)
		{
			  if (evnt.getClickCount() == 1)
			  {
				  this.controllerCar.indexSelectOnView = this.controllerCar.carGuiView.getTable().getSelectedRow();
				  this.controllerCar.setDataOfView();
			  }
		}
	}
 
}
