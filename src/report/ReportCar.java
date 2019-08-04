package report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
 
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration; 

public class ReportCar extends FormatReport{

	private static final String REPORTE = "reporte.jasper";
	
	@Override
	public void obtenerInforme() { 
		try {
			jasperPrint = JasperFillManager.fillReport("reports/"+REPORTE, null,conexion);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void compilarInforme() {
		try {
		exportar = new JRPdfExporter();
		exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
		exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("reports/reporte.pdf"));
		conf = new SimplePdfExporterConfiguration();
		exportar.setConfiguration(conf);
		exportar.exportReport();
		} catch (JRException e) { 
			e.printStackTrace();
		}  
	} 
	@Override
	public void MuestraInforme() {
		try {
		     File path = new File ("reports/reporte.pdf");
		     Desktop.getDesktop().open(path);
		}catch (IOException ex) {
		     ex.printStackTrace();
		}
	} 

}
