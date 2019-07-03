package model.list;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DaoAuto;
import dao.DaoInterface;
import model.dto.DtoAuto;


public class ListAuto {
	private List<DtoAuto> _listAuto;
	private DaoInterface<DtoAuto> _daoAuto;
	
	private static ListAuto _instance;
	
	private ListAuto() {
		_listAuto = new ArrayList<DtoAuto>();
		_daoAuto = new DaoAuto();
	}
	
	public static ListAuto getInstance() {
		if (_instance == null) {
			_instance = new ListAuto();
		}
		return _instance;
	}
	
	public List<DtoAuto> getList(){
		return _listAuto;
	}
	
	public void loadList () throws ClassNotFoundException, SQLException{
		_listAuto = _daoAuto.getAll();
	}
	
	public void add (DtoAuto dtoAuto) throws ClassNotFoundException, SQLException{
		int id_added = (int) _daoAuto.add(dtoAuto);
		if( id_added != -1 ) {
			dtoAuto.setId(id_added);
			_listAuto.add(dtoAuto);
		}	
	}
	
	public DtoAuto getOne(int position) {
		return _listAuto.get(position);
	}
	
	public void delete(int position) throws ClassNotFoundException,SQLException{
		if(_daoAuto.delete(_listAuto.get(position))) {
			_listAuto.remove(position);
		}
	}
	
	public void update (DtoAuto dtoAuto, int position) throws ClassNotFoundException,SQLException{
		if(_daoAuto.update(dtoAuto)) {
			_listAuto.set(position,dtoAuto);
		}
	}
}
