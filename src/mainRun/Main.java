package mainRun;

import java.sql.SQLException;

import dao.DaoPeople;
import model.DtoPeople;
import view.ViewUser;

public class Main {

	public static void main(String[] args) {
		//ViewUser ser = new ViewUser();
		//ser.viewUser();
		
		//TEST MAIN 
		
		DaoPeople daoPeople = new DaoPeople();
		try {
			//int result = (int ) daoPeople.add(new DtoPeople(0, "Jacobo", "Jacobo", "Uribe", "5588821155", "amanda@gmail.com"));
			//	System.out.println(result);
			
			DtoPeople dto =  daoPeople.get(2);
			System.out.println(dto);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
	}
}
