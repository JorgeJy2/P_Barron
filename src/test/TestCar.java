package test;

import java.sql.SQLException;

import model.dto.DtoCar;
import model.dto.DtoPeople;
import model.list.ListCar;
import model.list.interador.Interator;

public class TestCar {

	private ListCar listCar;

	public TestCar() {
		listCar = ListCar.getInstance();
	}

	public void selectTest() {
		try {
			listCar.loadList();
			Interator<DtoCar> interator = listCar.getAll();
			while (interator.hasNext()) {
				DtoCar car = interator.next();
				System.out.println(car);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listCar, al momento de consultar.");
			System.err.println(e.getLocalizedMessage());
		}
	}

	public void addTest() {
		try {
			DtoCar car = new DtoCar();
			car.setColor("nuevo");
			car.setPlaca("GHGHG");
			listCar.add(car);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listCar, al momento de agregar.");
			System.err.println(e.getLocalizedMessage());
		}
	}

	
	public void updateTest() {
		try {
			Interator<DtoCar> interator = listCar.getAll();
			DtoCar car = interator.first();
			if (car != null) {
				car.setColor("Amarillo");
				listCar.update(car, 0);
			} else
				System.out.println("Lista de coches vacia.");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listCar, al momento de actualizar.");
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	
	public void delteTest() {
		try {
			Interator<DtoCar> interator = listCar.getAll();
			DtoCar car = interator.first();
			if (car != null) {
				listCar.delete(0);
			} else
				System.out.println("Lista de coches vacia.");
			
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listCar, al momento de eliminar.");
			System.err.println(e.getLocalizedMessage());
		}
	}
	
	public DtoCar getFirst() {
		try {
			listCar.loadList();
			Interator<DtoCar> interator = listCar.getAll();
			return interator.first();
		
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurrió un error en test de listCar, al momento de eliminar.");
			System.err.println(e.getLocalizedMessage());
			return null;
		}
	}
}
