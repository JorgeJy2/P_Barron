package model.list;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import dao.DaoPeople;

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
		_daoPeople 	= new DaoPeople();
		paginator = new PaginatorDao<DtoPeople>(_daoPeople);
	
	}

	public static ListPeople getInstance() {
		if (_instance == null) {
			_instance = new ListPeople();
		}
		return _instance;
	}
	
	
	@Override
	public List<DtoPeople> getList(){
		return _listPeople;
	}

	@Override
	public void loadList () throws ClassNotFoundException, SQLException {
		if(reloadNext()) {
			System.out.println("Se cargó people");
		}else {
			System.out.println("No se recargó people.");
		}
	}
	
	@Override
	public void add(DtoPeople dtoPeople) throws ClassNotFoundException, SQLException {
		int id_added = (int) _daoPeople.add(dtoPeople);
		if( id_added != -1 ) {
			dtoPeople.setId(id_added);
			_listPeople.add(dtoPeople);
		}
	}
	
	@Override
	public DtoPeople getOne(int position) {
		return _listPeople.get(position);
	}
	
	@Override
	public void delete(int position) throws ClassNotFoundException, SQLException {
		if(_daoPeople.delete(_listPeople.get(position).getId()))
			_listPeople.remove(position);
	}
	
	@Override
	public void update(DtoPeople dtoPeople, int position) throws ClassNotFoundException, SQLException {
		if(_daoPeople.update(dtoPeople))
			_listPeople.set(position,dtoPeople);
	}

	@Override
	public Interator<DtoPeople> getAll() {
		return  new DaoInteractor<DtoPeople>(_listPeople);
	}
	
	@Override
	public int sizeDtos() {
		return _listPeople.size();
	}

	private boolean addedCarsInList(List<DtoPeople> peoplesNews) {
		if (peoplesNews != null) {
			if(peoplesNews.size() > 0) {
				_listPeople.addAll(peoplesNews);
				return true;
			} else 
				return false;
		} else 
			return false;
	}
	@Override
	public boolean reloadNext()  throws ClassNotFoundException, SQLException {
		return addedCarsInList(paginator.next());
	}

	@Override
	public void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
