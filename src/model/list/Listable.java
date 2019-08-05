package model.list;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.list.interador.Interator;
import net.sf.jasperreports.engine.JRException;
import report.FormatReport;

/**
 * Archivo: Listable.java contiene la definición de la interface Listable
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 * @param <Dto> Recibe un modelo Dto
 */
public interface Listable<Dto> {
	// métodos de la interface
	void loadList() throws ClassNotFoundException, SQLException;

	boolean add(Dto dtoCar) throws ClassNotFoundException, SQLException;

	Dto getOne(int position);

	boolean delete(int position) throws ClassNotFoundException, SQLException;

	boolean update(Dto dtoCar, int position) throws ClassNotFoundException, SQLException;

	List<Dto> getList();

	Interator<Dto> getAll();

	int sizeDtos();

	boolean reloadNext() throws ClassNotFoundException, SQLException;

	void loadListFilter(String parameter, String value) throws ClassNotFoundException, SQLException;

	boolean getReport(FormatReport format) throws ClassNotFoundException, SQLException, JRException, IOException;
}// cierre interface Listable
