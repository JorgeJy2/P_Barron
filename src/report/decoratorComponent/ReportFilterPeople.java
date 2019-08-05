package report.decoratorComponent;
 
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import controller.ControllerPeople;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration; 
import report.Report;

public class ReportFilterPeople extends DecoradorReporte{

	private static final String REPORT_PEOPLE_FILTER = "rep_filter_people.jasper";
	
	private ControllerPeople controller;
	
	private Map<String, Object> parameters;
	public ReportFilterPeople(ControllerPeople controller, Report report) {
		super(report);
		this.controller = controller;
		obtenerParametros();
	}

	
	public void obtenerParametros() {  
			 parameters = new HashMap<String, Object>();
			 parameters.put("search", controller.getParametro());  
	}
	
	@Override
	public void obtenerInforme() { 
		try {
			jasperPrint = JasperFillManager.fillReport("reports/"+REPORT_PEOPLE_FILTER, parameters,conexion);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
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
