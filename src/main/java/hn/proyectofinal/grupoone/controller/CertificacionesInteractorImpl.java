package hn.proyectofinal.grupoone.controller;

import hn.proyectofinal.grupoone.data.CertificacionesResponse;
import hn.proyectofinal.grupoone.repository.DatabaseRepositoryImpl;
import hn.proyectofinal.grupoone.views.consultarcertificaciones.*;

public class CertificacionesInteractorImpl implements CertificacionesInteractor{
	private DatabaseRepositoryImpl modelo;
	private ConsultarCertificacionesViewModel vista;
	
	public  CertificacionesInteractorImpl(ConsultarCertificacionesViewModel vista) {
		super();
		this.vista = vista;
		this.modelo=DatabaseRepositoryImpl.getInstance("https://apex.oracle.com/",3000L);					
	}
	
	
	@Override
	public void ConsultarCertificaciones() {
		try {
			CertificacionesResponse respuesta =this.modelo.ConsultarCertificaciones();
			if(respuesta==null|| respuesta.getCount()==0||respuesta.getItems()==null) {
				this.vista.mostrarMensajeError("No hay empleados");
								
			}else {
				this.vista.mostrarCertificacionesEnComboBox(respuesta.getItems());
				
			}
			
		}catch(Exception error){
			error.printStackTrace();
			
		}
		
	}

}