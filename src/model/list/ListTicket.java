package model.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoTicket;
import dao.SaveErrosDao;
import model.dto.DtoTicket;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

public class ListTicket implements Listable<DtoTicket> {

	private static ListTicket instance;

	private DaoInterface<DtoTicket> _dao;
	private List<DtoTicket> _tickets;
//	private Paginator<DtoTicket> _paginator;

	private ListTicket() {
		_dao = new DaoTicket();
		_tickets = new ArrayList<DtoTicket>();
		//_paginator = new PaginatorDao<DtoTicket>(_dao);
	}

	public static ListTicket getInstance() {

		if (instance == null)
			instance = new ListTicket();

		return instance;
	}

	@Override
	public void loadList() throws ClassNotFoundException, SQLException {

		try {
			this._tickets = _dao.getAll();
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean add(DtoTicket ticket) throws ClassNotFoundException, SQLException {

		try {
			int id_added = (int) _dao.add(ticket);
			if (id_added != -1) {
				ticket.setId(id_added);
				_tickets.add(ticket);
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public DtoTicket getOne(int position) {
		return _tickets.get(position);
	}

	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {

		try {
			if (_dao.delete(_tickets.get(position).getId())) {
				_tickets.remove(position);
				return true;
			}
			return false;
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean update(DtoTicket dtoCar, int position) throws ClassNotFoundException, SQLException {

		try {
			if (_dao.update(dtoCar)) {
				_tickets.set(position, dtoCar);
				return true;
			}
			return false;

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
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
			if (tickesNews.size() > 0) {
				_tickets.addAll(tickesNews);
				return true;
			} else
				return false;
		} else
			return false;
	}


	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {

		try {
			System.out.println("parameter: "+parameter +" value "+ value);
			_tickets = _dao.getFilter(parameter, value);
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean reloadNext() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean getReport(FormatReport format)  throws ClassNotFoundException, SQLException, JRException, IOException { 
		_dao.generateReport(format); 
		return true;
	}

}
