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

public class CompileReporte {

	public static void excecuteReport(Connection conexion,String name) { 
			try {		
				JasperPrint  jasperPrint = JasperFillManager.fillReport(
						"reports/"+name, null,
						conexion);
				JRPdfExporter exp = new JRPdfExporter();
				exp.setExporterInput(new SimpleExporterInput(jasperPrint));
				exp.setExporterOutput(new SimpleOutputStreamExporterOutput("reporte.pdf"));
				SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
				exp.setConfiguration(conf);
				exp.exportReport(); 
				JasperPrint jasperPrintWindow; 
					jasperPrintWindow = JasperFillManager.fillReport(
							"reports/"+name, null,conexion);
					JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
					jasperViewer.setVisible(true);	
				} catch (JRException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				} 
	}
}
