package dao;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Archivo: SaveErrosDao.java contiene la definición de la clase SaveErrosDao.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class SaveErrosDao {
	// declaración de atributos
	private static final String PATH = "errors_report/err_dao.txt";

	/**
	 * Método saveErrors
	 * 
	 * @param e objeto de tipo Exception
	 */
	public static void saveErrors(Exception e) {
		FileOutputStream fErr;
		try {
			fErr = new FileOutputStream(PATH);
			PrintStream stdErr = new PrintStream(fErr);
			System.setErr(stdErr);
		} catch (Exception ex) {
			System.out.println("Ocurrio un error al querder guardar... ");
			System.out.println(ex.getLocalizedMessage());
		}
		e.printStackTrace();

	}// cierre método saveErrors
}// cierre clase SaveErrorsDao
