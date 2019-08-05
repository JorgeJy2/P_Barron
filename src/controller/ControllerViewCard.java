package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

import java.sql.SQLException;

import java.util.concurrent.Executor;

import javax.swing.JScrollBar;

import controller.concurrente.ExecuterThread;
import gui.content.car.CarGuiView;

import gui.dialogs.Messages;

import model.dto.DtoCar;

import model.list.ListCar;
import model.list.Listable;
import model.list.interador.Interator;

public class ControllerViewCard {

	private CarGuiView carGuiView;

	private Listable<DtoCar> listCar;

	public ControllerViewCard(CarGuiView carGuiView, Listable<DtoCar> listCar) {
		this.carGuiView = carGuiView;
		this.listCar = listCar;
		
		setActionListener();
		addScroll();
	}

	public ControllerViewCard(CarGuiView carGuiView) {
		this.carGuiView = carGuiView;
		this.listCar = ListCar.getInstance();

		if (listCar.sizeDtos() < 0) {
			try {
				listCar.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showMessage(e.getLocalizedMessage());
			}
			System.out.println("Cardado de nuevccco");
		}else {
			System.out.println("Ya contiene datosccc-.");
		}
		setActionListener();
		addScroll();
	}

	
	public void setKeyTable(KeyListener arg0) {
		carGuiView.getTable().addKeyListener(arg0);
	}
	
	public void selectOneRowTable() {
		carGuiView.getTable().setRowSelectionInterval(0, 0);
		carGuiView.getScrollPaneTable().getViewport().setViewPosition(new Point(0, 0));
	}

	public void filterTable() {
		try {
			listCar.loadListFilter(carGuiView.getCbxFilter(), carGuiView.getTxtFilter().getText());
			String[][] data = new String[listCar.sizeDtos()][5];
			Interator<DtoCar> inte = listCar.getAll();
			while (inte.hasNext()) {
				int pointerCar = inte.now();
				DtoCar car = inte.next();
				data[pointerCar][0] = car.getModelo();
				data[pointerCar][1] = car.getPlaca();
				data[pointerCar][2] = car.getColor();
			}
			carGuiView.setModelTable(data);
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
	}
	
	public void updateTable(DtoCar car, int position) {
		carGuiView.getTable().setValueAt(car.getModelo(), position, 0);
		carGuiView.getTable().setValueAt(car.getPlaca(), position, 1);
		carGuiView.getTable().setValueAt(car.getColor(), position, 2);
	}
	
	public int getSelectedRow() {
		return carGuiView.getTable().getSelectedRow() ;
	}
	
	public int getLengthSelectedRows() {
		return carGuiView.getTable().getSelectedRows().length;
	}
	
	public DtoCar getDataSelect(int position) {
		DtoCar dtoCar = new DtoCar();
		 dtoCar.setModelo(carGuiView.getTable().getValueAt(position, 0).toString());
         dtoCar.setPlaca(carGuiView.getTable().getValueAt(position, 1).toString());
         dtoCar.setColor(carGuiView.getTable().getValueAt(position, 2).toString());
         return dtoCar;
	}
	
	
	public void reloadData() {
		String[][] data= new String[listCar.sizeDtos()][5]; 
        Interator<DtoCar> inte =  listCar.getAll(); 
		while(inte.hasNext()) {
			int pointerCar = inte.now();
			DtoCar car =inte.next();
			data[pointerCar][0] = car.getModelo();
        	data[pointerCar][1] = car.getPlaca();
        	data[pointerCar][2] = car.getColor();
		} 
    	carGuiView.setModelTable(data);
	}
	
	public void setActionListener() {
		//TODO: Implementación lambda.
		carGuiView.getBtnFilter().addActionListener((ActionEvent e) -> {
			filterTable();
		});
	
	}
	
	public void addScroll() {
		carGuiView.getScrollPaneTable().getVerticalScrollBar().addAdjustmentListener(new ScrollableTable(this));	
	}
	
	//Inner Class Event to view
	private class ScrollableTable implements AdjustmentListener {
		
		private ControllerViewCard controllerCar; 
		public ScrollableTable(ControllerViewCard controllerCar) {
			this.controllerCar = controllerCar;
		}
		
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			  if(!e.getValueIsAdjusting()){    
	                JScrollBar source = (JScrollBar) e.getAdjustable();
	                int extent = source.getModel().getExtent();
	                int maximum = source.getModel().getMaximum();
	                if(e.getValue() + extent == maximum){
	                	controllerCar.loadNextCars();
	                	System.out.println("llego al final");
	                }
	            }
		}
	}
	
	public void loadNextCars () {
		ExecuterThread  executor = new ExecuterThread();
		executor.execute(() -> {
			try {
				if(listCar.reloadNext()) 
					reloadData();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(" "+e.getLocalizedMessage());
			}
		});

	}
	
	
	public void clearSelection() {
		carGuiView.getTable().clearSelection();
	}

	
	public void setClicTable(MouseAdapter mousAdapter) {
		carGuiView.getTable().addMouseListener(mousAdapter);
	}
	

}
