package controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.swing.JFrame;

import gui.content.car.CarGuiView;
import gui.content.people.PeopleGuiView;
import gui.content.ticket.TicketContainerMainGui;
import gui.content.ticket.TicketGui;
import gui.content.ticket.TicketGuiView;
import gui.dialogs.Messages;
import model.dto.DtoCar;
import model.dto.DtoPeople;
import model.dto.DtoTicket;
import model.list.ListCar;
import model.list.ListPeople;
import model.list.ListTicket;
import model.list.interador.Interator;

/**
 * Archivo: ControllerTicket.java contiene la definición de la clase
 * ControllerTicket que extiende de ControllerWindow.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco.
 * @version 1.0
 *
 */
public class ControllerTicket extends ControllerWindow {
	// declaración de atributos
	private static final Double PRECIO_HORA = 40.0;
	private static final Double PRECIO_TICKECT_PERDIDO = 200.0;

	private static enum Status {
		Pagado, En_espera, Perdido
	};

	// Messages
	private static final String MISS_CARD = "Es necesario seleccionar un auto.";
	private static final String MISS_PEOPLE = "Es necesario seleccionar una persona.";
	private static final String MISS_TICKET = "Es necesario seleccionar una boleto.";

	private static final String TEXT_END_TICKET = "Terminar estacionamiento";

	private static final String ERROR_SAVE = "No se pudo agregar, consulta el archivo de errores";
	private static final String ERROR_UPDATE = "No se pudo actualizar, consulta el archivo de errores";
	private static final String ERROR_DELETE = "No se pudo eliminar, consulta el archivo de errores";

	private static final String INFO_LOSE_TICKET = "Por perder el boleto se aplicará un cargo extra de: "
			+ PRECIO_TICKECT_PERDIDO;

	// GUI
	private TicketContainerMainGui _containerMainGui;
	private TicketGui _ticketGui;
	private TicketGuiView _ticketGuiView;

	// Model
	private ListTicket _listTicket;
	private DtoPeople dtoPeopleSelect;
	private DtoCar dtoCarSelect;
	private DtoTicket dtoTicket;

	private int actualTicketSelect;

	private boolean inViewFragmentPeople = false;
	private boolean inViewFragmentCard = false;

	/**
	 * Constructor con parámetro
	 * 
	 * @param containerMainGui objeto de tipo TicketContainerMainGui
	 */
	public ControllerTicket(TicketContainerMainGui containerMainGui) {

		this._containerMainGui = containerMainGui;
		this._ticketGui = containerMainGui.getTicketGui();
		this._ticketGuiView = containerMainGui.getTicketGuiView();

		_listTicket = ListTicket.getInstance();

		addListener();

		try {
			_listTicket.loadList();
			reloadData();
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showMessage(e.getLocalizedMessage());
		}

	}// cierre constructor

	/**
	 * Método validateData Valida si la información es nula
	 * 
	 * @return retorna valor booleano
	 */
	private boolean validateData() {
		if (dtoCarSelect == null) {
			Messages.showError(MISS_CARD);
			return false;
		}
		if (dtoPeopleSelect == null) {
			Messages.showError(MISS_PEOPLE);
			return false;
		}

		return true;
	}// cierre método validateData

	/**
	 * Método saveRegistry Guarda los valores de Ticket
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean saveRegistry() {

		if (validateData()) {
			DtoTicket ticket = new DtoTicket();

			ticket.setIdPesona(dtoPeopleSelect.getId());
			ticket.setIdAuto(dtoCarSelect.getId());

			try {
				if (_listTicket.add(ticket)) {
					_listTicket.loadList();
					resetSelects();
					reloadData();
				} else {
					Messages.showError(ERROR_SAVE);
				}
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(e.getLocalizedMessage());
			}

		}

		return false;
	}// cierre método saveRegistry

	/**
	 * Método filter Filtra la lista de Ticket
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean filter() {
		try {
			_listTicket.loadListFilter(_ticketGuiView.getCbxFilter(), _ticketGuiView.getTxtFilter().getText());
			reloadData();

		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
			System.out.println(e.getMessage());
		}
		return false;
	}// cierre método filter

	/**
	 * Método updateRegistry Actualiza los datos de Ticket
	 * 
	 * @return retorna valor booleano
	 */
	@Override
	public boolean updateRegistry() {

		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);

