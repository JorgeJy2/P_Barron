package model.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoCar;
import dao.DaoInterface;

import dao.SaveErrosDao;

import model.dto.DtoCar;

import model.list.interador.DaoInteractor;
import model.list.interador.Interator;
import model.list.paginator.Paginator;
import model.list.paginator.PaginatorDao;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

/**
 * Archivo: ListCar.java contiene la definición de la clase ListCar que
 * implementa Listable<DtoCar>.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class ListCar implements Listable<DtoCar> {
	// declaración de atrtibutoa
	private static ListCar _instance;

	private DaoInterface<DtoCar> _daoAuto;

	private List<DtoCar> _listAuto;
	private Paginator<DtoCar> _paginator;

	// constructor sin parámetros
	protected ListCar() {
		_listAuto = new ArrayList<DtoCar>();
		_daoAuto = new DaoCar();
		_paginator = new PaginatorDao<DtoCar>(_daoAuto);
	}// cierre constructor

	/**
	 * Instancia de la clase ListCar
	 * 
	 * @return retorna la instancia
	 */
	public static ListCar getInstance() {
		if (_instance == null) {
			_instance = new ListCar();
		}
		return _instance;
	}// cierre instancia

	/**
	 * Método getList
	 * 
	 * @return retorna la lista de carros
	 */
	@Override
	public List<DtoCar> getList() {
		return _listAuto;
	}// cierre método getList

	/**
	 * Método loadList
	 * 
	 * @throws excepcion de clase y base de datos
	 */
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
	}// cierre método loadList

	/**
	 * Método addedCarsInList
	 * 
	 * @param carsNews objeto de tipo List
	 * @return retorna un valor booleano
	 */
	private boolean addedCarsInList(List<DtoCar> carsNews) {
		if (carsNews != null) {
			if (carsNews.size() > 0) {
				_listAuto.addAll(carsNews);
				return true;
			}
		}

		return false;
	}// cierre método addedCarsInList

	/**
	 * Método loadListFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @throws excepcion de tipo clase y base de datos
	 */
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
	}// cierre método loadListFilter

	/**
	 * Método add
	 * 
	 * @param dtoCar objeto de tipo DtoCar
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
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
	}// cierre método add

	/**
	 * Método reorder
	 * 
	 * @param car objeto de tipo DtoCar
	 */
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

	}// cierre método reorder

	/**
	 * Método getOne
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoCar
	 */
	@Override
	public DtoCar getOne(int position) {
		return _listAuto.get(position);
	}// cierre método getOne

	/**
	 * Método delete
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un valor de tipo booleano
	 * @throws exceopcion de tipo clase y base de datos
	 */
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
	}// cierre método delete

	/**
	 * Método update
	 * 
	 * @param dtoCar   objeto de tipo DtoCar
	 * @param position valor de tipo entero
	 * @return retorna una valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
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

	}// cierre método update

	/**
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Interator
	 */
	@Override
	public Interator<DtoCar> getAll() {
		return new DaoInteractor<DtoCar>(_listAuto);
	}// cierre método Interator

	/**
	 * Método sizeDtos
	 * 
	 * @return retorna un valor de tipo entero
	 */
	@Override
	public int sizeDtos() {
		return _listAuto.size();
	}// cierre método sizeDtos

	/**
	 * Método reloadNext
	 * 
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
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
	}// cierre método reloadNext

	/**
	 * Método getReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @return retorna un valor de tipo booleano
	 */
	@Override
	public boolean getReport(FormatReport format)
			throws ClassNotFoundException, SQLException, JRException, IOException {
		_daoAuto.generateReport(format);
		return true;
	}// cierre de método getReport
}// cierre de clase ListCar
