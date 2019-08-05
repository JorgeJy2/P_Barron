package model.list;

import java.io.IOException;
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
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

/**
 * Archivo: ListPeople.java contiene la definición de la clase ListPeople que
 * implementa la interfaz Listable.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class ListPeople implements Listable<DtoPeople> {
	// declaración de atributos
	private List<DtoPeople> _listPeople;
	private DaoInterface<DtoPeople> _daoPeople;
	private Paginator<DtoPeople> paginator;

	private static ListPeople _instance;

	// constructor si parámetros
	protected ListPeople() {

		_listPeople = new ArrayList<DtoPeople>();
		_daoPeople = new DaoPeople();
		paginator = new PaginatorDao<DtoPeople>(_daoPeople);

	}// cierre constructor

	/**
	 * Instancia de la clase ListPeople
	 * 
	 * @return retorna la instancia
	 */
	public static ListPeople getInstance() {
		if (_instance == null) {
			_instance = new ListPeople();
		}
		return _instance;
	}// cierre de instancia

	/**
	 * Método getList
	 * 
	 * @return retorna la lista de personas
	 */
	@Override
	public List<DtoPeople> getList() {
		return _listPeople;
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
	 * Método add
	 * 
	 * @param dtoPeople objeto de tipo DtoPeople
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
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

	}// cierre método add

	/**
	 * Método getOne
	 * 
	 * @param position valor de tipo entero
	 * @return retorna un objeto de tipo DtoPeople
	 */
	@Override
	public DtoPeople getOne(int position) {
		return _listPeople.get(position);
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
	}// cierre método delete

	/**
	 * Método update
	 * 
	 * @param dtoPeople objeto de tipo DtoPeople
	 * @param position  valor de tipo entero
	 * @return retorna una valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
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
	}// cierre método update

	/**
	 * Método getAll
	 * 
	 * @return retorna un objeto de tipo Interator
	 */
	@Override
	public Interator<DtoPeople> getAll() {
		return new DaoInteractor<DtoPeople>(_listPeople);
	}// cierre método getAll

	/**
	 * Método sizeDtos
	 * 
	 * @return retorna un valor de tipo entero
	 */
	@Override
	public int sizeDtos() {
		return _listPeople.size();
	}// cierre método sizeDtos

	/**
	 * Método addedPeopleInList
	 * 
	 * @param peoplesNews objeto de tipo List
	 * @return retorna un valor booleano
	 */
	private boolean addedPeopleInList(List<DtoPeople> peoplesNews) {
		if (peoplesNews != null) {
			if (peoplesNews.size() > 0) {
				_listPeople.addAll(peoplesNews);
				return true;
			} else
				return false;
		} else
			return false;
	}// cierre método addedPeopleInList

	/**
	 * Método reloadNext
	 * 
	 * @return retorna un valor de tipo booleano
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public boolean reloadNext() throws ClassNotFoundException, SQLException {

		try {
			return addedPeopleInList(paginator.next());

		} catch (ClassNotFoundException e) {
			SaveErrosDao.saveErrors(e);
			throw new ClassNotFoundException(e.getMessage());
		} catch (SQLException e) {
			SaveErrosDao.saveErrors(e);
			throw new SQLException(e.getMessage());
		}
	}// cierre método reloadNext

	/**
	 * Método loadListFilter
	 * 
	 * @param parameter valor de tipo String
	 * @param value     valor de tipo String
	 * @throws excepcion de tipo clase y base de datos
	 */
	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {
		_listPeople = _daoPeople.getFilter(parameter, value);

	}

	/**
	 * Método getReport
	 * 
	 * @param format objeto de tipo FormatReport
	 * @return retorna un valor de tipo booleano
	 */
	@Override
	public boolean getReport(FormatReport format)
			throws ClassNotFoundException, SQLException, JRException, IOException {
		_daoPeople.generateReport(format);
		return true;
	}// cierre método getReport
}// cierre clase ListPeople