		long diff = timestamp.getTime() - dtoTicket.getDate().getTime();

		int seconds = (int) diff / 1000;
		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;

		seconds = (seconds % 3600) % 60;

		double total = 0;

		if (minutes > 15) // Limite de tolerancia
			total += PRECIO_HORA;
		else {
			// Primer hora debe cobrarse
			if (hours == 0)
				total += PRECIO_HORA;
		}

		total += (hours * PRECIO_HORA);

		dtoTicket.setTotalPago(total);

		if (_ticketGui.getCbxLoseTicket().isSelected()) {
			total += PRECIO_TICKECT_PERDIDO;
			dtoTicket.setEstatus(Status.Perdido.toString());
			Messages.showMessage(INFO_LOSE_TICKET);
		} else
			dtoTicket.setEstatus(Status.Pagado.toString());

		try {
			if (_listTicket.update(dtoTicket, actualTicketSelect)) {
				_listTicket.loadList();
				resetSelects();
				reloadData();
			} else
				Messages.showError(ERROR_UPDATE);
		} catch (ClassNotFoundException | SQLException e1) {
			Messages.showError(e1.getLocalizedMessage());
		}

		return true;
	}// cierre método updateRegistry

	/**
	 * Método deleteRegistry Elimina un registro de Ticket
	 * 
	 * @return retorna un valor de tipo booleano
	 */
	@Override
	public boolean deleteRegistry() {
		if (dtoTicket != null) {
			try {
				if (_listTicket.delete(actualTicketSelect)) {
					_listTicket.loadList();
					resetSelects();
					reloadData();
				} else
					Messages.showError(ERROR_DELETE);
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError(e.getLocalizedMessage());
			}
		} else
			Messages.showError(MISS_TICKET);

		return false;
	}// cierre método deleteRegistry

	// getter y setter
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

	/**
	 * Método reloadData Recarga los datos a la vista
	 * 
	 * @return retorna un valor booleano
	 */
	@Override
	public boolean reloadData() {

		String[][] data = new String[_listTicket.sizeDtos()][6];
		Interator<DtoTicket> interator = _listTicket.getAll();
		while (interator.hasNext()) {

			int pointerCar = interator.now();
			DtoTicket ticket = interator.next();
			data[pointerCar][0] = ticket.getPlacaAuto();
			data[pointerCar][1] = ticket.getEmailAuto();
			data[pointerCar][2] = ticket.getFechaEntrada();
			data[pointerCar][3] = ticket.getFechaSalida();
			data[pointerCar][4] = ticket.getTotalPago() + "";
			data[pointerCar][5] = ticket.getEstatus();

		}
		_ticketGuiView.setModelTable(data);

		return true;
	}// cierre método reloadData

	/**
	 * Método actionPerformed Realiza las acciones de cada evento de los botones.
	 * 
	 * @param e objeto de tipo ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _ticketGui.getBtnAdd()) {
			if (_ticketGui.getBtnAdd().getText().equals(TEXT_END_TICKET))
				updateRegistry();
			else
				saveRegistry();
		} else if (e.getSource() == _ticketGui.getBtnCancel()) {
			resetSelects();
			_ticketGui.resetBtnAdd();
		} else if (e.getSource() == _ticketGui.getBtnCar()) {
			if (!inViewFragmentCard) {
				openListCard();
				inViewFragmentCard = true;
			} else {
				System.out.println("Existe fragmento en vista.. card");
			}

		} else if (e.getSource() == _ticketGui.getBtnPeople()) {
			if (!inViewFragmentPeople) {
				openListPeople();
				inViewFragmentPeople = true;
			} else
				System.out.println("Existe fragmento en vista...");
		} else if (e.getSource() == _ticketGui.getBtnDelete()) {

			deleteRegistry();
		} else if (e.getSource() == _ticketGui.getBtnInforme()) {

		} else if (e.getSource() == _ticketGuiView.getBtnFilter()) {
			filter();
		}
	}// cierre método actionPerformed

	/**
	 * Método addListener Escuha los eventos de los botones.
	 * 
	 * @return retorna un valor booleano.
	 */
	@Override
	public boolean addListener() {
		try {
			// Butons
			_ticketGui.getBtnAdd().addActionListener(this);
			_ticketGui.getBtnCancel().addActionListener(this);
			_ticketGui.getBtnCar().addActionListener(this);
			_ticketGui.getBtnPeople().addActionListener(this);
			_ticketGui.getBtnDelete().addActionListener(this);
			_ticketGui.getBtnInforme().addActionListener(this);

			_ticketGuiView.getBtnFilter().addActionListener(this);

			// Table
			_ticketGuiView.getTable().addKeyListener(this);
			_ticketGuiView.getTable().addMouseListener(new MauseClickedOnTableTicke(this));

			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}// cierre método addListener

	/**
	 * Método openListPeople Abre la lista People
	 */
	private void openListPeople() {

		JFrame applicationFrame;

		PeopleGuiView guiView = new PeopleGuiView();

		ListPeople listPeople = ListPeople.getInstance();

		if (listPeople.sizeDtos() > 0) {
			System.out.println("Contiene datos...");
		} else {
			System.out.println("No contiene datos cargar..");
			try {
				listPeople.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showError("  " + e.getMessage());
			}

		}

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
		guiView.setModelTable(data);

		guiView.getTable().addMouseListener(new MauseClickedOnTable(this, guiView));
		applicationFrame = new JFrame("Seleciona a la persona");
		applicationFrame.getContentPane().add(guiView);
		applicationFrame.addWindowListener(new WindowClosePeople(this));
		applicationFrame.pack();
		applicationFrame.setVisible(true);
	}// cierre método openListPeople

	/**
	 * Método openListCard Abre la lista Card
	 */
	private void openListCard() {

		JFrame applicationFrame;

		CarGuiView carGuiView = new CarGuiView();

		ControllerViewCard card = new ControllerViewCard(carGuiView);

		carGuiView.getTable().addMouseListener(new MauseClickedOnTableCard(this, carGuiView));
		applicationFrame = new JFrame("Seleciona a la coche");
		applicationFrame.getContentPane().add(carGuiView);
		applicationFrame.addWindowListener(new WindowCloseCard(this));
		applicationFrame.pack();
		applicationFrame.setVisible(true);
	}// cierre método openListCard

	/**
	 * Archivo: ControllerTicket.java contiene la definición de la clase
	 * WindowClosePeople que extiende de WindowAdapter.
	 * 
	 * @author Jorge Jacobo, Marcos Guillermo, Gabriel García, Amanda Franco
	 *
	 */

	private static final class WindowClosePeople extends WindowAdapter {
		// declaración de atributo
		private ControllerTicket controllerTicket;

		/**
		 * Constructor con parámetro
		 * 
		 * @param controllerTicket objeto de tipo ControllerTicket
		 */
		public WindowClosePeople(ControllerTicket controllerTicket) {
			this.controllerTicket = controllerTicket;
		}

		/**
		 * Método windowClosing Cierra el fragment People
		 * 
		 * @param evt objeto de tipo WindowEvent
		 */
		public void windowClosing(WindowEvent evt) {
			controllerTicket.inViewFragmentPeople = false;

		}// cierre método windowClosing
	}// cierre clase WindowClosePeople

	/**
	 * Archivo: ControlleTicket.java contiene la definición de la clase
	 * WindowCloseCard que extiende de WindowAdapter
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private static final class WindowCloseCard extends WindowAdapter {
		// declaración de atributos
		private ControllerTicket controllerTicket;

		/**
		 * Constructor con parámetro
		 * 
		 * @param controllerTicket objeto de tipo ControllerTicket
		 */
		public WindowCloseCard(ControllerTicket controllerTicket) {
			this.controllerTicket = controllerTicket;
		}

		/**
		 * Método windowClosing
		 * 
		 * @param evt objeto de tipo WindowEvent
		 */
		public void windowClosing(WindowEvent evt) {
			controllerTicket.inViewFragmentCard = false;

		}// cierre método windowClosing
	}// cierre clase WindowCloseCard

	/**
	 * Archivo: ControlleTicket.java contiene la definición de la clase
	 * MauseClickedOnTable que extiende de MouseAdapter
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTable extends MouseAdapter {
		// declaración de atributos
		private ControllerTicket controllerTicket;
		private PeopleGuiView guiView;

		/**
		 * Constructor con parámetros
		 * 
		 * @param controllerTicket objeto de tipo ControllerTicket
		 * @param guiView          objeto de tipo PeopleGuiView
		 */
		public MauseClickedOnTable(ControllerTicket controllerTicket, PeopleGuiView guiView) {
			this.controllerTicket = controllerTicket;
			this.guiView = guiView;
		}// cierre constructor

		/**
		 * Método mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = guiView.getTable().getSelectedRow();
				controllerTicket.dtoPeopleSelect = ListPeople.getInstance().getOne(contador);
				controllerTicket.actualTicketSelect = contador;
				controllerTicket._ticketGui.getBtnPeople()
						.setText("Se selecionó a " + controllerTicket.dtoPeopleSelect.getName());
			}
		}// cierre método mouseClicked
	}// cierre clase MouseClickedOnTable

	/**
	 * Archivo: ControlleTicket.java contiene la definición de la clase
	 * MauseClickedOnTableCars que extiende de MouseAdapter
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTableCard extends MouseAdapter {
		// declaración de atributos
		private ControllerTicket controllerTicket;
		private CarGuiView carGuiView;

		/**
		 * Constructor con parámetros
		 * 
		 * @param controllerTicket objeto de tipo ControllerTicket
		 * @param carGuiView       objeto de tipo CarGuiView
		 */
		public MauseClickedOnTableCard(ControllerTicket controllerTicket, CarGuiView carGuiView) {
			this.controllerTicket = controllerTicket;
			this.carGuiView = carGuiView;
		}// cierre constructor

		/**
		 * Método mouseClicked Controla los eventos del mouse
		 * 
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = carGuiView.getTable().getSelectedRow();
				controllerTicket.dtoCarSelect = ListCar.getInstance().getOne(contador);
				controllerTicket._ticketGui.getBtnCar()
						.setText("Se selecionó a " + controllerTicket.dtoCarSelect.getPlaca());
			}
		}// cierre método mouseClicked
	}// cierre clase MauseClickedOnTableCard

	// GUI

	/**
	 * Método resetSelects
	 * Regresa a nulo los dto seleccionados.
	 */
	private void resetSelects() {
		_ticketGui.resetBtnSelect();
		this.dtoCarSelect = null;
		this.dtoPeopleSelect = null;
	}//cierre método resetSelects
	

	/**
	 * Archivo: ControlleTicket.java contiene la definición de la clase
	 * MauseClickedOnTableTicke que extiende de MouseAdapter
	 * 
	 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
	 * @version 1.0
	 *
	 */
	private class MauseClickedOnTableTicke extends MouseAdapter {
		//declaración de atributo
		private ControllerTicket controllerTicket;

		/**
		 * Constructor con parámetro
		 * @param controllerTicket objeto de tipo ControllerTicket
		 */
		public MauseClickedOnTableTicke(ControllerTicket controllerTicket) {
			this.controllerTicket = controllerTicket;
		}//cierre constructor

		/**
		 * Método mouseClicked
		 * Controla los eventos del mouse
		 * @param evnt objeto de tipo MouseEvent
		 */
		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = controllerTicket._ticketGuiView.getTable().getSelectedRow();

				controllerTicket.actualTicketSelect = contador;
				controllerTicket._ticketGui.getBtnCar()
						.setText("Se selecionó a " + _listTicket.getOne(contador).getPlacaAuto());

				controllerTicket._ticketGui.getBtnPeople()
						.setText("Se selecionó a " + _listTicket.getOne(contador).getEmailAuto());

				controllerTicket.dtoTicket = _listTicket.getOne(contador);

				controllerTicket._ticketGui.getBtnAdd().setText(TEXT_END_TICKET);
			}
		}//cierre método mouseClicked
	}//cierre clase MauseClickedOnTableTicke

}//cierre clase ControllerTicket
