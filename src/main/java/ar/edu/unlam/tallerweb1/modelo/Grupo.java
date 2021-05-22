package ar.edu.unlam.tallerweb1.modelo;

public class Grupo {
	
	private Long id;
	private String nombre;
	private String descripcion;
	private Boolean privado;
	private Integer ctdMaxima;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Grupo))
			return false;
		Grupo other = (Grupo) obj;
		if (ctdMaxima == null) {
			if (other.ctdMaxima != null)
				return false;
		} else if (!ctdMaxima.equals(other.ctdMaxima))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (privado == null) {
			if (other.privado != null)
				return false;
		} else if (!privado.equals(other.privado))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getPrivado() {
		return privado;
	}

	public void setPrivado(Boolean privado) {
		this.privado = privado;
	}

	public Integer getCtdMaxima() {
		return ctdMaxima;
	}

	public void setCtdMaxima(Integer ctdMaxima) {
		this.ctdMaxima = ctdMaxima;
	}
}
