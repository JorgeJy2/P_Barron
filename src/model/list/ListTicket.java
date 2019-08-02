package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoTicket;

import model.dto.DtoTicket;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;
import model.list.paginator.Paginator;
import model.list.paginator.PaginatorDao;

public class ListTicket implements Listable<DtoTicket> {

	private static ListTicket instance;
	
	private DaoInterface<DtoTicket> _dao;
	private List<DtoTicket> _tickets;
	private Paginator<DtoTicket> _paginator;
	
	
	private ListTicket() {
		_tickets = new ArrayList<DtoTicket>();
		_dao = new DaoTicket();
		_paginator = new PaginatorDao<DtoTicket>(_dao);
	}
	
	public static ListTicket getInstance() {
		
		if(instance == null) 
			instance = new  ListTicket();
		
		return instance;
	}
	
	@Override
	public void loadList() throws ClassNotFoundException, SQLException {
		if(reloadNext()) {
			System.out.println("Se cargó");
		}else {
			System.out.println("No se recargó.");
		}
	}

	@Override
	public boolean add(DtoTicket ticket) throws ClassNotFoundException, SQLException {
		int id_added = (int) _dao.add(ticket);
		if( id_added != -1 ) {
			ticket.setId(id_added);
			_tickets.add(ticket);
			return true;
		}
		return false;
	}

	@Override
	public DtoTicket getOne(int position) {
		return _tickets.get(position);
	}

	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {
		if(_dao.delete(_tickets.get(position).getId())) {
			_tickets.remove(position);
			return true;
		}
		return false;
	}

	@Override
	public boolean update(DtoTicket dtoCar, int position) throws ClassNotFoundException, SQLException {
		if(_dao.update(dtoCar)) {
			_tickets.set(position,dtoCar);
			return true;
		}
		return false;
	}

	@Override
	public List<DtoTicket> getList() {
		return _tickets;
	}

	@Override
	public Interator<DtoTicket> getAll() {
		return new DaoInteractor<DtoTicket>(_tickets);
	}

	@Override
	public int sizeDtos() {
		return _tickets.size();
	}
	
	private boolean addedTicketInList(List<DtoTicket> tickesNews) {
		if (tickesNews != null) {
			if(tickesNews.size() > 0) {
				_tickets.addAll(tickesNews);
				return true;
			}else 
				return false;
		}else 
			return false;
	}

	@Override
	public boolean reloadNext() throws ClassNotFoundException, SQLException {
		return addedTicketInList(_paginator.next());
	}

	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {
		_tickets = _dao.getFilter(parameter, value);
	}

}
