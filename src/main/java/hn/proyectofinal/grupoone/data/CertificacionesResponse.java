package hn.proyectofinal.grupoone.data;

import java.util.List;

import hn.proyectofinal.grupoone.data.Certificaciones;


public class CertificacionesResponse {
	private List<Certificaciones> items;
	private int count;
	
	public List<Certificaciones> getItems() {
		return items;
	}
	public void setItems(List<Certificaciones> items) {
		this.items = items;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}