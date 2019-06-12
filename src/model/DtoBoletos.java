package model;

public class DtoBoletos {
		private int id;
		private int id_auto;
		private int id_persona;
		private String fecha_entrada;
		private String fecha_salida;
		private double total_pago;
		private String estatus_boleto;
		
		public DtoBoletos() {
				this.id=1;
				this.id_auto=1;
				this.id_persona=1;
				this.fecha_entrada="13/06/2019";
				this.fecha_salida="14/06/2019";
				this.total_pago=30;
				this.estatus_boleto="Pagado";
		}
		
		public DtoBoletos(int id,int id_auto,int id_persona,String fecha_entrada,String fecha_salida,double total_pago,String estatus_boleto) {
			
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId_auto() {
			return id_auto;
		}

		public void setId_auto(int id_auto) {
			this.id_auto = id_auto;
		}

		public int getId_persona() {
			return id_persona;
		}

		public void setId_persona(int id_persona) {
			this.id_persona = id_persona;
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

		public String getEstatus_boleto() {
			return estatus_boleto;
		}

		public void setEstatus_boleto(String estatus_boleto) {
			this.estatus_boleto = estatus_boleto;
		}
		
		
		
}
