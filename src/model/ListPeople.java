package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoPeople;

public class ListPeople {

	private List<DtoPeople> _listPeople;
	private DaoInterface<DtoPeople> _daoPeople;

	private static ListPeople _instance;

	private ListPeople() {

		_listPeople = new ArrayList<DtoPeople>();
		_daoPeople 	= new DaoPeople();

	}

	public static ListPeople getInstance() {
		if (_instance == null) {
			_instance = new ListPeople();
		}
		return _instance;
	}
	
	public List<DtoPeople> getList(){
		return _listPeople;
	}
	
	public void loadList () throws ClassNotFoundException, SQLException {
		_listPeople = _daoPeople.getAll();
	}
	
	public void add(DtoPeople dtoPeople) throws ClassNotFoundException, SQLException {
		int id_added = (int) _daoPeople.add(dtoPeople);
		if( id_added != -1 ) {
			dtoPeople.setId(id_added);
			_listPeople.add(dtoPeople);
		}
	}
	public DtoPeople getOne(int posicon) {
		return _listPeople.get(posicon);
	}
	
	public void delete(int poscion) throws ClassNotFoundException, SQLException {
		if(_daoPeople.delete(_listPeople.get(poscion))) {
			_listPeople.remove(poscion);
		}
	}
	
	public void update(DtoPeople dtoPeople, int posicion) throws ClassNotFoundException, SQLException {
		if(_daoPeople.update(dtoPeople)) {
			_listPeople.set(posicion,dtoPeople);
		}
	}
}
