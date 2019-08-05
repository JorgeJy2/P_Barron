package controller;

import java.awt.event.ActionEvent;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import gui.content.car.CarContainerMainGui;
import gui.content.car.CarGui;
import gui.content.car.CarGuiView;
import gui.dialogs.Messages;
import model.dto.DtoCar;
import model.list.ListCar;

import model.list.Listable;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;
import report.ReportCar;
import report.ReportTicket;
import report.decoratorComponent.ReportFilterCar;
import report.decoratorComponent.ReportFilterTicket;

/**
 * Archivo: ControllerCar.java contiene la definici�n de la clase ControllerCar
 * y extiende de la clase ControllerWindow.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */

public class ControllerCar extends ControllerWindow {
	// declaraci�n de atributos
	private CarContainerMainGui viewCar;
	private CarGuiView carGuiView;
	private CarGui carGui;

	private DtoCar dtoCar;
	private int indexSelectOnView;

	private boolean newRegistry;

	private Listable<DtoCar> listCar;

	// private ScrollableTable scrollableTable;

	private ControllerViewCard controllerViewCard;

	/**
	 * Constructor ControllerCar que recibe un objeto de CarContainerMainGui.
	 * 
	 * @param viewCar objeto de tipo CarContainerMainGui.
	 */
	public ControllerCar(CarContainerMainGui viewCar) {
		this.viewCar = viewCar;
		listCar = ListCar.getInstance();

		// scrollableTable = new ScrollableTable(this);

		carGuiView = this.viewCar.getCarGuiView();
		controllerViewCard = new ControllerViewCard(carGuiView, listCar);

		controllerViewCard.setClicTable(new MauseClickedOnTable(this));
		controllerViewCard.setKeyTable(this);
		carGui = this.viewCar.getCarGui();

		addListener();
		addScrollTable();

		/*
		 * carGuiView.getTable().addKeyListener(this);
		 * carGuiView.getTable().addMouseListener(mauseClickedOnTable);
		 */
		if (listCar.sizeDtos() <= 0) {
			try {
				listCar.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showMessage(e.getLocalizedMessage());
			}
		}
		reloadData();

	}// cierre constructor

