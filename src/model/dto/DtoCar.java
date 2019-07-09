package model.dto;
/**
 * Archivo: DtoAuto.java
 * 
 * Objetivo: Representa la estructura de un auto en el mundo real.
 *  
 * @author jorge
 *
 */
public class DtoCar {

	//Atributos
	
	//Atributos de clase
	private int id;
	private String modelo;
	private String placa;
	private String color;
	
	//Constructores
	
	//Constructor sin parametros
	public DtoCar() {
		id 		= 0;
		modelo 	= "Audi";
		placa 	= "39MX29";
		color 	= "Negro";
	}
	
	//Constructor con parametros
	public DtoCar(int id, String modelo, String placa, String color) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.color = color;
	}

	//Manejadores

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "DtoAuto [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", color=" + color + "]";
	}
	
}//End class
