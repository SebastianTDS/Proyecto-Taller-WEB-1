package ar.edu.unlam.tallerweb1.dto;

public class DatosDeMensaje {
    private Long id;
    private String mensaje;
    private Boolean esMateria;
    
    public DatosDeMensaje() {
    	this.setEsMateria(false);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

	public Boolean getEsMateria() {
		return esMateria;
	}

	public void setEsMateria(Boolean esMateria) {
		this.esMateria = esMateria;
	}
}
