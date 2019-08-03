package model.dto;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

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
	
	/*private DtoCar auto;
	private DtoPeople people;
	*/
	
	
	private String fechaEntrada;
	private Timestamp date;
	
	private String fechaSalida;
	private double totalPago;
	private String estatus;

	
	private int idAuto;
	private int idPesona;
	
	private String placaAuto;
	private String emailAuto;
	
	//Constructores
	
	//Sin parametro
	
	public DtoTicket() {
		this.id = 1;
		/*
		this.auto = new DtoCar();
		this.people = new DtoPeople();
		*/
		idAuto = 90;
		idPesona = 2;
		this.fechaEntrada = "13/06/2019";
		this.fechaSalida = "14/06/2019";
		this.totalPago = 30;
		this.estatus = "Pagado";
	}
	
	//Con parametros
	
	public DtoTicket(int id, int idAuto, int idPersona, String fechaEntrada, String fechaSalida,
			double total_pago,
			String estatus) {
		super();
		this.id = id;
		this.idAuto = idAuto;
		this.idAuto = idAuto;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.totalPago = total_pago;
		this.estatus = estatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
		this.notifyIObservers();
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public double getTotalPago() {
		return totalPago;
	}

	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public int getIdAuto() {
		return idAuto;
	}

	public void setIdAuto(int idAuto) {
		this.idAuto = idAuto;
	}

	public int getIdPesona() {
		return idPesona;
	}

	public void setIdPesona(int idPesona) {
		this.idPesona = idPesona;
	}

	public String getPlacaAuto() {
		return placaAuto;
	}

	public void setPlacaAuto(String placaAuto) {
		this.placaAuto = placaAuto;
	}

	public String getEmailAuto() {
		return emailAuto;
	}

	public void setEmailAuto(String emailAuto) {
		this.emailAuto = emailAuto;
	}
	
	

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DtoTicket [id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida
				+ ", totalPago=" + totalPago + ", estatus=" + estatus + ", idAuto=" + idAuto + ", idPesona=" + idPesona
				+ ", placaAuto=" + placaAuto + ", emailAuto=" + emailAuto + "]";
	}

	
	/*
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
	}*/


	/**
	 * toString
	 * 
	 * Proporciona una vista del estado de la clase.
	 * 
	 * @return valores de los atributos que almacena la instancia. 
	 */
	
	
	
	
}//End class
