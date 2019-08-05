package model.dto;

import observer.Observable;

/**
 * Archivo: DtoPeople.java
 * 
 * Objetivo: Representa la estructura de una persona en el mundo real.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */

public class DtoPeople extends Observable {

	// Atributos de clase
	private int id;
	private String name;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;

	// Constructores

	// Sin parametros

	public DtoPeople() {
		id = 1;
		name = "Juan";
		firstName = "Pedro";
		lastName = "Gutierrez";
		telephone = "(55)- 66-11-22-12";
		email = "pedro@gmail.com";

	}// cierre constructor

	/**
	 * Construtor con parámetros
	 * 
	 * @param id        valor de tipo entero
	 * @param name      valor de tipo String
	 * @param firstName valor de tipo String
	 * @param lastName  valor de tipo String
	 * @param telephone valor de tipo String
	 * @param email     valor de tipo String
	 */
	public DtoPeople(int id, String name, String firstName, String lastName, String telephone, String email) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
		this.email = email;
	}// cierre constructor

	// Manejadores getters y setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyIObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.notifyIObservers();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		this.notifyIObservers();
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		this.notifyIObservers();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
		this.notifyIObservers();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
		this.notifyIObservers();
	}

	/**
	 * toString
	 * 
	 * Proporciona una vista del estado de la clase.
	 * 
	 * @return valores de los atributos que almacena la instancia.
	 */

	@Override
	public String toString() {
		return "DtoPeople [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}

}// Cierre clase DtoPeople
