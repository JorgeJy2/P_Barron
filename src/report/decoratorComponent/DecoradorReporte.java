package report.decoratorComponent;
 
import report.FormatReport;
import report.Report;

public abstract class DecoradorReporte extends FormatReport implements Report {
	private Report report;
	
	public DecoradorReporte( Report report) {
		this.report = report;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
	
}
