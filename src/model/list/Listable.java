package model.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.list.interador.Interator;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

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
	
	boolean getReport(FormatReport format)  throws ClassNotFoundException, SQLException, JRException, IOException;
}
