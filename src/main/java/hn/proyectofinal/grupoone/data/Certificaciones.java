package hn.proyectofinal.grupoone.data;

public class Certificaciones extends AbstractEntity {

    private Integer certificacionID;
    private String nombre;
    private String descripcion;
    private String validez;
    private Integer cursoid;
    private Integer empleadoid;
    
    public Integer getCertificacionID() {
		return certificacionID;
	}

	public void setCertificacionID(Integer certificacionID) {
		this.certificacionID = certificacionID;
	}

	public Integer getEmpleadoid() {
		return empleadoid;
	}

	public void setEmpleadoid(Integer empleadoid) {
		this.empleadoid = empleadoid;
	}

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getValidez() {
		return validez;
	}

	public void setValidez(String validez) {
		this.validez = validez;
	}
  
	public Integer getCursoid() {
		return cursoid;
	}

	public void setCursoid(Integer cursoid) {
        this.cursoid = cursoid;
    }

}