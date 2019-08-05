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

/**
 * Archivo: ControllerViewCard.java contiene la definici�n de la clase
 * ControllerViewCard.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class ControllerViewCard {
	// declaraci�n de atributos
	private CarGuiView carGuiView;
	private Listable<DtoCar> listCar;

	/**
	 * Constructor con par�metros
	 * 
	 * @param carGuiView objeto de tipo CarGuiView
	 * @param listCar    objeto de tipo Listable
	 */
	public ControllerViewCard(CarGuiView carGuiView, Listable<DtoCar> listCar) {
		this.carGuiView = carGuiView;
		this.listCar = listCar;

		setActionListener();
		addScroll();
	}// cierre constructor

	/**
	 * Constructor con par�metro
	 * 
	 * @param carGuiView objeto de tipo CarGuiView
	 */
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
		} else {
			System.out.println("Ya contiene datosccc-.");
		}
		setActionListener();
		addScroll();
	}// cierre constructor

	/**
	 * M�todo setKeyTable
	 * 
	 * @param arg0 objeto de tipo KeyListener
	 */
	public void setKeyTable(KeyListener arg0) {
		carGuiView.getTable().addKeyListener(arg0);
	}// cierre m�todo setKeyTable

	/**
	 * M�todo seledtOneRowTable Selecciona una fila de la tabla
	 */
	public void selectOneRowTable() {
		carGuiView.getTable().setRowSelectionInterval(0, 0);
		carGuiView.getScrollPaneTable().getViewport().setViewPosition(new Point(0, 0));
	}// cierre m�todo selectOneRowTable

	/**
	 * M�todo filterTable Filtra la tabla Car
	 */
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
	}// cierre m�todo filterTable

	/**
	 * M�todo updateTable Actualiza tabla
	 * 
	 * @param car      objeto de tipo DtoCar
	 * @param position variable de tipo entero
	 */
	public void updateTable(DtoCar car, int position) {
		carGuiView.getTable().setValueAt(car.getModelo(), position, 0);
		carGuiView.getTable().setValueAt(car.getPlaca(), position, 1);
		carGuiView.getTable().setValueAt(car.getColor(), position, 2);
	}// cierre m�todo updateTable

	/**
	 * M�todo getSelectedRow Obtiene la selecci�n de una fila
	 * 
	 * @return retorna un valor entero
	 */
	public int getSelectedRow() {
		return carGuiView.getTable().getSelectedRow();
	}// cierre m�todo getSelectedRow

	/**
	 * M�todo getLengthSelectedRows Obtiene la longitud de las filas
	 * 
	 * @return retorna un valor entero
	 */
	public int getLengthSelectedRows() {
		return carGuiView.getTable().getSelectedRows().length;
	}// cierre m�todo getLengthSelectedRows

	/**
	 * M�todo getDataSelect Obtiene la informaci�n seleccionada.
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoCar
	 */
	public DtoCar getDataSelect(int position) {
		DtoCar dtoCar = new DtoCar();
		dtoCar.setModelo(carGuiView.getTable().getValueAt(position, 0).toString());
		dtoCar.setPlaca(carGuiView.getTable().getValueAt(position, 1).toString());
		dtoCar.setColor(carGuiView.getTable().getValueAt(position, 2).toString());
		return dtoCar;
	}// cierre m�todo getDataSelect

	/**
	 * M�todo reloadData Recarga los datos
	 */
	public void reloadData() {
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
	}// cierre m�todo reloadData

	/**
	 * M�todo setActionListener
	 */
	public void setActionListener() {
		// TODO: Implementaci�n lambda.
		carGuiView.getBtnFilter().addActionListener((ActionEvent e) -> {
			filterTable();
		});

	}// cierre m�todo setActionListener

	/**
	 * M�todo addScroll M�todo que agrega un scroll
	 */
	public void addScroll() {
		carGuiView.getScrollPaneTable().getVerticalScrollBar().addAdjustmentListener(new ScrollableTable(this));
	}// cierre m�todo addScroll

	/**
	 * Archivo: ControllerViewCard.java contiene la definici�n de la clase
	 * ScrollableTable que implementa AdjustmentListener.
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	// Inner Class Event to view
	private class ScrollableTable implements AdjustmentListener {
		// declaraci�n de atributos
		private ControllerViewCard controllerCar;

		/**
		 * Constructor con parametro
		 * 
		 * @param controllerCar objeto de tipo ControllerViewCard
		 */
		public ScrollableTable(ControllerViewCard controllerCar) {
			this.controllerCar = controllerCar;
		}// cierre constructor

		/**
		 * M�todo adjusmentValueChanged Ajusta la scrollbar dependiendo los valores.
		 * 
		 * @param e objeto de tipo AdjustmentEvent
		 */
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (!e.getValueIsAdjusting()) {
				JScrollBar source = (JScrollBar) e.getAdjustable();
				int extent = source.getModel().getExtent();
				int maximum = source.getModel().getMaximum();
				if (e.getValue() + extent == maximum) {
					controllerCar.loadNextCars();
					System.out.println("llego al final");
				}
			}
		}// cierre m�todo adjustmentValueChanged
	}// cierre clase ScrollableTable

	// M�todo loadNextCars
	public void loadNextCars() {
		Executor executor = new Executor() {
			@Override
			public void execute(Runnable arg0) {
				arg0.run();
			}
		};

		executor.execute(() -> {
			try {
				if (listCar.reloadNext())
					reloadData();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(" " + e.getLocalizedMessage());
			}
		});
	}// cierre m�todo loadNextCars

	/**
	 * M�todo clearSelection Limpia la selecci�n.
	 */
	public void clearSelection() {
		carGuiView.getTable().clearSelection();
	}// cierre m�todo clearSelection

	/**
	 * M�todo setClicTable Escucha cuando se da clic a la tabla
	 * 
	 * @param mousAdapter
	 */
	public void setClicTable(MouseAdapter mousAdapter) {
		carGuiView.getTable().addMouseListener(mousAdapter);
	}// cierre setClicTable

}// cierre clase ControllerViewCard
