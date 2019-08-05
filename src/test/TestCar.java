package test;

import java.sql.SQLException;

import model.dto.DtoCar;
import model.dto.DtoPeople;
import model.list.ListCar;
import model.list.interador.Interator;

/**
 * Archivo: TestCar.java contiene la definici髇 de la clase TestCar.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class TestCar {
	// declarar m閠odos
	private ListCar listCar;

	// Constructor sin par醡etros
	public TestCar() {
		listCar = ListCar.getInstance();
	}// cierre constructor

	// m閠odo selectTest
	public void selectTest() {
		try {
			listCar.loadList();
			Interator<DtoCar> interator = listCar.getAll();
			while (interator.hasNext()) {
				DtoCar car = interator.next();
				System.out.println(car);
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurri贸 un error en test de listCar, al momento de consultar.");
			System.err.println(e.getLocalizedMessage());
		}
	}// cierre m閠odo selectTest

	// m閠odo addTest
	public void addTest() {
		try {
			DtoCar car = new DtoCar();
			car.setColor("nuevo");
			car.setPlaca("GHGHG");
			listCar.add(car);
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurri贸 un error en test de listCar, al momento de agregar.");
			System.err.println(e.getLocalizedMessage());
		}
	}// cierre m閠odo addTest

	// m閠odo updateTest
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
			System.out.println("Ocurri贸 un error en test de listCar, al momento de actualizar.");
			System.err.println(e.getLocalizedMessage());
		}
	}// cierre m閠odo updateTest

	// m閠odo delteTest
	public void delteTest() {
		try {
			Interator<DtoCar> interator = listCar.getAll();
			DtoCar car = interator.first();
			if (car != null) {
				listCar.delete(0);
			} else
				System.out.println("Lista de coches vacia.");

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurri贸 un error en test de listCar, al momento de eliminar.");
			System.err.println(e.getLocalizedMessage());
		}
	}// cierre m閠odo delteTest

	/**
	 * M閠odo getFirst
	 * 
	 * @return retorna un objeto de tipo DtoCar
	 */
	public DtoCar getFirst() {
		try {
			listCar.loadList();
			Interator<DtoCar> interator = listCar.getAll();
			return interator.first();

		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Ocurri贸 un error en test de listCar, al momento de eliminar.");
			System.err.println(e.getLocalizedMessage());
			return null;
		}
	}// cierre m閠odo getFirst
}// cierre clase TestCar
