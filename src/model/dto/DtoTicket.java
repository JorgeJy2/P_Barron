package model.dto;

import observer.Observable;

/**
 * Archivo: DtoAuto.java
 * 
 * Objetivo: Representa la estructura de un  boleto en el mundo real.
 *  
 * @author jorge
 * Contiene la composición de las clases: 
 * 
 * {@link DtoCar} 	 automovil que se ha estacionado. 
 * {@link DtoPeople} persona que ha estacionado el automovil. 
 * 
 *
 */
public class DtoTicket extends Observable{

	//Atributos
	
	//Atributos de clase
	
	private int id;
	
	private DtoCar auto;
	private DtoPeople people;
	
	private String fecha_entrada;
	private String fecha_salida;
	private double total_pago;
	private String estatus;

	//Constructores
	
	//Sin parametro
	
	public DtoTicket() {
		this.id = 1;
		this.auto = new DtoCar();
		this.people = new DtoPeople();
		this.fecha_entrada = "13/06/2019";
		this.fecha_salida = "14/06/2019";
		this.total_pago = 30;
		this.estatus = "Pagado";
	}
	
	//Con parametros
	
	public DtoTicket(int id, DtoCar auto, DtoPeople people, String fecha_entrada, String fecha_salida,
			double total_pago,
			String estatus) {
		super();
		this.id = id;
		this.auto = auto;
		this.people = people;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.total_pago = total_pago;
		this.estatus = estatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyIObservers();
	}

	
	public DtoCar getAuto() {
		return auto;
	}

	public void setAuto(DtoCar auto) {
		this.auto = auto;
		this.notifyIObservers();
	}

	public DtoPeople getPeople() {
		return people;
	}

	public void setPeople(DtoPeople people) {
		this.people = people;
		this.notifyIObservers();
	}

	public String getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
		this.notifyIObservers();
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
		this.notifyIObservers();
	}

	public double getTotal_pago() {
		return total_pago;
	}

	public void setTotal_pago(double total_pago) {
		this.total_pago = total_pago;
		this.notifyIObservers();
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus_boleto) {
		this.estatus = estatus_boleto;
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
		return "DtoBoleto [id=" + id + ", auto=" + auto + ", user=" + people + ", fecha_entrada=" + fecha_entrada
				+ ", fecha_salida=" + fecha_salida + ", total_pago=" + total_pago + ", estatus=" + estatus + "]";
	}
	
}//End class
