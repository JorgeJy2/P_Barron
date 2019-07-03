package model.list;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import dao.DaoInterface;
import model.dto.DtoUser;

public class ListUser {

	private DaoInterface<DtoUser> DAOUser;
	private List<DtoUser> _listUser;
	
	private static ListUser instance;

	private ListUser() {
		_listUser = new ArrayList<DtoUser>();
		DAOUser = new dao.DaoUser();
	}

	public static ListUser geInstance() {
		if (instance == null) {
			instance = new ListUser();
		}
		return instance;
	}

	public void loadList() throws ClassNotFoundException, SQLException {
		_listUser = DAOUser.getAll();
	}

	// Manejadores
	public List<DtoUser> getList() {
		return _listUser;
	}

	public void add(DtoUser dtoUser) throws ClassNotFoundException, SQLException {
		int resultId = (int) DAOUser.add(dtoUser);
		if (resultId != -1) {
			dtoUser.setId(resultId);
			_listUser.add(dtoUser);
		}
	}

	public void update(DtoUser dtoUser, int posicon) throws ClassNotFoundException, SQLException {
		if (DAOUser.update(_listUser.get(posicon))) {
			_listUser.set(posicon, dtoUser);
		}
	}

	public void delete(int posicion) throws ClassNotFoundException, SQLException {
		if (DAOUser.delete(_listUser.get(posicion))) {
			_listUser.remove(posicion);
		}
	}

	public DtoUser getOne(int posicion) {
		return _listUser.get(posicion);
	}

}
