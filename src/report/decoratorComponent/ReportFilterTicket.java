package report.decoratorComponent;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import controller.ControllerTicket;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import report.Report;

/**
 * Archivo: ReportFilterTicket.java contiene la definición de la clase
 * ReportFilterTicket que extiende de DecoradorReporte.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class ReportFilterTicket extends DecoradorReporte {
	// declaración de atributos
	private static final String REPORT_PEOPLE_FILTER = "boleto_filter.jasper";

	private ControllerTicket controller;

	private Map<String, Object> parameters;

	/**
	 * Constructor con parámetros
	 * 
	 * @param controller objeto de tipo ControllerTicket
	 * @param report     objeto de tipo Report
	 */
	public ReportFilterTicket(ControllerTicket controller, Report report) {
		super(report);
		this.controller = controller;
		obtenerParametros();
	}// cierre constructor

	// método obtenerParametros
	public void obtenerParametros() {
		parameters = new HashMap<String, Object>();
		parameters.put("estatus", controller.getParametro());
	}// cierre método obtenerParametros

	/**
	 * Método obtenerInforme
	 * 
	 * @throws JRException
	 */
	@Override
	public void obtenerInforme() throws JRException {
		jasperPrint = JasperFillManager.fillReport("reports/" + REPORT_PEOPLE_FILTER, parameters, conexion);
	}// cierre método obtenerInforme

	/**
	 * Méttodo compilarInforme
	 * 
	 * @throws excepcion JRException
	 */
	@Override
	public void compilarInforme() throws JRException {

		exportar = new JRPdfExporter();
		exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
		exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("reports/filter_boleto.pdf"));
		conf = new SimplePdfExporterConfiguration();
		exportar.setConfiguration(conf);
		exportar.exportReport();
	}// cierre método compilarInforme

	/**
	 * Método MuestraInforme
	 * 
	 * @throws excepcion IOException
	 */
	@Override
	public void MuestraInforme() throws IOException {

		File path = new File("reports/filter_boleto.pdf");
		Desktop.getDesktop().open(path);

	}// cierre método MuestraInforme

}// cierre clase ReportFilterTicket
