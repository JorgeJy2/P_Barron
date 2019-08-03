package model.list;

import java.sql.SQLException;
import java.util.List;

import model.list.interador.Interator;

public interface Listable<Dto> {
	
	void loadList () throws ClassNotFoundException, SQLException;
	boolean add (Dto dtoCar) throws ClassNotFoundException, SQLException;
	Dto getOne(int position);
	boolean delete(int position) throws ClassNotFoundException,SQLException;
	boolean update (Dto dtoCar, int position) throws ClassNotFoundException,SQLException;
	List<Dto> getList();
	Interator<Dto> getAll();
	int sizeDtos();
	boolean reloadNext() throws ClassNotFoundException, SQLException ;
	void loadListFilter(String parameter,String value) throws ClassNotFoundException, SQLException ;
}
