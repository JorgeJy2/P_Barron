package model.dto;

/**
 * Clase DTO de People
 * 
 * Versi�n 1
 * 
 * @author jorge Date : 04/05/2019
 * 
 */

public class DtoPeople {

	/* Atributos */

	private int id;
	private String name;
	private String firstName;
	private String lastName;
	private String telephone;
	private String email;

	/* Constructores */
	// Por defecto
	public DtoPeople() {
		id 			= 1;
		name 		= "Juan";
		firstName 	= "Pedro";
		lastName 	= "Gutierrez";
		telephone 	= "(55)- 66-11-22-12";
		email 		= "pedro@gmail.com";

	}

	// Con parametros
	public DtoPeople(int id, String name, String firstName, String lastName, String telephone, String email) {
		this.id 		= id;
		this.name 		= name;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.telephone 	= telephone;
		this.email 		= email;
	}

	/* Metodos */

	// Manejadores
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DtoPeople [id=" + id + ", name=" + name + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", telephone=" + telephone + ", email=" + email + "]";
	}
	
	

}// Fin de la clase
