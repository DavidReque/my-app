package hn.proyectofinal.grupoone.views.consultarcertificaciones;

import java.util.List;
import hn.proyectofinal.grupoone.data.Certificaciones;

public interface ConsultarCertificacionesViewModel {
	void mostrarCertificacionesEnComboBox(List<Certificaciones> items);//Modifique list Empleados
	void mostrarMensajeError(String mensaje);
}