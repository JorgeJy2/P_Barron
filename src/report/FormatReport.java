package report;

import java.sql.Connection;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter; 
import net.sf.jasperreports.export.SimplePdfExporterConfiguration; 

public abstract class FormatReport implements Report{


	protected JasperPrint  jasperPrint;
	protected String name;
	protected Connection conexion;
	protected JRPdfExporter exportar;
	protected JasperPrint jasperPrintWindow; 
	protected SimplePdfExporterConfiguration conf;

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}
	
	
	
}
