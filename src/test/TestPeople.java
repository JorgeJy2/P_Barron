package test;

import java.sql.SQLException;

import model.dto.DtoPeople;
import model.list.ListPeople;
import model.list.interador.Interator;

public class TestPeople {

	private ListPeople listPeople;

	public TestPeople() {
		listPeople = ListPeople.getInstance();
	}

	public void selectTest() {
		try {
			listPeople.loadList();
			Interator<DtoPeople> interator = listPeople.getAll();
			while (interator.hasNext()) {
				DtoPeople car = interator.next();
				System.out.println(car);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listPeople, al momento de consultar.");
			System.err.println(e.getLocalizedMessage());
		}
	}

	public void addTest() {
		try {
			DtoPeople people = new DtoPeople();
			people.setName("Jacobo");
			people.setEmail("josdsj@gmail.com");
			listPeople.add(people);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listPeople, al momento de agregar.");
			System.err.println(e.getLocalizedMessage());
		}
	}

	
	public void updateTest() {
		try {
			Interator<DtoPeople> interator = listPeople.getAll();
			DtoPeople people= interator.first();
			if (people != null) {
				people.setName("Otro nombre");
				listPeople.update(people, 0);
			} else
				System.out.println("Lista de coches vacia.");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listPeople, al momento de actualizar.");
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	
	public void delteTest() {
		try {
			Interator<DtoPeople> interator = listPeople.getAll();
			DtoPeople car = interator.first();
			if (car != null) {
				listPeople.delete(0);
			} else
				System.out.println("Lista de coches vacia.");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listPeople, al momento de eliminar.");
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	public DtoPeople getFirst() {
		
		Interator<DtoPeople> interator = listPeople.getAll();
			return  interator.first();
		
	}
}
