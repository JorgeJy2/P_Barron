package dao;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class SaveErrosDao {
	
	private static final String PATH = "errors_report/err_dao.txt";
	
	public static void saveErrors(Exception e){
		FileOutputStream fErr;
		try{
			fErr = new FileOutputStream( PATH );
			PrintStream stdErr= new PrintStream(fErr);
			System.setErr(stdErr);
		}catch(Exception ex){
			System.out.println("Ocurrio un error al querder guardar... ");
			System.out.println(ex.getLocalizedMessage());
		}
		e.printStackTrace();
		
	}
}
