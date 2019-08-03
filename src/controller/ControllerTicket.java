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

public class ControllerTicket extends ControllerWindow {

	private static final Double PRECIO_HORA = 40.0;
	private static final Double PRECIO_TICKECT_PERDIDO = 200.0;
	private static enum Status {
		Pagado, En_espera, Perdido
	};

	private TicketContainerMainGui _containerMainGui;

	private TicketGui _ticketGui;
	private TicketGuiView _ticketGuiView;

	private ListTicket _listTicket;

	private DtoPeople dtoPeopleSelect;

	private DtoCar dtoCarSelect;

	private DtoTicket dtoTicket;

	private int actualTicketSelect;

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

	}

	@Override
	public boolean saveRegistry() {
		DtoTicket ticket = new DtoTicket();
		if (dtoCarSelect == null) {
			Messages.showError("Debes seleccionar un auto.");
			return false;
		}
		if (dtoPeopleSelect == null) {
			Messages.showError("Debes seleccionar una persona.");
			return false;
		}
		ticket.setIdPesona(dtoPeopleSelect.getId());
		ticket.setIdAuto(dtoCarSelect.getId());
		
		
		try {
			if (_listTicket.add(ticket)) {
				_listTicket.loadList();
				resetSelects();

				reloadData();
			} else {
				Messages.showError("No se pudo agregar..");
			}
		} catch (ClassNotFoundException | SQLException e) {
			Messages.showError(e.getLocalizedMessage());
		}
		

		return false;
	}

	@Override
	public boolean filter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRegistry() {
		Instant instant = Instant.now();
		Timestamp timestamp = Timestamp.from(instant);
		
		long diff = timestamp.getTime() - dtoTicket.getDate().getTime();
		
		int seconds = (int) diff / 1000;
		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;
		seconds = (seconds % 3600) % 60;

		int total = 0;
		if (minutes > 15) 
			total += PRECIO_HORA;
		 else {
			if (hours == 0) 
				total += PRECIO_HORA;
		}

		total += (hours * PRECIO_HORA);

		dtoTicket.setTotalPago(total);
	
		
		

		if(_ticketGui.getCbxLoseTicket().isSelected()) {
			System.out.println("perdido");
			System.out.println("se aplicará tarifa");
			total += PRECIO_TICKECT_PERDIDO;
			dtoTicket.setEstatus(Status.Perdido.toString());
		}else {
			dtoTicket.setEstatus(Status.Pagado.toString());
		}
		
		try {
			if (_listTicket.update(dtoTicket, actualTicketSelect)) {
				_listTicket.loadList();
				resetSelects();
				reloadData();
			}

		} catch (ClassNotFoundException | SQLException e1) {
			Messages.showError(e1.getLocalizedMessage());
			System.out.println(e1.getMessage());
		}
		Messages.showMessage(
				"El tiempo estacionad Horas: " + hours + " Minutos: " + minutes + " Segundos: " + seconds);
		return true;
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
		_ticketGuiView.getTable().addKeyListener(this);
		_ticketGuiView.getTable().addMouseListener(new MauseClickedOnTableTicke(this));

		return true;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == _ticketGui.getBtnAdd()) {
			if (_ticketGui.getBtnAdd().getText().equalsIgnoreCase("Terminar estacionamiento")) {
				updateRegistry();
			} else
				saveRegistry();
		} else if (e.getSource() == _ticketGui.getBtnCancel()) {
			System.out.println("Cancelar");
			resetSelects();
			_ticketGui.resetBtnAdd();
		} else if (e.getSource() == _ticketGui.getBtnCar()) {
			openListCard();
		} else if (e.getSource() == _ticketGui.getBtnPeople()) {
			openListPeople();
		}
	}

	@Override
	public boolean addListener() {
		try {
			_ticketGui.getBtnAdd().addActionListener(this);
			_ticketGui.getBtnCancel().addActionListener(this);
			_ticketGui.getBtnCar().addActionListener(this);
			_ticketGui.getBtnPeople().addActionListener(this);
			return true;
		} catch (Exception e) {
			Messages.showError("  " + e.getMessage());
			return false;
		}
	}

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
		applicationFrame.addWindowListener(new WindowCloseManager());
		applicationFrame.pack();
		applicationFrame.setVisible(true);
	}

	private void openListCard() {

		JFrame applicationFrame;

		CarGuiView carGuiView = new CarGuiView();

		ListCar listCar = ListCar.getInstance();
		if (listCar.sizeDtos() <= 0) {
			try {
				listCar.loadList();
			} catch (ClassNotFoundException | SQLException e) {
				Messages.showMessage(e.getLocalizedMessage());
			}
		}
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

		carGuiView.getTable().addMouseListener(new MauseClickedOnTableCard(this, carGuiView));
		applicationFrame = new JFrame("Seleciona a la persona");
		applicationFrame.getContentPane().add(carGuiView);
		applicationFrame.addWindowListener(new WindowCloseManager());
		applicationFrame.pack();
		applicationFrame.setVisible(true);
	}

	private static class WindowCloseManager extends WindowAdapter {
		public void windowClosing(WindowEvent evt) {
			System.out.println("intento cerrar");
		}
	}

	private class MauseClickedOnTable extends MouseAdapter {
		private ControllerTicket controllerTicket;
		private PeopleGuiView guiView;

		public MauseClickedOnTable(ControllerTicket controllerTicket, PeopleGuiView guiView) {
			this.controllerTicket = controllerTicket;
			this.guiView = guiView;
		}

		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = guiView.getTable().getSelectedRow();
				controllerTicket.dtoPeopleSelect = ListPeople.getInstance().getOne(contador);
				controllerTicket._ticketGui.getBtnPeople()
						.setText("Se selecionó a " + controllerTicket.dtoPeopleSelect.getName());
			}
		}
	}

	private class MauseClickedOnTableCard extends MouseAdapter {
		private ControllerTicket controllerTicket;
		private CarGuiView carGuiView;

		public MauseClickedOnTableCard(ControllerTicket controllerTicket, CarGuiView carGuiView) {
			this.controllerTicket = controllerTicket;
			this.carGuiView = carGuiView;
		}

		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = carGuiView.getTable().getSelectedRow();
				controllerTicket.dtoCarSelect = ListCar.getInstance().getOne(contador);
				controllerTicket._ticketGui.getBtnCar()
						.setText("Se selecionó a " + controllerTicket.dtoCarSelect.getPlaca());
			}
		}
	}

	// GUI

	private void resetSelects() {
		_ticketGui.resetBtnSelect();
		this.dtoCarSelect = null;
		this.dtoPeopleSelect = null;
	}

	private class MauseClickedOnTableTicke extends MouseAdapter {
		private ControllerTicket controllerTicket;

		public MauseClickedOnTableTicke(ControllerTicket controllerTicket) {
			this.controllerTicket = controllerTicket;
		}

		public void mouseClicked(MouseEvent evnt) {
			if (evnt.getClickCount() == 1) {
				int contador = controllerTicket._ticketGuiView.getTable().getSelectedRow();

				controllerTicket.actualTicketSelect = contador;
				controllerTicket._ticketGui.getBtnCar()
						.setText("Se selecionó a " + _listTicket.getOne(contador).getPlacaAuto());

				controllerTicket._ticketGui.getBtnPeople()
						.setText("Se selecionó a " + _listTicket.getOne(contador).getEmailAuto());

				controllerTicket.dtoTicket = _listTicket.getOne(contador);

				controllerTicket._ticketGui.getBtnAdd().setText("Terminar estacionamiento");
			}
		}
	}

}
