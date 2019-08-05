package controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

import gui.content.people.PeopleContainerMainGui;
import gui.dialogs.Messages;
import model.dto.DtoPeople;
import model.list.ListPeople;
import model.list.interador.Interator;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;
import report.ReportPeople;
import report.ReportTicket;
import report.decoratorComponent.ReportFilterPeople;
import report.decoratorComponent.ReportFilterTicket;

/**
 * Archivo: ControllerPeople.java contiene la definiciï¿½n de la clase
 * ControllerPeople y extiende de la clase ControllerWindow.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class ControllerPeople extends ControllerWindow {
//declaraciï¿½n de atributos
	private PeopleContainerMainGui view;

	private ListPeople listPeople;

	private ScrollableTable scrollableTable;
	private DtoPeople dtoPeople;

	private int indexSelectOnView;

	private MauseClickedOnTable mauseClickedOnTable;
	private boolean newRegistry;

	/**
	 * Constructor ControllerPeople con parï¿½metro
	 * 
	 * @param view objeto de tipo PeopleContainerMainGui
	 */
	public ControllerPeople(PeopleContainerMainGui view) {
		this.view = view;
		listPeople = ListPeople.getInstance();
		scrollableTable = new ScrollableTable(this);
		view.getPeopleGuiView().getTable().addMouseListener(new MauseClickedOnTable(this));
		addScrollTable();
		addListener();
		if (listPeople.sizeDtos() > 0) {
		} else {
			try {
				listPeople.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(e.getMessage());

			}
		}
		reloadData();
	}// cierre constructor

	/**
	 * Mï¿½todo reloadDataList Obtiene los datos de la lista.
	 * 
	 * @return retorna un valor booleano
	 */
	public boolean reloadDataList() {
		String[][] data = new String[listPeople.sizeDtos()][5];
		Interator<DtoPeople> inte = listPeople.getAll();
		while (inte.hasNext()) {
			int pointerPeople = inte.now();
			DtoPeople people = inte.next();
			data[pointerPeople][0] = people.getName();
			data[pointerPeople][1] = people.getFirstName();
			data[pointerPeople][2] = people.getLastName();
			data[pointerPeople][3] = people.getEmail();
			data[pointerPeople][4] = people.getTelephone();
		}
		view.getPeopleGuiView().setModelTable(data);
		view.getPeopleGuiView().getTable().addKeyListener(this);
		view.getPeopleGuiView().getTable().addMouseListener(mauseClickedOnTable);
		return true;

	}// cierre mï¿½todo reloadDataList

	/**
	 * Mï¿½todo saveRegistry Guarda o modifica los datos de People.
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean saveRegistry() {
		if (view.getPeopleGui().getBtnAdd().getText().equalsIgnoreCase("Modificar")) {
			if (getDataOfView()) {
				updateRegistry();
				// reloadData();
				return true;
			} else {
				return true;
			}
		} else {
			dtoPeople = new DtoPeople();
			try {
				if (getDataOfView()) {
					listPeople.add(dtoPeople);
					reloadDataList();
					view.getPeopleGuiView().getTable().setRowSelectionInterval(0, 0);
					view.getPeopleGuiView().getScrollPaneTable().getViewport().setViewPosition(new Point(0, 0));
					return true;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}// cierre mï¿½todo saveRegistry

	/**
	 * Mï¿½todo filter Filtrar los datos de la lista People.
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean filter() {
		try {
			listPeople.loadListFilter(view.getPeopleGuiView().getCbxFilter(),
					view.getPeopleGuiView().getTxtFilter().getText());
			String[][] data = new String[listPeople.sizeDtos()][5];
			Interator<DtoPeople> inte = listPeople.getAll();
			while (inte.hasNext()) {
				int pointerPeople = inte.now();
				DtoPeople people = inte.next();
				data[pointerPeople][0] = people.getName();
				data[pointerPeople][1] = people.getFirstName();
				data[pointerPeople][2] = people.getLastName();
				data[pointerPeople][3] = people.getEmail();
				data[pointerPeople][4] = people.getTelephone();
			}
			view.getPeopleGuiView().setModelTable(data);
			view.getPeopleGuiView().getTable().addKeyListener(this);
			view.getPeopleGuiView().getTable().addMouseListener(mauseClickedOnTable);
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
			return false;
		}
	}// cierre mï¿½todo filter

	/**
	 * Mï¿½todo updateRegistry Actualiza un valor de tipo People.
	 * 
	 * @return retorna un valor de tipo booleano.
	 */
	@Override
	public boolean updateRegistry() {
		try {

			if (listPeople.update(dtoPeople, indexSelectOnView)) {

				view.getPeopleGuiView().getTable().setValueAt(dtoPeople.getName(), indexSelectOnView, 0);
				view.getPeopleGuiView().getTable().setValueAt(dtoPeople.getFirstName(), indexSelectOnView, 1);
				view.getPeopleGuiView().getTable().setValueAt(dtoPeople.getLastName(), indexSelectOnView, 2);
				view.getPeopleGuiView().getTable().setValueAt(dtoPeople.getEmail(), indexSelectOnView, 3);
				view.getPeopleGuiView().getTable().setValueAt(dtoPeople.getTelephone(), indexSelectOnView, 4);
				// setDataOfView();
				newRegistry = false;
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}

		return false;
	}// cierre mï¿½tod updateRegistry

	/**
	 * Mï¿½todo deleteRegistry Elimina un registro de la lista People.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean deleteRegistry() {
		try {
			dtoPeople = listPeople.getList().get(indexSelectOnView);
			listPeople.delete(indexSelectOnView);
			if (view.getPeopleGuiView().getTable().getSelectedRow() < 0) {
				newRegistry = true;
			} else {
				newRegistry = false;
				dtoPeople = listPeople.getList().get(view.getPeopleGuiView().getTable().getSelectedRow());
			}
			reloadData();
			setDataOfView();
			return true;
		} catch (ClassNotFoundException e) {
			Messages.showError("  " + e.getMessage());
			return false;
		} catch (SQLException e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre mï¿½todo deleteRegistry

	/**
	 * Mï¿½todo getDataOfView Obtiene los datos de la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean getDataOfView() {
		try {
			if (validateFieldText(view.getPeopleGui().getTxtName().getText())) {
				dtoPeople.setName(view.getPeopleGui().getTxtName().getText());
			} else {
				Messages.showError("  Campo Nombre invï¿½lido");
				return false;
			}
			if (validateFieldText(view.getPeopleGui().getTxtFirsName().getText())) {
				dtoPeople.setFirstName(view.getPeopleGui().getTxtFirsName().getText());
			} else {
				Messages.showError("  Campo Apellido Paterno invï¿½lido");
				return false;
			}
			if (validateFieldText(view.getPeopleGui().getTxtLastName().getText())) {
				dtoPeople.setLastName(view.getPeopleGui().getTxtLastName().getText());
			} else {
				Messages.showError("  Campo Apellido Materno invï¿½lido");
				return false;
			}

			if (validateFieldText(view.getPeopleGui().getTxtEmail().getText())) {
				dtoPeople.setEmail(view.getPeopleGui().getTxtEmail().getText());
			} else {
				Messages.showError("  Campo  Correo invï¿½lidoï¿½ï¿½lido");
				return false;
			}
			if (validateFieldText(view.getPeopleGui().getTxtTelephone().getText())) {
				dtoPeople.setTelephone(view.getPeopleGui().getTxtTelephone().getText());
			} else {
				Messages.showError("  Campo Tï¿½lefono invï¿½lido");
				return false;
			}
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre mï¿½todo getDataOfView

	/**
	 * Mï¿½todo validateFieldText Valida el texto de los campos
	 * 
	 * @param text valor de tipo String
	 * @return retorna un valor booleano
	 */
	private boolean validateFieldText(String text) {
		if (text.length() < 1 || text.equals(""))
			return false;

		return true;
	}// cierre mï¿½todo validateFieldText

	/**
	 * Mï¿½todo setDataOfView Envia los datos a la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean setDataOfView() {
		try {
			if (newRegistry) {
				dtoPeople = new DtoPeople();
				view.getPeopleGui().getBtnAdd().setText("Agregar");
				view.getPeopleGui().getTxtFirsName().setText("");
				view.getPeopleGui().getTxtEmail().setText("");
				view.getPeopleGui().getTxtLastName().setText("");
				view.getPeopleGui().getTxtName().setText("");
				view.getPeopleGui().getTxtTelephone().setText("");
				view.getPeopleGui().getBtnDelete().setEnabled(false);
			} else {
				dtoPeople = listPeople.getList().get(indexSelectOnView);
				view.getPeopleGui().getTxtFirsName().setText(dtoPeople.getFirstName() + " ID" + dtoPeople.getId());
				view.getPeopleGui().getTxtEmail().setText(dtoPeople.getEmail());
				view.getPeopleGui().getTxtLastName().setText(dtoPeople.getLastName());
				view.getPeopleGui().getTxtName().setText(dtoPeople.getName());
				view.getPeopleGui().getTxtTelephone().setText(dtoPeople.getTelephone());
				view.getPeopleGui().getBtnAdd().setText("Modificar");
				view.getPeopleGui().getBtnDelete().setEnabled(true);
			}
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre mï¿½todo setDataOfView

	/**
	 * Mï¿½todo reloadData Recarga los datos a la vista.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean reloadData() {

		String[][] data = new String[listPeople.sizeDtos()][5];
		Interator<DtoPeople> interator = listPeople.getAll();
		while (interator.hasNext()) {
			int countPeoples = interator.now();
			DtoPeople people = interator.next();
			data[countPeoples][0] = people.getName();
			data[countPeoples][1] = people.getLastName();
			data[countPeoples][2] = people.getFirstName();
			data[countPeoples][3] = people.getEmail();
			data[countPeoples][4] = people.getTelephone();
		}
		view.getPeopleGuiView().setModelTable(data);
		view.getPeopleGuiView().getTable().addKeyListener(this);
		view.getPeopleGuiView().getTable().addMouseListener(mauseClickedOnTable);

		return true;

	}// cierre mï¿½todo reloadData

	/**
	 * Mï¿½todo keyReleased Evento del teclado
	 */
	@Override
	public void keyReleased(KeyEvent d) {
		if (view.getPeopleGuiView().getTable().getSelectedRows().length > 0) {
			indexSelectOnView = view.getPeopleGuiView().getTable().getSelectedRow();
			dtoPeople = listPeople.getList().get(indexSelectOnView);
			dtoPeople.setName(view.getPeopleGuiView().getTable().getValueAt(indexSelectOnView, 0).toString());
			dtoPeople.setLastName(view.getPeopleGuiView().getTable().getValueAt(indexSelectOnView, 1).toString());
			dtoPeople.setFirstName(view.getPeopleGuiView().getTable().getValueAt(indexSelectOnView, 2).toString());
			dtoPeople.setEmail(view.getPeopleGuiView().getTable().getValueAt(indexSelectOnView, 3).toString());
			dtoPeople.setTelephone(view.getPeopleGuiView().getTable().getValueAt(indexSelectOnView, 4).toString());
		}
		updateRegistry();
	}// cierre mï¿½todo keyReleased

	/**
	 * Mï¿½todo actionPerformed Realiza las acciones de cada evento de los botones.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getPeopleGuiView().getBtnFilter()) {
			filter();
		} else if (e.getSource() == view.getPeopleGui().getBtnCancel()) {
			if (view.getPeopleGui().getBtnAdd().getText().equalsIgnoreCase("Modificar")) {
				newRegistry = true;
				view.getPeopleGuiView().getTable().clearSelection();
				setDataOfView();
			} else {
				view.getPeopleGuiView().getTable().setRowSelectionInterval(0, 0);
				newRegistry = false;
				setDataOfView();
			}
		} else if (e.getSource() == view.getPeopleGui().getBtnAdd()) {
			if (saveRegistry()) {
				Messages.showMessage(" Guardado");
			}
		} else if (e.getSource() == view.getPeopleGui().getBtnDelete()) {
			if (deleteRegistry()) {
				Messages.showMessage(" Eliminado");
			}
		} else if (e.getSource() == view.getPeopleGui().getBtnInforme()) {
			searchReport();
		}
	}// cierre mï¿½todo actionPerformed

	/**
	 * Cierre mï¿½todo searchReport Carga el reporte "simple o avanzado".
	 */
	private void searchReport() {
		
		Thread threarReport = new Thread(() -> {
			String[] reportOption = { "Reporte Simple", "Reporte(Mediante Busqueda Avanzada)" };
			JFrame frame = new JFrame();

			String index = (String) JOptionPane.showInputDialog(frame, "¿Qué reporte deseas ver?", "Formato de Reporte",
					JOptionPane.QUESTION_MESSAGE, null, reportOption, reportOption[0]);
			if (index != null) {
				FormatReport format = null;
				boolean execute = false;
				if (index.equalsIgnoreCase("Reporte Simple")) {
					format = new ReportPeople();
					execute = true;
				} else {
					format = new ReportFilterPeople(this, new ReportPeople());
					execute = true;
				}
				if (execute) {

					try {
						try {
							listPeople.getReport(format);
						} catch (net.sf.jasperreports.engine.JRRuntimeException es) {
							Messages.showError(es.getLocalizedMessage());
							System.out.println(es.getMessage());
						}
					} catch (ClassNotFoundException | SQLException | JRException | IOException e1) {
						System.out.println(e1.getMessage());
						Messages.showError(e1.getLocalizedMessage());
					}
				}
			}
		});

		threarReport.start();
		
	}

	/**
	 * Mï¿½todo addListener Escuha los eventos de los botones.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean addListener() {
		try {
			view.getPeopleGuiView().getBtnFilter().addActionListener(this);
			view.getPeopleGui().getBtnAdd().addActionListener(this);
			view.getPeopleGui().getBtnDelete().addActionListener(this);
			view.getPeopleGui().getBtnCancel().addActionListener(this);
			view.getPeopleGui().getBtnInforme().addActionListener(this);
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre mï¿½todo addListener

	/**
	 * Mï¿½todo addScrollTable Agrega un scroll a la tabla.
	 */
	public void addScrollTable() {
		view.getPeopleGuiView().getScrollPaneTable().getVerticalScrollBar().addAdjustmentListener(scrollableTable);
	}// cierre mï¿½todo addScrollTable

	/**
	 * Mï¿½todo loadNextCars Carga los datos del carro que pertenece a People
	 */
	public void loadNextCars() {
		Executor executor = new Executor() {
			@Override
			public void execute(Runnable arg0) {
				arg0.run();
			}
		};

		executor.execute(() -> {
			try {
				if (listPeople.reloadNext())
					reloadData();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(" " + e.getLocalizedMessage());
			}
		});
	}// cierre mï¿½todo loadNextCars

//Inner Class Event to view
	/**
	 * Archivo: ControllerPeople.java contiene la definiciï¿½n de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter.
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaraciï¿½n de atributos
		private ControllerPeople controllerPeople;

		/**
		 * Constructor con parï¿½metro
		 * 
		 * @param controllerPeople objeto de tipo ControllerPeople
		 */
		public MauseClickedOnTable(ControllerPeople controllerPeople) {
			this.controllerPeople = controllerPeople;
		}

		/**
		 * Mï¿½todo mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				this.controllerPeople.indexSelectOnView = this.controllerPeople.view.getPeopleGuiView().getTable()
						.getSelectedRow();
				this.controllerPeople.newRegistry = false;
				this.controllerPeople.dtoPeople = this.controllerPeople.listPeople.getList().get(indexSelectOnView);
				this.controllerPeople.setDataOfView();
			}
		}
	}// cierre clase MauseClickedOnTable

	/**
	 * Archivo: ControllerPeople.java que contiene la definiciï¿½n de la clase
	 * ScrollableTable que implementa de AdjustmentListener.
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private class ScrollableTable implements AdjustmentListener {
		// declaraciï¿½n de atributo
		private ControllerPeople controller;

		/**
		 * Constructor con parï¿½metro
		 * 
		 * @param controller objeto de tipo ControllerPeople
		 */
		public ScrollableTable(ControllerPeople controller) {
			this.controller = controller;
		}// cierre constructor

		/**
		 * Mï¿½todo adjustmentValueChanged
		 * 
		 * @param e objeto de tipo AdjustmentEvent
		 */
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			if (!e.getValueIsAdjusting()) {
				JScrollBar source = (JScrollBar) e.getAdjustable();
				int extent = source.getModel().getExtent();
				int maximum = source.getModel().getMaximum();
				if (e.getValue() + extent == maximum) 
					controller.loadNextCars();
			}
		}// cierre mï¿½todo adjustmentValueChanged
	}// cierre clase ScrollableTable

	/**
	 * Mï¿½todo getParametro
	 * 
	 * @return retorna un valor de tipo String
	 */
	public String getParametro() {
		return JOptionPane.showInputDialog(null, "Ingresa Digitos de una placa para bï¿½squeda");
	}// cierre mï¿½todo getParametro

}// cierre clase ControllerPeople