	/**
	 * M�todo saveRegistry(). Registra o modifica un carro.
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean saveRegistry() {
		if (carGui.getBtnAdd().getText().equalsIgnoreCase("Modificar")) {
			if (getDataOfView())
				updateRegistry();

			return true;

		} else {

			dtoCar = new DtoCar();
			try {
				if (getDataOfView()) {
					listCar.add(dtoCar);

					reloadData();
					/*
					 * carGuiView.getTable().setRowSelectionInterval(0,0);
					 * carGuiView.getScrollPaneTable().getViewport().setViewPosition(new
					 * Point(0,0));
					 */
					controllerViewCard.selectOneRowTable();
					return true;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}// cierre m�todo saveRegistry

	/**
	 * M�todo filter(). Llama el m�todo filterTable().
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean filter() {
		/*
		 * try { listCar.loadListFilter(carGuiView.getCbxFilter(),
		 * carGuiView.getTxtFilter().getText()); String[][] data= new
		 * String[listCar.sizeDtos()][5]; Interator<DtoCar> inte = listCar.getAll();
		 * while(inte.hasNext()) { int pointerCar = inte.now(); DtoCar car =inte.next();
		 * data[pointerCar][0] = car.getModelo(); data[pointerCar][1] = car.getPlaca();
		 * data[pointerCar][2] = car.getColor(); } carGuiView.setModelTable(data);
		 * 
		 * return true; } catch (ClassNotFoundException | SQLException e) {
		 * Messages.showError(e.getLocalizedMessage()); return false; }
		 */
		controllerViewCard.filterTable();
		return true;
	}// cierre m�todo filter

	/**
	 * M�todo updateRegistry(). Actualiza la tabla de los registros de car.
	 * 
	 * @return retorna un valor booleano.
	 * @exception Excepci�n de clase y de base de datos.
	 * 
	 */
	@Override
	public boolean updateRegistry() {
		try {

			if (listCar.update(dtoCar, indexSelectOnView)) {
				/*
				 * carGuiView.getTable().setValueAt(dtoCar.getModelo(), indexSelectOnView, 0);
				 * carGuiView.getTable().setValueAt(dtoCar.getPlaca(), indexSelectOnView, 1);
				 * carGuiView.getTable().setValueAt(dtoCar.getColor(), indexSelectOnView, 2);
				 */
				controllerViewCard.updateTable(dtoCar, indexSelectOnView);
				// setDataOfView();
				newRegistry = false;
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}

		return false;
	}// cierre m�todo update

	/**
	 * M�todo deleteRegistry(). Elimina un registro de la tabla Car.
	 * 
	 * @return retorna un valor booleano
	 * @exception Excepcion de clase y de base de datos.
	 */
	@Override
	public boolean deleteRegistry() {
		try {
			// dtoCar = listCar.getOne(indexSelectOnView);
			listCar.delete(indexSelectOnView);
			// if (carGuiView.getTable().getSelectedRow() < 0) {
			if (controllerViewCard.getSelectedRow() < 0) {
				newRegistry = true;
			} else {
				newRegistry = false;
				// dtoCar = listCar.getOne(carGuiView.getTable().getSelectedRow());
				dtoCar = listCar.getOne(controllerViewCard.getSelectedRow());
			}
			listCar.loadList();
			reloadData();
			setDataOfView();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getMessage());
			return false;
		}

	}// cierre m�todo deleteRegistry

	/**
	 * M�todo getDataOfView
	 * 
	 * @return retona un valor booleano.
	 */
	@Override
	public boolean getDataOfView() {
		try {
			if (validateFieldText(carGui.getTxtColor().getText())) {
				dtoCar.setColor(carGui.getTxtColor().getText());
			} else {
				Messages.showError("Campo Color inv�lido");
				return false;
			}
			if (validateFieldText(carGui.getTxtModelo().getText())) {
				dtoCar.setModelo(carGui.getTxtModelo().getText());
			} else {
				Messages.showError("Campo Modelo inv�lido");
				return false;
			}
			if (validateFieldText(carGui.getTxtPlaca().getText())) {
				dtoCar.setPlaca(carGui.getTxtPlaca().getText());
			} else {
				Messages.showError("Campo Placa inv�lido");
				return false;
			}
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�todo getDataOfView

	/**
	 * M�todo validateFieldText().
	 * 
	 * @param text valor de tipo String
	 * @return valor de retorno booleano
	 */
	private boolean validateFieldText(String text) {
		if (text.length() < 1 || text.equals(""))
			return false;

		return true;
	}// cierre m�todo validateFieldText

	/**
	 * M�todo keyReleased
	 * 
	 * @param d de tipo KeyEvent
	 */
	@Override
	public void keyReleased(KeyEvent d) {
		// if( carGuiView.getTable().getSelectedRows().length > 0 ) {
		if (controllerViewCard.getLengthSelectedRows() > 0) {
			// indexSelectOnView = carGuiView.getTable().getSelectedRow();
			indexSelectOnView = controllerViewCard.getSelectedRow();
			dtoCar = listCar.getOne(indexSelectOnView);
			/*
			 * dtoCar.setModelo(carGuiView.getTable().getValueAt(indexSelectOnView,
			 * 0).toString());
			 * dtoCar.setPlaca(carGuiView.getTable().getValueAt(indexSelectOnView,
			 * 1).toString());
			 * dtoCar.setColor(carGuiView.getTable().getValueAt(indexSelectOnView,
			 * 2).toString());
			 */
			dtoCar = controllerViewCard.getDataSelect(indexSelectOnView);
		}
		updateRegistry();
	}// cierre m�todo keyReleased

	/**
	 * M�todo setDataOfView Env�a los datos a la vista.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean setDataOfView() {
		try {
			if (newRegistry) {
				dtoCar = new DtoCar();
				carGui.getBtnAdd().setText("Agregar");
				carGui.getTxtColor().setText("");
				carGui.getTxtModelo().setText("");
				carGui.getTxtPlaca().setText("");
				carGui.getBtnDelete().setEnabled(false);
			} else {
				dtoCar = listCar.getOne(indexSelectOnView);
				carGui.getTxtColor().setText(dtoCar.getColor());
				carGui.getTxtModelo().setText(dtoCar.getModelo());
				carGui.getTxtPlaca().setText(dtoCar.getPlaca());
				carGui.getBtnAdd().setText("Modificar");
				carGui.getBtnDelete().setEnabled(true);
			}
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�todo setDataOfView

	/**
	 * M�todo reloadData. Recarga los datos en la vista.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean reloadData() {
		controllerViewCard.reloadData();
		/*
		 * try { listCar.loadList(); String[][] data= new String[listCar.sizeDtos()][5];
		 * Interator<DtoCar> inte = listCar.getAll(); while(inte.hasNext()) {
		 * 
		 * int pointerCar = inte.now(); DtoCar car =inte.next(); data[pointerCar][0] =
		 * car.getModelo(); data[pointerCar][1] = car.getPlaca(); data[pointerCar][2] =
		 * car.getColor(); } carGuiView.setModelTable(data);
		 * 
		 * 
		 * return true; } catch (ClassNotFoundException | SQLException e) {
		 * Messages.showError("  "+e.getMessage()); return false; }
		 */

		/*
		 * String[][] data= new String[listCar.sizeDtos()][5]; Interator<DtoCar> inte =
		 * listCar.getAll(); while(inte.hasNext()) { int pointerCar = inte.now(); DtoCar
		 * car =inte.next(); data[pointerCar][0] = car.getModelo(); data[pointerCar][1]
		 * = car.getPlaca(); data[pointerCar][2] = car.getColor(); }
		 * carGuiView.setModelTable(data);
		 */
		return true;
	}// cierre m�todo reloadData

	/*
	 * public boolean reloadDataList() { String[][] data= new
	 * String[listCar.sizeDtos()][5]; Interator<DtoCar> inte = listCar.getAll();
	 * while(inte.hasNext()) { int pointerCar = inte.now(); DtoCar car =inte.next();
	 * data[pointerCar][0] = car.getModelo(); data[pointerCar][1] = car.getPlaca();
	 * data[pointerCar][2] = car.getColor(); } carGuiView.setModelTable(data); //
	 * carGuiView.getTable().addKeyListener(this); //
	 * carGuiView.getTable().addMouseListener(mauseClickedOnTable); return true; }
	 */

	/**
	 * M�todo addListener Agrega los eventos a los botones
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean addListener() {
		try {
			// carGuiView.getBtnFilter().addActionListener(this);

			carGui.getBtnAdd().addActionListener(this);
			carGui.getBtnDelete().addActionListener(this);
			carGui.getBtnCancel().addActionListener(this);
			carGui.getBtnInforme().addActionListener(this);

			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre m�tod addListener

	public void addScrollTable() {
		// carGuiView.getScrollPaneTable().getVerticalScrollBar().addAdjustmentListener(scrollableTable);
	}

	/**
	 * M�todo actionPerformed Controla las acciones que se realizan dentro de los
	 * eventos de los botones.
	 * 
	 * @param e objeto de tipo ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == carGui.getBtnCancel()) {
			if (carGui.getBtnAdd().getText().equalsIgnoreCase("Modificar")) {
				newRegistry = true;
				controllerViewCard.clearSelection();
				setDataOfView();
			} else {
				// carGuiView.getTable().setRowSelectionInterval(0, 0);
				controllerViewCard.selectOneRowTable();
				newRegistry = false;
				setDataOfView();
			}
		} else if (e.getSource() == carGui.getBtnAdd()) {
			if (saveRegistry()) {
				Messages.showMessage("Guardado");
			}
		} else if (e.getSource() == carGui.getBtnDelete()) {
			if (deleteRegistry()) {
				Messages.showMessage(" Eliminado");
			}
		} else if (e.getSource() == carGui.getBtnInforme()) {
			searchReport();
		}
	}// cierre m�todo actionPerformed

	/**
	 * M�todo searchReport() Carga dos tipos de reporte "simple o avanzado".
	 */
	private void searchReport() {
		
		
		Thread threarReport = new Thread(() -> {
			String[] reportOption = { "Reporte Simple", "Reporte(Mediante Busqueda Avanzada)" };
			JFrame frame = new JFrame();

			String index = (String) JOptionPane.showInputDialog(frame, "Qu� reporte deseas ver?", "Formato de Reporte",
					JOptionPane.QUESTION_MESSAGE, null, reportOption, reportOption[0]);

			if (index != null) {
				FormatReport format = null;
				boolean execute = false;
				if (index.equalsIgnoreCase("Reporte Simple")) {
					format = new ReportCar();
					execute = true;
				} else {
					format = new ReportFilterCar(this, new ReportCar());
					execute = true;
				}
				if (execute) {

					try {
						try {
							listCar.getReport(format);
						} catch (net.sf.jasperreports.engine.JRRuntimeException es) {
							Messages.showError(es.getLocalizedMessage());
							System.out.println(es.getMessage());
						}
					} catch (ClassNotFoundException | SQLException | JRException | IOException e1) {
						Messages.showError(e1.getLocalizedMessage());
					}
				}
			}
		});

		threarReport.start();
		
		

	}// cierre m�todo searchReport

	/**
	 * Archivo:ControllerCar.java contiene la definici�n de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaraci�n de atributos
		private ControllerCar controllerCar;

		/**
		 * Constructor con par�metro
		 * 
		 * @param controllerCar objeto de tipo ControllerCar
		 */
		public MauseClickedOnTable(ControllerCar controllerCar) {
			this.controllerCar = controllerCar;
		}
			
		/**
		 * M�todo mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				this.controllerCar.indexSelectOnView = this.controllerCar.controllerViewCard.getSelectedRow();
				this.controllerCar.newRegistry = false;
				dtoCar = listCar.getOne(indexSelectOnView);
				this.controllerCar.setDataOfView();
			}
		}// cierre m�todo mouseClicked
	}// cierre de clase MauseClickedOnTable

	/**
	 * M�todo getParametro
	 * 
	 * @return retorna un valor de tipo String
	 */
	public String getParametro() {
		return JOptionPane.showInputDialog(null, "Ingresa P�rametro de Busqueda");
	}// cierre m�todo getParametro

}// cierre clase ControllerCar
