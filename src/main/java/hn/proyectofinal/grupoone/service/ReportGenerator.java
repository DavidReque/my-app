package hn.proyectofinal.grupoone.service;
import java.io.File;
import java.util.Map;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JasperReport;

public class ReportGenerator {
	
	private String ubicacionReporte;

	public boolean generarReportePDF(String nombreReporte, JRDataSource datasource, Map<String, Object> parameters) {
		boolean generado = false;
		try {
			JasperReport reporte = (JasperReport)JRLoader.loadObjectFromFile(obtenerUbicacionArchivo(nombreReporte+".jasper")); 
			
			JasperPrint impresora = JasperFillManager.fillReport(reporte,parameters, datasource);
			
			ubicacionReporte = generarUbicacionReporte();
			
			JasperExportManager.exportReportToPdfFile(impresora, ubicacionReporte);
			generado = true;
		}catch (Exception error) {
			error.printStackTrace();
			generado = false;
		}
		
		return generado;
	}
	
	private String generarUbicacionReporte() {
		String ubicacion = null;
		try {
			ubicacion = File.createTempFile("temp", ".pdf").getAbsolutePath();				
		}catch(Exception error) {
			error.printStackTrace();
		}
		return ubicacion;
	}

	private String obtenerUbicacionArchivo(String archivo) {
		String ubicacion = null;
		try {
			ubicacion = ResourceUtils.getFile("classpath:"+archivo).getAbsolutePath();
		}catch(Exception error) {
			error.printStackTrace();
		}return ubicacion;
	}

	public String getUbicacionReporte() {
		return ubicacionReporte;
	}
	
}
