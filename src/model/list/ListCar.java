package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCar;
import dao.DaoInterface;
import dao.DaoPeople;
import dao.SaveErrosDao;

import model.dto.DtoCar;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;
import model.list.paginator.Paginator;
import model.list.paginator.PaginatorDao;
import report.FormatReport;

public class ListCar implements Listable<DtoCar> {

	private static ListCar _instance;

	private DaoInterface<DtoCar> _daoAuto;

	private List<DtoCar> _listAuto;
	private Paginator<DtoCar> _paginator;

	protected ListCar() {
		_listAuto = new ArrayList<DtoCar>();
		_daoAuto = new DaoCar();
		_paginator = new PaginatorDao<DtoCar>(_daoAuto);
	}

	public static ListCar getInstance() {
		if (_instance == null) {
			_instance = new ListCar();
		}
		return _instance;
	}

	@Override
	public List<DtoCar> getList() {
		return _listAuto;
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

	private boolean addedCarsInList(List<DtoCar> carsNews) {
		if (carsNews != null) {
			if (carsNews.size() > 0) {
				_listAuto.addAll(carsNews);
				return true;
			}
		}

		return false;
	}

	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {

		try {
			_listAuto = _daoAuto.getFilter(parameter, value);
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}

	}

	@Override
	public boolean add(DtoCar dtoCar) throws ClassNotFoundException, SQLException {

		try {
			int id_added = (int) _daoAuto.add(dtoCar);

			if (id_added != -1) {
				dtoCar.setId(id_added);
				reorder(dtoCar);
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

	private void reorder(DtoCar car) {

		int sizeAutos = _listAuto.size();

		List<DtoCar> newList = new ArrayList<DtoCar>(sizeAutos);

		if (sizeAutos > 0) {
			newList.add(car);
			for (int i = 0; i < sizeAutos - 1; i++) {
				newList.add(_listAuto.get(i));
			}
			_listAuto = newList;
		}

	}

	@Override
	public DtoCar getOne(int position) {
		return _listAuto.get(position);
	}

	@Override
	public boolean delete(int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoAuto.delete(_listAuto.get(position).getId())) {
				_listAuto.remove(position);
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
	public boolean update(DtoCar dtoCar, int position) throws ClassNotFoundException, SQLException {

		try {
			if (_daoAuto.update(dtoCar)) {
				_listAuto.set(position, dtoCar);
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
	public Interator<DtoCar> getAll() {
		return new DaoInteractor<DtoCar>(_listAuto);
	}

	@Override
	public int sizeDtos() {
		return _listAuto.size();
	}

	@Override
	public boolean reloadNext() throws ClassNotFoundException, SQLException {
		try {
			return addedCarsInList(_paginator.next());
		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}

	public boolean getReport(FormatReport format) throws ClassNotFoundException, SQLException { 
		((DaoCar) _daoAuto).generateReport(format); 
		return true;
	}
}
