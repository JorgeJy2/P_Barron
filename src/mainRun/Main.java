package mainRun;

import java.sql.SQLException;
import dao.DaoAuto;
import dao.DaoBoleto;
import dao.DaoPeople;

public class Main {

	public static void main(String[] args) {
		// ViewUser ser = new ViewUser();
		// ser.viewUser();

		// TEST MAIN

		DaoPeople daoPeople = new DaoPeople();
		DaoAuto auto = new DaoAuto();
		DaoBoleto boleto = new DaoBoleto(); 
		try {
			/*
			 int result = (int ) daoPeople.add(new DtoPeople(0, "Jacobo", "Jacobo",
			 "Uribe", "5588821155", "amanda@gmail.com"));
			 
			 System.out.println(result);
			 */
			//DtoPeople dto = daoPeople.get(2);
			//System.out.println(dto);

			// lambda
			//daoPeople.getAll().stream().forEach(System.out::println);
			//ListPeople listPeople = ListPeople.getInstance();
			//listPeople.loadList();
			
			//auto.add(new DtoAuto());
			//DtoAuto auto2 = new DtoAuto();
			//auto2.setId(4);
			//auto.delete(5);
			//DtoAuto auto2 = new DtoAuto();
		//	auto2.setColor("Rojo");
			//auto2.setId(6);
			//auto2.setModelo("Ferrari");
			//auto2.setPlaca("Jorge");
			//auto.update(auto2);
			
			auto.getAll().stream().forEach(System.out::println);
			daoPeople.getAll().stream().forEach(System.out::println);
			boleto.getAll().stream().forEach(System.out::println);
			
			//listPeople.add(new DtoPeople(1, "Jorge","Jacobo","Francisco","55-145-23-23","jorge@gmail.com"));
			//listPeople.getList().stream().forEach(System.out::println);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getLocalizedMessage());
		}
	}
}
