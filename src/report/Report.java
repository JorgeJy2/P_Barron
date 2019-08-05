package report;

import java.io.IOException;

import net.sf.jasperreports.engine.JRException;

public interface Report {
 
	public void obtenerInforme() throws JRException;

	public void compilarInforme() throws JRException;

	public void MuestraInforme() throws IOException;
	
}
