package model;

public class DtoAuto {

	private int id;
	private String modelo;
	private String placa;
	private String color;
	
	public DtoAuto() {
		id 		= 0;
		modelo 	= "Audi";
		placa 	= "39MX29";
		color 	= "Negro";
	}
	
	public DtoAuto(int id, String modelo, String placa, String color) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.placa = placa;
		this.color = color;
	}

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

	@Override
	public String toString() {
		return "DtoAuto [id=" + id + ", modelo=" + modelo + ", placa=" + placa + ", color=" + color + "]";
	}
	
	
	
}
