package report;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

/**
 * Archivo: Report.java contiene la definición de la interface Report.
 * 
 * @author Jorge Jacobo, Marcos Moreno, Gabriel Garcia, Amanda Franco
 * @version 1.0
 *
 */
public interface Report {
	// métodos de la interfaz
	public void obtenerInforme() throws JRException;

	public void compilarInforme() throws JRException;

	public void MuestraInforme() throws IOException;

}// cierre interface Report
