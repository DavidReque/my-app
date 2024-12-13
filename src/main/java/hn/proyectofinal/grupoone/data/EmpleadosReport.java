package hn.proyectofinal.grupoone.data;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class EmpleadosReport implements JRDataSource{

	private List<Empleados> items;
	private int contador = -1;
	private int maximo = 0;
	
	
	public List<Empleados> getItems() {
		return items;
	}

	public void setItems(List<Empleados> items) {
		this.items = items;
		this.maximo = this.items.size()-1;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	@Override
	public boolean next() throws JRException {
		if (contador < maximo) {
			contador++;
			return true;
		}
		return false;
	}
	
	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if("NombreCompleto".equals(jrField.getName())) {
			return items.get(contador).getNombre()+" "+items.get(contador).getApellido();
		}
		else if("Correo".equals(jrField.getName())) {
			return items.get(contador).getEmail();
		}
		else if("DepartamentoID".equals(jrField.getName())) {
			return items.get(contador).getDepartamentoid();
		}
		
		return "";
	}

}
