package model.dto;

public class DtoBoleto {

	private int id;
	
	private DtoAuto auto;
	private DtoPeople people;
	
	private String fecha_entrada;
	private String fecha_salida;
	private double total_pago;
	private String estatus;

	public DtoBoleto() {
		this.id = 1;
		this.auto = new DtoAuto();
		this.people = new DtoPeople();
		this.fecha_entrada = "13/06/2019";
		this.fecha_salida = "14/06/2019";
		this.total_pago = 30;
		this.estatus = "Pagado";
	}
	
	public DtoBoleto(int id, DtoAuto auto, DtoPeople people, String fecha_entrada, String fecha_salida,
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
	}

	
	public DtoAuto getAuto() {
		return auto;
	}

	public void setAuto(DtoAuto auto) {
		this.auto = auto;
	}

	public DtoPeople getPeople() {
		return people;
	}

	public void setPeople(DtoPeople people) {
		this.people = people;
	}

	public String getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public double getTotal_pago() {
		return total_pago;
	}

	public void setTotal_pago(double total_pago) {
		this.total_pago = total_pago;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus_boleto) {
		this.estatus = estatus_boleto;
	}

	@Override
	public String toString() {
		return "DtoBoleto [id=" + id + ", auto=" + auto + ", user=" + people + ", fecha_entrada=" + fecha_entrada
				+ ", fecha_salida=" + fecha_salida + ", total_pago=" + total_pago + ", estatus=" + estatus + "]";
	}
	
	
	

}
