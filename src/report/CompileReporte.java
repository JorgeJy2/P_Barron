package report;

import java.sql.Connection;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Archivo: CompileReporte.java contiene la definición de la clase
 * CompileReporte.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public class CompileReporte {

	/**
	 * Método excuteReport
	 * 
	 * @param conexion objeto de tipo Connection
	 * @param name     valor de tipo String
	 */
	public static void excecuteReport(Connection conexion, String name) {
		try {
			JasperPrint jasperPrint = JasperFillManager.fillReport("reports/" + name, null, conexion);

			JRPdfExporter exp = new JRPdfExporter();
			exp.setExporterInput(new SimpleExporterInput(jasperPrint));
			exp.setExporterOutput(new SimpleOutputStreamExporterOutput("reporte.pdf"));
			SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
			exp.setConfiguration(conf);
			exp.exportReport();
			JasperPrint jasperPrintWindow;
			jasperPrintWindow = JasperFillManager.fillReport("reports/" + name, null, conexion);
			JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow, false);
			jasperViewer.setVisible(true);
		} catch (JRException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}// cierre método executeReport
}// cierre clase CompileReporte
