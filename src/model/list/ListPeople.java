package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoPeople;
import dao.SaveErrosDao;
import model.dto.DtoPeople;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;
import model.list.paginator.Paginator;
import model.list.paginator.PaginatorDao;

public class ListPeople implements Listable<DtoPeople> {

	private List<DtoPeople> _listPeople;
	private DaoInterface<DtoPeople> _daoPeople;
	private Paginator<DtoPeople> paginator;

	private static ListPeople _instance;

	protected ListPeople() {

		_listPeople = new ArrayList<DtoPeople>();
		_daoPeople = new DaoPeople();
		paginator = new PaginatorDao<DtoPeople>(_daoPeople);

	}

	public static ListPeople getInstance() {
		if (_instance == null) {
			_instance = new ListPeople();
		}
		return _instance;
	}

	@Override
	public List<DtoPeople> getList() {
		return _listPeople;
	}

	@Override
	public void loadList() throws ClassNotFoundException, SQLException {

		try {
			reloadNext();
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public boolean add(DtoPeople dtoPeople) throws ClassNotFoundException, SQLException {

		try {
			int id_added = (int) _daoPeople.add(dtoPeople);
			if (id_added != -1) {
				dtoPeople.setId(id_added);
				_listPeople.add(dtoPeople);
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
	public DtoPeople getOne(int position) {
		return _listPeople.get(position);
	}

	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoPeople.delete(_listPeople.get(position).getId())) {
				_listPeople.remove(position);
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
	public boolean update(DtoPeople dtoPeople, int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoPeople.update(dtoPeople)) {
				_listPeople.set(position, dtoPeople);
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
	public Interator<DtoPeople> getAll() {
		return new DaoInteractor<DtoPeople>(_listPeople);
	}

	@Override
	public int sizeDtos() {
		return _listPeople.size();
	}

	private boolean addedCarsInList(List<DtoPeople> peoplesNews) {
		if (peoplesNews != null) {
			if (peoplesNews.size() > 0) {
				_listPeople.addAll(peoplesNews);
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public boolean reloadNext() throws ClassNotFoundException, SQLException {

		try {
			return addedCarsInList(paginator.next());

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}
}
