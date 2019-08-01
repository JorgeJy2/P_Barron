package model.list;

import java.sql.SQLException;
import java.util.List;
import model.list.interador.Interator;

public interface Listable<Dto> {
	
	public void loadList () throws ClassNotFoundException, SQLException;
	public void add (Dto dtoCar) throws ClassNotFoundException, SQLException;
	public Dto getOne(int position);
	public void delete(int position) throws ClassNotFoundException,SQLException;
	public void update (Dto dtoCar, int position) throws ClassNotFoundException,SQLException;
	public List<Dto> getList();
	public Interator<Dto> getAll();
	public int sizeDtos();
	
	public boolean reloadNext() throws ClassNotFoundException, SQLException ;
	public void loadListFilter(String parameter,String value) throws ClassNotFoundException, SQLException ;
}
