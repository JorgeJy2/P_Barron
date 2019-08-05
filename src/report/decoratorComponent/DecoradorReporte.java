package report.decoratorComponent;

import report.FormatReport;
import report.Report;

/**
 * Archivo: DecoradorReporte.java contiene la definición de la clase abstracta
 * DecoradorReporte que extiende de FormatReport e implementa de Report.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public abstract class DecoradorReporte extends FormatReport implements Report {
	// declaración de atributo
	private Report report;

	/**
	 * Constructor con parámetro
	 * 
	 * @param report
	 */
	public DecoradorReporte(Report report) {
		this.report = report;
	}// cierre constructor

	// manejadores getter y setter
	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}

}// cierre clase DecoratorReporte
