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

public class ReportPeople extends FormatReport {

	private static final String REPORTE = "personas.jasper";

	@Override
	public void obtenerInforme() throws JRException {
		jasperPrint = JasperFillManager.fillReport("reports/" + REPORTE, null, conexion);
	}

	@Override
	public void compilarInforme() throws JRException {
		exportar = new JRPdfExporter();
		exportar.setExporterInput(new SimpleExporterInput(jasperPrint));
		exportar.setExporterOutput(new SimpleOutputStreamExporterOutput("reports/reporte.pdf"));
		conf = new SimplePdfExporterConfiguration();
		exportar.setConfiguration(conf);
		exportar.exportReport();
	}

	@Override
	public void MuestraInforme() throws IOException {
		File path = new File("reports/reporte.pdf");
		Desktop.getDesktop().open(path);

	}

}
