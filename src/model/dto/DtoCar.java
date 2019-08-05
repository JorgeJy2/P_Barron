package model.dto;

import observer.Observable;

/**
 * Archivo: DtoAuto.java
 * 
 * Objetivo: Representa la estructura de un auto en el mundo real.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class DtoCar extends Observable {
	// Atributos de clase
	private int id;
	private String modelo;
	private String placa;
	private String color;

	// Constructores

	// Constructor sin parametros
	public DtoCar() {
		id = 0;
		modelo = "Audi";
		placa = "39MX29";
		color = "Negro";
	}// cierre constructor

	/**
	 * Constructor con parámetros
	 * 
	 * @param id     valor de tipo entero
	 * @param modelo valor de tipo String
	 * @param placa  valor de tipo String
	 * @param color  valor de tipo String
	 */
	public DtoCar(int id, String modelo, String placa, String color) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.color = color;
	}// cierre constructor

	// Manejadores getters y setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyIObservers();
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
		this.notifyIObservers();
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
		this.notifyIObservers();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
		return "DtoAuto [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", color=" + color + "]";
	}

}// cierre clase DtoCar
